package com.example

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class JankenpoResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/jankenpo/play?playerMove=ROCK")
          .then()
             .statusCode(200)
    }
}