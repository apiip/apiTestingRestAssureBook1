package com.tests;
import org.testng.annotations.Test;

import com.example.base.testBase;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



public class deleteManual extends testBase {
    private static String createdIsbn;

    @Test
    public void deleteBook() {
        
        //string pojo
        // pastikan createdIsbn sudah ada (diisi saat addBook)
        //deleteBookRequest deleteReq = new deleteBookRequest("9781449325862", "0da7426c-da11-4228-9693-28831e0834b1");

        //sting manual
        // String deleteBody = "{\n" +
        //         "  \"isbn\": \"" + createdIsbn + "\",\n" +
        //         "  \"userId\": \"" + userId + "\"\n" +
        //         "}";

        given()
                //.body(deleteReq)
        .when()
                //.delete("/BookStore/v1/Book")
                .delete("/BookStore/v1/Books?UserId=0da7426c-da11-4228-9693-28831e0834b1")
        .then()
                .statusCode(204);

        System.out.println("Deleted ISBN: " + createdIsbn);
    }


    @Test
    public void lihatISBN() {
        String pakeIni = userId;
        
        Response response = given()
        .when()
        .get("/Account/v1/User/" + pakeIni)
        .then()
        .log().all()
        .statusCode(200)
        .extract().response();
        
        
        System.out.println(response);

    }






}
