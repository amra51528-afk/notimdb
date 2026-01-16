package com.example.notimdb.controller;

import com.example.notimdb.pojo.dto.CreateReviewRequest;
import com.example.notimdb.pojo.entity.Director;
import com.example.notimdb.pojo.entity.Movie;
import com.example.notimdb.pojo.entity.Review;
import com.example.notimdb.pojo.entity.User;
import com.example.notimdb.repository.MovieRepository;
import com.example.notimdb.repository.ReviewRepository;
import com.example.notimdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }
    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable Integer id){
        return reviewRepository.findById(id).orElse(null);
    }

    @PostMapping("/reviews")
    public Review createReview(@RequestBody CreateReviewRequest newReview) {
        Review review = new Review();
        review.setRating(newReview.getRating());
        review.setComment(newReview.getComment());
        User user = userRepository.findById(newReview.getUserId()).orElse(null);
        review.setUser(user);
        Movie movie = movieRepository.findById(newReview.getMovieId()).orElse(null);
        review.setMovie(movie);

        return reviewRepository.save(review);
    }
}
