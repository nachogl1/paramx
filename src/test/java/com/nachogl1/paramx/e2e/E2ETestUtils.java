package com.nachogl1.paramx.e2e;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class E2ETestUtils {

    static RequestSpecification givenAUserIsCreated(String firstName, String secondName, String email){
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                        "firstName":"%s",
                        "secondName": "%s",
                        "email":"%s"
                        }
                        """, firstName, secondName,email));
    }

    static RequestSpecification givenAUserParameterIsCreated(String name, String value, String date, String paramUserId){
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                        "date":"%s",
                        "valueParameter": "%s",
                        "name":"%s",
                        "paramUser":{
                                "id":"%s"
                            }
                        }
                        """, date, value, name, paramUserId));
    }


}
