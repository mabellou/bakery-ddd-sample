package com.mabellou.dddsamplemab.acceptance.registration;

import com.mabellou.dddsamplemab.acceptance.AbstractAcceptanceTest;
import com.mabellou.dddsamplemab.acceptance.TestObjects;
import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.customer.RegisteredCustomerList;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.Optional;

import static com.mabellou.dddsamplemab.acceptance.TestObjects.createNouvelleAddress;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

public class RegistrationAcceptanceTest extends AbstractAcceptanceTest {

    @Autowired
    private RegisteredCustomerList registeredCustomerList;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void registerNewCustomer(){
        int initialSize = registeredCustomerList.findAll().size();

        given()
            .contentType(ContentType.JSON)
            .body(TestObjects.registrationRequest())
            .post("/registration")
        .then()
            .statusCode(HttpStatus.SC_CREATED)
            .header("Location", matchesPattern(".*\\d.*"))
        ;

        List<Customer> registeredCustomers = registeredCustomerList.findAll();

        assertThat(registeredCustomers).hasSize(initialSize + 1);
    }

    @Test
    public void changeAddress(){
        CustomerId customerId = registeredCustomerList.nextCustomerId();
        registeredCustomerList.save(TestObjects.createCustomer(customerId));

        given()
                .contentType(ContentType.JSON)
                .body(TestObjects.nouvelleAddresseRequest(customerId, createNouvelleAddress()))
                .put("/customer/" + customerId.idString() + "/address")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
        ;

        Optional<Customer> registeredCustomers = registeredCustomerList.findById(customerId);

        assertThat(registeredCustomers).isPresent();
        assertThat(registeredCustomers.get().address()).isEqualTo(createNouvelleAddress());
    }
}
