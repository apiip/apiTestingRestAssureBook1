package com.example.base;

import com.example.helper.tokenManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class testBase {
    protected static String token;
    protected static String bookId;
    protected static String userId = envLoader.getUserId();

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = envLoader.getBaseUrl();
        tokenManager.generateToken();
        tokenManager.bookIdGet();
        token = tokenManager.getToken();
        bookId = tokenManager.getBookId();

        System.out.println("=== Setup Done ===");
        System.out.println("Token: " + token);
        System.out.println("BookId: " + bookId);
        System.out.println("UserId: " + userId);
    }

     @BeforeMethod
    public void setupRequestSpec() {

        RequestSpecification spec = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all();

        RestAssured.requestSpecification = spec;

        System.out.println("===== Starting new test =====");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("This is After Method");
        if (RestAssured.requestSpecification != null) {
            RestAssured.requestSpecification = null;
        }
    }


    @AfterClass
    public void teardown() {
        System.out.println("=== All tests finished ===");
    }
}
