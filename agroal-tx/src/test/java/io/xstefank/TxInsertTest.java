package io.xstefank;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TxInsertTest {

    @RepeatedTest(100)
    void test_QuarkusTransaction_requiringNew_insert() {
        given()
            .when().get("/tx/insert")
            .then()
            .statusCode(200)
            .body(Matchers.equalTo("1"));

        given()
            .when().get("/tx/delete")
            .then()
            .statusCode(200)
            .body(Matchers.equalTo("1"));
    }

}