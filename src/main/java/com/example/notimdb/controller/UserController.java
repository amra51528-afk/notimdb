package com.example.notimdb.controller;

import com.example.notimdb.pojo.dto.CreateReviewFromUser;
import com.example.notimdb.pojo.dto.CreateReviewRequest;
import com.example.notimdb.pojo.dto.CreateUserRequest;
import com.example.notimdb.pojo.entity.Movie;
import com.example.notimdb.pojo.entity.Review;
import com.example.notimdb.pojo.entity.User;
import com.example.notimdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest newUserRequest) {
        User user = new User();
        user.setUsername(newUserRequest.getUsername());
        user.setPassword(newUserRequest.getPassword()); // Note: In a real-world application, use secure password hashing

        return userService.addUser(user);
    }
    @PostMapping("/users/movie/{id}")
    public Movie addMovieReview(@PathVariable Integer id, @RequestBody CreateReviewFromUser newReview) {

        return userService.addMovieReview(id,newReview);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
