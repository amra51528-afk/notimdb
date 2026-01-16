package com.example.notimdb;

public class MovieNotFoundException extends IllegalArgumentException {
    public MovieNotFoundException(String message) {

        super(message);
        System.out.println("MovieNotFoundException: " + message);
    }
}
