package com.tests;

import com.example.base.testBase;
import com.example.helper.tokenManager;
import java.util.List;

import org.testng.annotations.DataProvider;

public class dataProvider extends testBase {

     @DataProvider(name = "addBookData2")
    public static Object[][] addBookData() {
        if (bookList == null || bookList.isEmpty()) {
            bookList = tokenManager.getBookList();

            System.out.println("DEBUG from DataProvider (lazy init): " + bookList);
        }

        return new Object[][] {
            
            {userId + "1", bookId, 401},
            {userId, bookList.get(0), 201},
            {userId, bookList.get(1), 201}

        };
    }

    @DataProvider(name = "deleteData2")
    public static Object[][] deleteData2() {
        List<String> data = booStoreTestDataProvider.createdIsbnList;

        if (data == null || data.isEmpty()) {
            // fallback jika data belum tersedia
            throw new IllegalStateException("Data provider is empty");
        }

        // Konversi List<String> ke Object[][]
        return new Object[][] {
            {userId + "1", data.get(0), 401},
            {userId, data.get(0), 204},
            {userId, data.get(0), 400}
        };
    }

}
