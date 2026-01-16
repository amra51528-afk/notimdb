package com.example.notimdb.pojo.dto;

import java.time.LocalDate;
import java.util.Set;

public class MovieUpdateRequest {

    private String title;
    private String description;
    private LocalDate releaseDate;
    private double rating;
    private String posterUrl;
    private Integer directorId;
private Set<Integer> actorsIds;
private Set<Integer> genreIds;

//constructors, getters,setters

    public MovieUpdateRequest() {
    }

public MovieUpdateRequest(String title,LocalDate releaseDate, String description, double rating, String posterUrl, Integer directorId, Set<Integer> genresIds, Set<Integer> actorsIds) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.directorId = directorId;
        this.actorsIds = actorsIds;
        this.genreIds = genresIds;

}

public String getTitle() {
        return title;
}
public void setTitle(String title) {
        this.title = title;
}
public LocalDate getReleaseDate() {
        return releaseDate;
}
public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
}
public double getRating() {
        return rating;
}
public void setRating(double rating) {
        this.rating = rating;
}
public String getPosterUrl() {
        return posterUrl;
}
public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
}
public Integer getDirectorId() {
        return directorId;
}
public void setDirectorId(Integer directorId){
        this.directorId = directorId;
}
public Set<Integer> getActorsIds() {

        return actorsIds;
}
public void setActorsIds(Set<Integer> actorsIds) {
        this.actorsIds = actorsIds;
}
public Set<Integer> getGenreIds() {
        return genreIds;
}
public void setGenreIds(Set<Integer> genreIds) {

        this.genreIds = genreIds;
}


}
