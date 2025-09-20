package com.example.base;
import io.github.cdimascio.dotenv.Dotenv;

public class envLoader {
     private static final Dotenv dotenv = Dotenv.configure()
            .directory("./")   // pastikan cari di root project
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }

    public static String getUsername() {
        return dotenv.get("USERNAME_APP");
    }

    public static String getPassword() {
        return dotenv.get("PASSWORD");
    }

    public static String getUserId() {
        return dotenv.get("USER_ID");
    }
    
}
