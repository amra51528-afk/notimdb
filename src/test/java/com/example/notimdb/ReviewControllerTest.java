package com.example.notimdb;

import com.example.notimdb.controller.ReviewController;
import com.example.notimdb.pojo.dto.CreateReviewRequest;
import com.example.notimdb.pojo.entity.Movie;
import com.example.notimdb.pojo.entity.Review;
import com.example.notimdb.pojo.entity.User;
import com.example.notimdb.repository.MovieRepository;
import com.example.notimdb.repository.ReviewRepository;
import com.example.notimdb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private ReviewController reviewController;

    private User user;
    private Movie movie;
    private CreateReviewRequest newReviewRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        movie = new Movie();
        newReviewRequest = new CreateReviewRequest(5.0, "Great movie!", 1, 1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> {
            Review createdReview = invocation.getArgument(0);
            createdReview.setId(1);
            return createdReview;
        });
    }

    @Test
    void testCreateReview() throws Exception {

        newReviewRequest.setComment("Great movie!");


        Review createdReview = reviewController.createReview(newReviewRequest);


        assertNotNull(createdReview);
        assertEquals(newReviewRequest.getRating(), createdReview.getRating());
        assertEquals(newReviewRequest.getComment(), createdReview.getComment());
        assertEquals(user, createdReview.getUser());
        assertEquals(movie, createdReview.getMovie());
        verify(userRepository, times(1)).findById(newReviewRequest.getUserId());
        verify(movieRepository, times(1)).findById(newReviewRequest.getMovieId());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
}

