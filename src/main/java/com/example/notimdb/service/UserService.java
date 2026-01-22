package com.example.notimdb.service;

import com.example.notimdb.pojo.dto.CreateReviewFromUser;
import com.example.notimdb.pojo.dto.CreateReviewRequest;
import com.example.notimdb.pojo.dto.MovieUpdateRequest;
import com.example.notimdb.pojo.entity.Movie;
import com.example.notimdb.pojo.entity.Review;
import com.example.notimdb.pojo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    User addUser(User user);
    Movie addMovieReview(Integer id, CreateReviewFromUser newReview);

    void deleteUser(Integer id);

    // User updateUser(Integer id, UserUpdateRequest request);
}

