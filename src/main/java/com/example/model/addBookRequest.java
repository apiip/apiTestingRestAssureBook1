package com.example.model;
import java.util.List;

public class addBookRequest {
    private String userId;
    private List<isbn> collectionOfIsbns;

    public addBookRequest(String userId, List<isbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<isbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<isbn> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
