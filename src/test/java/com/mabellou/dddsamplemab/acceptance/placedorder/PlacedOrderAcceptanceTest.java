package com.mabellou.dddsamplemab.acceptance.placedorder;

import com.mabellou.dddsamplemab.acceptance.AbstractAcceptanceTest;
import com.mabellou.dddsamplemab.acceptance.TestObjects;
import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.customer.RegisteredCustomerList;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrder;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.List;

import static com.mabellou.dddsamplemab.acceptance.TestObjects.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class PlacedOrderAcceptanceTest extends AbstractAcceptanceTest {

    @Autowired
    private PlacedOrderRepository placedOrderRepository;

    @Autowired
    private Catalog catalog;

    @Autowired
    private RegisteredCustomerList registeredCustomerList;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void placeAnOrderAndGetInvoice(){
        int initSize = placedOrderRepository.findAll().size();

        Customer customer = createCustomer(registeredCustomerList.nextCustomerId());
        registeredCustomerList.save(customer);

        AvailableProduct product1 = createPainAuChocolat_1euro(catalog.nextProductId());
        catalog.save(product1);

        AvailableProduct product2 = createCroissant_1euro(catalog.nextProductId());
        catalog.save(product2);

        String request = placeAnOrderRequestWithTwoLines(
                customer.customerId(),
                1, product1.productId(),
                2, product2.productId()
        );

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/placedorder")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", matchesPattern(".*\\d.*"))
        ;

        List<PlacedOrder> placedOrders = placedOrderRepository.findAll();

        assertThat(placedOrders).hasSize(initSize + 1);
        assertThat(placedOrders.get(initSize).totalPrice()).isEqualTo(new BigDecimal(3));

        String id = placedOrders.get(initSize).placedOrderId().idString();

        given()
                .contentType(ContentType.JSON)
                .get("/placedorder/" + id + "/invoice")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("amount", hasToString("3"))
                .body("status", hasToString("NOT_PAID"))
        ;
    }
}
