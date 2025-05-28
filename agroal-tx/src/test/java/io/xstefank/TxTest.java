package io.xstefank;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TxTest {

    @RepeatedTest(100)
    void test_QuarkusTransaction_requiringNew_not_null() {
        given()
            .when().get("/tx")
            .then()
            .statusCode(200);
    }

    @RepeatedTest(100)
    void test_QuarkusTransaction_requiringNew_select() {
        given()
            .when().get("/tx/select")
            .then()
            .statusCode(200)
            .body("size()", Matchers.is(3));
    }

    @RepeatedTest(100)
    void test_UserTransaction() {
        given()
            .when().get("/tx/user-transaction")
            .then()
            .statusCode(200)
            .body("size()", Matchers.is(3));
    }
}