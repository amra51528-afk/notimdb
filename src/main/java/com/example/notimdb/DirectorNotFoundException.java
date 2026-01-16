package com.example.notimdb;

public class DirectorNotFoundException extends IllegalArgumentException {
    public DirectorNotFoundException(String s) {

        super(s);
        System.out.println("DirectorNotFoundException: " + s);
    }
}
