package com.example.notimdb.service;

import com.example.notimdb.DirectorNotFoundException;
import com.example.notimdb.InvalidActorIdException;
import com.example.notimdb.MovieNotFoundException;
import com.example.notimdb.pojo.dto.MovieUpdateRequest;
import com.example.notimdb.pojo.entity.*;
import com.example.notimdb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultMovieService implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    DirectorRepository directorRepository;
    GenreRepository genreRepository;
    ActorRepository actorRepository;
    ReviewRepository reviewRepository;

    @Autowired
    public DefaultMovieService(MovieRepository movieRepository,DirectorRepository directorRepository,GenreRepository genreRepository,ActorRepository actorRepository,ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.genreRepository=genreRepository;
        this.actorRepository=actorRepository;
        this.reviewRepository=reviewRepository;
    }

    @Override
    public List<Movie> getAllMovies() {

        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(Integer id, MovieUpdateRequest request) {

        try {
            Movie movie = getMovieById(id);
            if (movie != null) {
                movie.setTitle(request.getTitle());
                movie.setReleaseDate(request.getReleaseDate());
                movie.setDescription(request.getDescription());
                movie.setRating(request.getRating());
                movie.setTitle(request.getTitle());
                movie.setPosterUrl(request.getPosterUrl());
                Director director = directorRepository.findById(request.getDirectorId()).orElse(null);
                movie.setDirector(director);
                Set<Genre> genres = new HashSet<>();
                for (Integer reqid : request.getGenresIds()) {
                    Genre genre = genreRepository.findById(reqid).orElse(null);
                    genres.add(genre);
                }
                movie.setGenres(genres);
                Set<Actor> actors = new HashSet<>();
                for (Integer reqid : request.getActorsIds()) {
                    Actor actor = actorRepository.findById(reqid).orElse(null);
                    actors.add(actor);
                }
                movie.setActors(actors);
                movieRepository.save(movie);
            }
            else {
                throw new MovieNotFoundException("Movie with id " + id + " not found.");
            }
            return movie;
        } catch (MovieNotFoundException e) {
            throw new RuntimeException("Error updating movie with id " + id, e);
        }

    }

    @Override
    public List<Movie> getMovieByActor(Integer id) {
        try {
            Actor actor = actorRepository.findById(id).orElse(null);
            if (actor != null) {
                List<Movie> moviesOfTheActor = new ArrayList<>();
                for (Movie movie : movieRepository.findAll()) {
                    if (movie.getActors().contains(actor)) {
                        moviesOfTheActor.add(movie);
                    }
                }
                return moviesOfTheActor;
            }
            else {
                throw new InvalidActorIdException("Invalid actor id: " + id);
            }
        }catch (InvalidActorIdException e) {
            throw new RuntimeException("Error fetching movies by actor with id " + id, e);
        }
    }

    @Override
    public List<Movie> getMovieByGenre(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException("Genre with ID " + id + " not found"));
        List<Movie> moviesOfGenre = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            if (movie.getGenres().contains(genre)) {
                moviesOfGenre.add(movie);
            }
        }
        return moviesOfGenre;
    }

    @Override
    public List<Movie> getMovieByDirector(Integer id) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new DirectorNotFoundException("Director with ID " + id + " not found"));
        List<Movie> moviesOfDirector = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            if (movie.getDirector().equals(director)) {
                moviesOfDirector.add(movie);
            }
        }
        return moviesOfDirector;
    }

    @Override
    public Long getTotalNrOfMovies() {
        return movieRepository.count();
    }

    @Override
    public List<Movie> searchMovieByTitle(String title) {

        List<Movie> moviesOfTitle = new ArrayList<>();
        if (title == null){
            return moviesOfTitle;
        }
        for (Movie movie:movieRepository.findAll()) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())){
                moviesOfTitle.add(movie);
            }
        }
        return moviesOfTitle;
    }

    @Override
    public List<Movie> TopTenMovies(Integer genre) {
        List<Movie> movies;
        if (genre == null) {

            movies = movieRepository.findAll();
        } else {
            movies = getMovieByGenre(genre);
        }

        movies.sort(Comparator.comparing(Movie::getRating).reversed());

        List<Movie> topTenMovies = movies.stream()
                .limit(10)
                .collect(Collectors.toList());

        return topTenMovies;

    }

}
