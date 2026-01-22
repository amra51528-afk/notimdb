package com.example.notimdb;

import com.example.notimdb.pojo.dto.CreateReviewFromUser;
import com.example.notimdb.pojo.entity.Movie;
import com.example.notimdb.pojo.entity.Review;
import com.example.notimdb.pojo.entity.User;
import com.example.notimdb.repository.MovieRepository;
import com.example.notimdb.repository.ReviewRepository;
import com.example.notimdb.repository.UserRepository;
import com.example.notimdb.service.DefaultUserService;
import com.example.notimdb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private UserService userService = new DefaultUserService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMovieReview() {
        // Given
        Integer userId = 1;
        Integer movieId = 2;

        User user = new User();
        user.setId(userId);

        Movie movie = new Movie();
        movie.setId(movieId);

        CreateReviewFromUser newReview = new CreateReviewFromUser();
        newReview.setUserId(userId);
        newReview.setRating(4.5);
        newReview.setComment("Great movie!");

        Set<Review> existingReviews = new HashSet<>();
        Review existingReview = new Review();
        existingReview.setRating(3.0);
        existingReview.setComment("Nice");
        existingReview.setMovie(movie);
        existingReview.setUser(user);
        existingReviews.add(existingReview);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(reviewRepository.findAll()).thenReturn(existingReviews);


        Movie updatedMovie = userService.addMovieReview(movieId, newReview);


        assertEquals(movie, updatedMovie);
        assertEquals(2, updatedMovie.getReviews().size());
        assertEquals(3.8, updatedMovie.getRating());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
}

