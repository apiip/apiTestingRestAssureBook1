package com.example.helper;
import com.example.base.envLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class tokenManager {
    private static String token;
    private static String bookId;

    public static void generateToken() {
        String body = "{\n" +
                "  \"userName\": \"" + envLoader.getUsername() + "\",\n" +
                "  \"password\": \"" + envLoader.getPassword() + "\"\n" +
                "}";

        // Generate token
        Response tokenRes = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        token = tokenRes.jsonPath().getString("token");

    }
    public static void bookIdGet() {
                // ambil Id Buku
        Response bookRes = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/Bookstore/v1/Books")
                .then()
                .statusCode(200)
                .extract().response();

        bookId = bookRes.jsonPath().getString("books[0].isbn");
    }

    public static String getToken() {
        return token;
    }

    public static String getBookId() {
        return bookId;
    }
}
