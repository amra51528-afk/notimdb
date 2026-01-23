package com.example.notimdb;


import com.example.notimdb.pojo.entity.Movie;
import com.example.notimdb.repository.*;
import com.example.notimdb.service.DefaultMovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class MovieTests {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private DefaultMovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getAllMovies()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());
    }
    @Test
    void testGetMovieById() {
        Movie movie = new Movie();
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovieById(1);

        assertNotNull(result);
        assertEquals(movie, result);
    }

    @Test
    void testCreateMovie() {

        Movie movie = new Movie();
        when(movieRepository.save(movie)).thenReturn(movie);


        Movie result = movieService.createMovie(movie);


        assertNotNull(result);
        assertEquals(movie, result);
    }

    @Test
    void testDeleteMovie() {

        movieService.deleteMovie(1);

        verify(movieRepository, times(1)).deleteById(1);
    }

    @Test
    void testSearchMovieByTitle() {
        // Arrange
        List<Movie> allMovies = Arrays.asList(
                new Movie(1, "Title1", LocalDate.now(), "Description1", 8.0, "Poster1", new Director(), new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new Movie(2, "Title2", LocalDate.now(), "Description2", 7.5, "Poster2", new Director(), new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new Movie(3, "AnotherTitle", LocalDate.now(), "AnotherDescription", 9.0, "Poster3", new Director(), new HashSet<>(), new HashSet<>(), new HashSet<>())
        );
        when(movieRepository.findAll()).thenReturn(allMovies);

        // Act
        List<Movie> result = movieService.searchMovieByTitle("title");

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(movie -> movie.getTitle().toLowerCase().contains("title".toLowerCase())));
    }




}
