package com.example.notimdb.pojo.dto;

public class GenreCreateRequest {
    private String name;

    public GenreCreateRequest(String name) {
        this.name = name;
    }
    public GenreCreateRequest() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
