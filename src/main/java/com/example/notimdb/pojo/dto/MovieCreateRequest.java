package com.example.notimdb.pojo.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MovieCreateRequest {
    private String title;
    private String description;
    private Integer directorId;
    private LocalDate releaseDate;
    private Double rating;
    private String posterUrl;
    private Set<Integer> genresIds;
    private Set<Integer> actorsIds;

    public MovieCreateRequest() {
    }
    public MovieCreateRequest(String title, String description, Integer directorId, LocalDate releaseDate, Double rating) {
        this.title = title;
        this.description = description;
        this.directorId = directorId;
        this.releaseDate = releaseDate;
        this.rating = 0.0;
        this.posterUrl = posterUrl;
        this.genresIds = genresIds;
        this.actorsIds = actorsIds;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getDirectorId() {
        return directorId;
    }
    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public Set<Integer> getGenresIds() {
        return genresIds;
    }
    public void setGenresIds(Set<Integer> genresIds) {
        this.genresIds = genresIds;
    }
    public Set<Integer> getActorsIds() {
        return actorsIds;

    }
    public void setActorsIds(Set<Integer> actorsIds) {
        this.actorsIds = actorsIds;
    }

    }


