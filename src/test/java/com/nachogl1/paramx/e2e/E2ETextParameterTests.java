package com.nachogl1.paramx.e2e;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.nachogl1.paramx.e2e.E2ETestUtils.givenAUserIsCreated;
import static com.nachogl1.paramx.e2e.E2ETestUtils.givenAUserParameterIsCreated;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class E2ETextParameterTests extends E2ETests {


    @Test
    public void givenAParam_WhenAdded_ThenResponseReturnsParam() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("date", equalTo("2024-05-01"))
                .body("valueParameter", equalTo("testValue"))
                .body("name", equalTo("testName"));

    }

    @Test
    public void givenAParamAdded_WhenFetchingParamNames_ThenReturnOneName() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/names/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", contains("testName"));


    }


    @Test
    public void givenAParamIsAddedForUser_WhenFetchingSpecificUser_ThenParamsAreIncludedInResponse() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters");

        givenAUserParameterIsCreated("testName2", "testValue2", "2024-05-02", paramUserId)
                .when()
                .post("/textParameters");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/users/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("textParametersList.size()", equalTo(2))
                .body("textParametersList[0].id", notNullValue())
                .body("textParametersList[0].date", equalTo("2024-05-01"))
                .body("textParametersList[0].valueParameter", equalTo("testValue"))
                .body("textParametersList[0].name", equalTo("testName"))
                .body("textParametersList[1].id", notNullValue())
                .body("textParametersList[1].date", equalTo("2024-05-02"))
                .body("textParametersList[1].valueParameter", equalTo("testValue2"))
                .body("textParametersList[1].name", equalTo("testName2"));


    }


    @Test
    public void givenAUserAndParamsAdded_WhenFetchingParamByUserId_ThenThatUserParametersAreBrought() {
        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");


        givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters")
                .jsonPath()
                .getString("id");


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].id", notNullValue())
                .body("[0].date", equalTo("2024-05-01"))
                .body("[0].valueParameter", equalTo("testValue"))
                .body("[0].name", equalTo("testName"));
    }


    @Test
    public void givenAParam_WhenUpdated_ThenResponseBringsNewUpdatedParam() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");


        String textParamId = givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters")
                .jsonPath()
                .getString("id");

        given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                        "date":"2024-05-02",
                        "valueParameter": "testValue2",
                        "name":"testName2",
                        "id":"%s",
                        "paramUser":{
                                "id":"%s"
                            }
                        }
                        """, textParamId, paramUserId))
                .when()
                .put("/textParameters");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].id", notNullValue())
                .body("[0].date", equalTo("2024-05-02"))
                .body("[0].valueParameter", equalTo("testValue2"))
                .body("[0].name", equalTo("testName2"));


    }


    @Test
    public void givenAParam_WhenDeleted_FetchingUserParamDoesNotBringTheDeletedParam() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        String textParamId = givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters")
                .jsonPath()
                .getString("id");

        givenAUserParameterIsCreated("testName2", "testValue2", "2024-05-02", paramUserId)
                .when()
                .post("/textParameters");


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("textParamId", textParamId)
                .delete("/textParameters/{textParamId}");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].id", notNullValue())
                .body("[0].date", equalTo("2024-05-02"))
                .body("[0].valueParameter", equalTo("testValue2"))
                .body("[0].name", equalTo("testName2"));


    }


    @Test
    public void givenAParamIsDeleted_WhenFetchingAssociatedUser_ThenStillExists() {
        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        String textParamId = givenAUserParameterIsCreated("testName1", "testValue1", "2024-05-02", paramUserId)
                .when()
                .post("/textParameters")
                .jsonPath()
                .getString("id");


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("textParamId", textParamId)
                .delete("/textParameters/{textParamId}");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/users/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(paramUserId))
                .body("firstName", equalTo("John"))
                .body("secondName", equalTo("Doe"))
                .body("email", equalTo("John@hotmail.com"))
                .body("textParametersList", emptyIterable());
    }


    @Test
    public void givenAUserWithParamsIsDeleted_WhenFetchingItsParams_ThenBringsNothing() {
        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        givenAUserParameterIsCreated("testName", "testValue", "2024-05-01", paramUserId)
                .when()
                .post("/textParameters");


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .delete("/users/{paramUserId}");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/users/{paramUserId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("User does not exists"))
                .body("status", equalTo("NOT_FOUND"))
                .body("statusCode", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("timestamp", notNullValue());


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/textParameters/{paramUserId}")
                .then()
                .statusCode(200)
                .body("", emptyIterable());


    }


}
