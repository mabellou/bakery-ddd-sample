package com.mabellou.dddsamplemab.acceptance.catalog;

import com.mabellou.dddsamplemab.acceptance.AbstractAcceptanceTest;
import com.mabellou.dddsamplemab.acceptance.TestObjects;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
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
import static org.hamcrest.Matchers.*;

public class CatalogAcceptanceTest extends AbstractAcceptanceTest {

    @Autowired
    private Catalog catalog;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void getCatalog(){
        assertThat(catalog.findAll()).isNotEmpty();

        given()
            .get("/catalog")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", everyItem(notNullValue()))
            .body("name", everyItem(notNullValue()))
        ;
    }
}
