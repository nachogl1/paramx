package com.nachogl1.paramx.e2e;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.nachogl1.paramx.e2e.E2ETestUtils.givenAUserIsCreated;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class E2EParamUserTests extends E2ETests {


    @Test
    public void givenAnUser_WhenAdded_ThenResponseIsTheAddedUser() {

        givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("firstName", equalTo("John"))
                .body("secondName", equalTo("Doe"))
                .body("email", equalTo("John@hotmail.com"))
                .body("textParametersList", Matchers.nullValue());

    }


    @Test
    public void givenAnUser_WhenFetching_ThenUserIsReturned() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

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
    public void givenAnUser_WhenUpdated_TheResponseBringsTheNewUpdatedUser() {

        String paramUserId = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                        "id":"%s",
                        "firstName":"John2",
                        "secondName": "Doe",
                        "email":"John2@hotmail.com"
                        }
                        """, paramUserId))
                .when()
                .put("/users");


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId)
                .get("/users/{paramUserId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(paramUserId))
                .body("firstName", equalTo("John2"))
                .body("secondName", equalTo("Doe"))
                .body("email", equalTo("John2@hotmail.com"))
                .body("textParametersList", emptyIterable());

    }


    @Test
    public void givenAFewUsersAdded_WhenFetchingAll_ThenAllAreReturned() {

        String paramUserId1 = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        String paramUserId2 =
                givenAUserIsCreated("Pepe", "Doe", "pepe@hotmail.com")
                        .when()
                        .post("/users")
                        .jsonPath()
                        .getString("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(2))
                .body("[0].id", equalTo(paramUserId1))
                .body("[0].firstName", equalTo("John"))
                .body("[0].secondName", equalTo("Doe"))
                .body("[0].email", equalTo("John@hotmail.com"))
                .body("[0].textParametersList", nullValue())
                .body("[1].id", equalTo(paramUserId2))
                .body("[1].firstName", equalTo("Pepe"))
                .body("[1].secondName", equalTo("Doe"))
                .body("[1].email", equalTo("pepe@hotmail.com"))
                .body("[1].textParametersList", nullValue());

    }


    @Test
    public void givenAUser_WhenDeleted_ThenFetchingDoesNotBringThatUser() {

        String paramUserId1 = givenAUserIsCreated("John", "Doe", "John@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        String paramUserId2 = givenAUserIsCreated("Pepe", "Doe", "pepe@hotmail.com")
                .when()
                .post("/users")
                .jsonPath()
                .getString("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("paramUserId", paramUserId2)
                .delete("/users/{paramUserId}");


        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(1))
                .body("[0].id", equalTo(paramUserId1))
                .body("[0].firstName", equalTo("John"))
                .body("[0].secondName", equalTo("Doe"))
                .body("[0].email", equalTo("John@hotmail.com"))
                .body("[0].textParametersList", nullValue());

    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void givenANonValidUser_WhenAddingIt_ThenItThrows(final String inputJson) {
        given()
                .contentType(ContentType.JSON)
                .body(inputJson)
                .when()
                .post("/users")
                .then()
                .statusCode(400)
                .body("message", notNullValue())
                .body("status", equalTo("BAD_REQUEST"))
                .body("statusCode", equalTo(HttpStatus.BAD_REQUEST.value()))
                .body("timestamp", notNullValue());
    }

    static Stream<String> dataProvider() {
        return Stream.of(
                """
                        {
                        "firstName":"",
                        "secondName": "test",
                        "email":"test@hotmail.com"
                        }
                        """,
                """
                        {
                        "firstName":"test",
                        "secondName": "",
                        "email":"test@hotmail.com"
                        }
                        """,
                """
                        {
                        "firstName":"test",
                        "secondName": "test",
                        "email":"test@.com"
                        }
                        """,
                """
                        {
                        "firstName":"test",
                        "secondName": "test",
                        "email":""
                        }
                        """
        );
    }


}
