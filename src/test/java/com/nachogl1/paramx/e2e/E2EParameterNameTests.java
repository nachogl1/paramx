package com.nachogl1.paramx.e2e;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static com.nachogl1.paramx.e2e.E2ETestUtils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class E2EParameterNameTests extends E2ETests {

    @Test
    public void givenAParamName_WhenAdded_ThenResponseReturnsParamName() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        givenAUserParameterNameIsCreated("testName", paramUserId)
                .when()
                .post("/textParameters/names")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("name", equalTo("testName"));

    }


    @Test
    public void givenAParamAdded_WhenFetchingParamNames_ThenReturnOneName() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        givenAUserParameterNameIsCreated("testName", paramUserId)
                .when()
                .post("/textParameters/names");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/names/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].id", notNullValue())
                .body("[0].name", equalTo("testName"));

    }

    @Test
    public void givenAParamAdded_WhenDeletingParamNames_ThenWasRemoved() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        String nameId = givenAUserParameterNameIsCreated("testName", paramUserId)
                .when()
                .post("/textParameters/names")
                .jsonPath()
                .getString("id");
        ;

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("parameterNameId", nameId)
                .delete("/textParameters/names/{parameterNameId}");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/names/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(0));

    }

    @Test
    public void givenAParamAdded_WhenUpdatingParamNames_ThenReturnUpdated() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        String paramNameId = givenAUserParameterNameIsCreated("testName", paramUserId)
                .when()
                .post("/textParameters/names")
                .jsonPath()
                .getString("id");

        given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                        "name":"testName2",
                        "id":"%s",
                        "paramUser":{
                                "id":"%s"
                            }
                        }
                        """, paramNameId, paramUserId))
                .when()
                .put("/textParameters/names");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/names/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].id", notNullValue())
                .body("[0].name", equalTo("testName2"));

    }

}
