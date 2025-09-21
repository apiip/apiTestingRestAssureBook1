package com.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.base.testBase;
import com.example.model.addBookRequest;
import com.example.model.addBookResponse;
import com.example.model.deleteBookRequest;
import com.example.model.isbn;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class booStoreTestDataProvider extends testBase {
    protected static List<String> createdIsbnList = new ArrayList<>();

    //private static List<String> createdIsbnList2 = new ArrayList<>();
    //private static String StarterIsbn;


    @Test(dataProvider = "addBookData2", dataProviderClass = dataProvider.class)
    public void addBook(String userId, String bookList, int statusCode) {
        System.out.println("Running test with userId=" + userId);

         // ISBN sudah siap dari BaseTest.BeforeMethod → isbnFromGet
         // addbookrequest itu namanya constructor
        addBookRequest req = new addBookRequest(
                userId,
                Collections.singletonList(new isbn(bookList))
        );

        Response response =
        given()
                .body(req)
        .when()
                .post("/BookStore/v1/Books")
        .then()
                .statusCode(statusCode)
                .extract().response();

        if (statusCode == 201) {
            //pemanggilan dan save json ke pojo
            addBookResponse res = response.as(addBookResponse.class);
            String createdIsbn = res.getBooks().get(0).getIsbn();
            createdIsbnList.add(createdIsbn);
            Assert.assertTrue(createdIsbn.contains(bookList));
            
            System.out.println("Book added with ISBN: " + createdIsbn);
            System.out.println("Books on ISBN List: " + createdIsbnList);
        } else {
            System.out.println("Ini tidak masukin ISBN " + statusCode);
            System.out.println("Books on ISBN List: " + createdIsbnList);
        }
        
        //pemanggilan manual tanpa Pojo
        // createdIsbn = response.jsonPath().getString("books[0].isbn");
        // System.out.println("Added ISBN: " + createdIsbn);
    }

    @Test(dependsOnMethods = "addBook")
    public void lihatISBN() {
        String pakeIni = userId;
        
        given()
        .when()
                .get("/Account/v1/User/" + pakeIni)
        .then()
                .statusCode(200)
                .body("books", notNullValue())
                .extract().response();
        
        System.out.println("Ini ISBN Tersedia: " + createdIsbnList);

    }


    @Test(dependsOnMethods = "lihatISBN", dataProvider = "deleteData2", dataProviderClass = dataProvider.class)
    public void deleteBook(String UserId, String data, int statusCode) {

        //string pojo
        // pastikan createdIsbn sudah ada (diisi saat addBook)
        deleteBookRequest deleteReq = new deleteBookRequest(data, UserId);

        Response response =
        given()
                .body(deleteReq)
        .when()
                .delete("/BookStore/v1/Book")
        .then()
                .log().all()
                .statusCode(statusCode)
                .extract().response();


        String message = (response.getStatusCode() == 204 || response.getBody().asString().isEmpty()) 
                    ? "No message" 
                    : response.path("message");

        System.out.println("Code " + statusCode);
        System.out.println("Deleted ISBN: " + data);
        System.out.println("Deleted ISBN: " + message);
    }

}