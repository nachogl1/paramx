package com.nachogl1.paramx.e2e;

import com.nachogl1.paramx.ParamxApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.assertj.core.api.Fail.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class E2ETests {

    @BeforeAll
    public static void beforeAll() {
        new SpringApplication(ParamxApplication.class);
        RestAssured.baseURI = "http://localhost:8080";
    }

    @BeforeEach
    void setUp() {
        cleanDb();
    }

    @AfterAll
    static void afterAll() {
        cleanDb();
    }

    private static void cleanDb() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:./paramx/local-test/db", "root", "")) {
            Statement stmt = conn.createStatement();
            stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");
            stmt.executeUpdate("TRUNCATE TABLE TEXT_PARAMETER");
            stmt.executeUpdate("TRUNCATE TABLE PARAM_USER");
            stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
