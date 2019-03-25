package com.mabellou.dddsamplemab.acceptance.registration;

import com.mabellou.dddsamplemab.acceptance.AbstractAcceptanceTest;
import com.mabellou.dddsamplemab.acceptance.TestObjects;
import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.RegisteredCustomerList;
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
}
