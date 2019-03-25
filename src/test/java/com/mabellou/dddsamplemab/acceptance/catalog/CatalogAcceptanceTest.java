package com.mabellou.dddsamplemab.acceptance.catalog;

import com.mabellou.dddsamplemab.acceptance.AbstractAcceptanceTest;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

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
            .body("unitPrice", everyItem(notNullValue()))
        ;
    }
}
