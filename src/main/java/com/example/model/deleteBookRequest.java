package com.example.model;

public class deleteBookRequest {
    private String userId;
    private String isbn;

    public deleteBookRequest(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
