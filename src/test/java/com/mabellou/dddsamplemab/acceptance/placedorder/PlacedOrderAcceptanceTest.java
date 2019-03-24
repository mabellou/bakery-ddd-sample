package com.mabellou.dddsamplemab.acceptance.placedorder;

import com.mabellou.dddsamplemab.acceptance.AbstractAcceptanceTest;
import com.mabellou.dddsamplemab.acceptance.TestObjects;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrder;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

public class PlacedOrderAcceptanceTest extends AbstractAcceptanceTest {

    @Autowired
    private PlacedOrderRepository placedOrderRepository;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void placeAnOrder(){
        assertThat(placedOrderRepository.findAll()).isEmpty();

        given()
                .contentType(ContentType.JSON)
                .body(TestObjects.placeAnOrderRequest())
                .post("/placedorder")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", matchesPattern(".*\\d.*"))
        ;

        List<PlacedOrder> placedOrders = placedOrderRepository.findAll();

        assertThat(placedOrders).hasSize(1);
    }
}
