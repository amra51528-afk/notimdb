package com.example.notimdb.pojo.dto;

public class CreateReviewFromUser {
    private Double rating;
    private String comment;
    private Integer userId;

    public CreateReviewFromUser() {

    }
    public CreateReviewFromUser(Double rating, String comment, Integer userId) {
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

