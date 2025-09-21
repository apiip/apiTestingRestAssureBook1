package com.tests;
import org.testng.annotations.Test;

import com.example.base.testBase;
import com.example.model.addBookRequest;
import com.example.model.addBookResponse;
import com.example.model.deleteBookRequest;
import com.example.model.isbn;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Collections;
import static org.testng.Assert.assertEquals;


public class bookStoreTest1 extends testBase {
    private static String createdIsbn;
    private static String StarterIsbn;

    @Test
    public void getAllBooks() {
        Response response = given()
        .when()
                .get("/BookStore/v1/Books");
        
        response.then()
                .statusCode(200);
        
        //pemanggilan dan save json ke pojo
        addBookResponse res = response.as(addBookResponse.class);
        StarterIsbn = res.getBooks().get(0).getIsbn();
        assertEquals(StarterIsbn, bookId);

        System.out.println("ISBN Buat Add: " + StarterIsbn);
    }


    @Test(priority = 1)
    public void addBook() {
         // ISBN sudah siap dari BaseTest.BeforeMethod → isbnFromGet
         // addbookrequest itu namanya constructor
        addBookRequest req = new addBookRequest(
                userId,
                Collections.singletonList(new isbn(bookId))
        );

        Response response = given()
                .body(req)
        .when()
                .post("/BookStore/v1/Books")
        .then()
                .log().all()
                .statusCode(201)
                .extract().response();

        //pemanggilan dan save json ke pojo
        addBookResponse res = response.as(addBookResponse.class);
        createdIsbn = res.getBooks().get(0).getIsbn();
        assertEquals(createdIsbn, bookId);
        
        System.out.println("Book added with ISBN: " + createdIsbn);
        
        //pemanggilan manual tanpa Pojo
        // createdIsbn = response.jsonPath().getString("books[0].isbn");
        // System.out.println("Added ISBN: " + createdIsbn);
    }


    @Test(dependsOnMethods = "addBook", priority = 2)
    public void getOneBook() {
        given()
                .queryParam("ISBN", createdIsbn)
        .when()
                .get("/BookStore/v1/Book")
        .then()
                .statusCode(200);
        
        System.out.println("ISBN: " + createdIsbn);
    }

    @Test(dependsOnMethods = "getOneBook", priority = 3)
    public void deleteBook() {
        
        //string pojo
        // pastikan createdIsbn sudah ada (diisi saat addBook)
        deleteBookRequest deleteReq = new deleteBookRequest(createdIsbn, userId);

        //sting manual
        // String deleteBody = "{\n" +
        //         "  \"isbn\": \"" + createdIsbn + "\",\n" +
        //         "  \"userId\": \"" + userId + "\"\n" +
        //         "}";

        given()
                .body(deleteReq)
        .when()
                .delete("/BookStore/v1/Book")
        .then()
                .statusCode(204);

        System.out.println("Deleted ISBN: " + createdIsbn);
    }

    @Test
    public void deleteAllBooks() {
        
        given()
                //.body(deleteReq)
        .when()
                //.delete("/BookStore/v1/Book")
                .delete("/BookStore/v1/Books?UserId=0da7426c-da11-4228-9693-28831e0834b1")
        .then()
                .statusCode(204);

        System.out.println("Deleted ISBN: " + createdIsbn);
    }
}
