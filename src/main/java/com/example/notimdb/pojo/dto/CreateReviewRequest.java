package com.example.notimdb.pojo.dto;

public class CreateReviewRequest {
    private Double rating;

    private String comment;
    private Integer userId;
    private Integer movieId;
    private Integer reviewId;

    public CreateReviewRequest() {
    }

    public CreateReviewRequest(Double rating, String comment, Integer userId, Integer movieId, Integer reviewId) {
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.movieId = movieId;
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
    public Integer getMovieId() {
        return movieId;
    }
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    public Integer getReviewId() {
        return reviewId;
    }
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }


}
