package com.notimdb.notimdb.controller;

import com.notimdb.notimdb.pojo.dto.MovieCreateRequest;
import com.notimdb.notimdb.pojo.dto.MovieUpdateRequest;
import com.notimdb.notimdb.pojo.entity.*;
import com.notimdb.notimdb.repository.ActorRepository;
import com.notimdb.notimdb.repository.DirectorRepository;
import com.notimdb.notimdb.repository.GenreRepository;
import com.notimdb.notimdb.repository.ReviewRepository;
import com.notimdb.notimdb.service.MovieService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@RestController
public class MovieController {
    MovieService movieService;
    DirectorRepository directorRepository;
    GenreRepository genreRepository;
    ActorRepository actorRepository;
    ReviewRepository reviewRepository;

    @Autowired
    public MovieController(MovieService movieService,DirectorRepository directorRepository,GenreRepository genreRepository,ActorRepository actorRepository,ReviewRepository reviewRepository) {
        this.movieService = movieService;
        this.directorRepository = directorRepository;
        this.genreRepository=genreRepository;
        this.actorRepository=actorRepository;
        this.reviewRepository=reviewRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable int id){
        return movieService.getMovieById(id);
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody MovieCreateRequest newMovie) {
        Movie movie = new Movie();
        movie.setTitle(newMovie.getTitle());
        movie.setReleaseDate(newMovie.getReleaseDate());
        movie.setDescription(newMovie.getDescription());
        movie.setRating(newMovie.getRating());
        movie.setTitle(newMovie.getTitle());
        movie.setPosterUrl(newMovie.getPosterUrl());
        Director director = directorRepository.findById(newMovie.getDirectorId()).orElse(null);
        movie.setDirector(director);
        Set<Genre> genres = new HashSet<>();
        for (Integer id:newMovie.getGenresIds()) {
            Genre genre = genreRepository.findById(id).orElse(null);
            genres.add(genre);
        }
        movie.setGenres(genres);
        Set<Actor> actors = new HashSet<>();
        for (Integer id:newMovie.getActorsIds()) {
            Actor actor = actorRepository.findById(id).orElse(null);
            actors.add(actor);
        }
        movie.setActors(actors);
        return movieService.createMovie(movie);
    }
    @PutMapping("/movies/{id}")
    public Movie update(
            @PathVariable Integer id,
            @RequestBody MovieUpdateRequest request) {

        return movieService.updateMovie(id,request);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("movies/actor/{id}")
    public List<Movie> getMovieByActor(@PathVariable Integer id){
        return movieService.getMovieByActor(id);
    }

    @GetMapping("movies/genre/{id}")
    public List<Movie> getMoviesByGenre(@PathVariable Integer id){
        return movieService.getMovieByGenre(id);
    }

    @GetMapping("movies/director/{id}")
    public List<Movie> getMoviesByDirector(@PathVariable Integer id){
        return movieService.getMovieByDirector(id);
    }

    @GetMapping(value = {"/movies/search","movies/search/{title}"})
    public List<Movie> getMoviesByTitle(@PathVariable(required = false) String title){
        return movieService.searchMovieByTitle(title);
    }

    @GetMapping("movies/count")
    public Long getTotalNrOfMovies(){
        return movieService.getTotalNrOfMovies();
    }
    @GetMapping(value = {"/movies/toprated","movies/toprated/{id}"})
    public List<Movie> getTopRated(@PathVariable(required = false) Integer id){
        return movieService.TopTenMovies(id);
    }
}

