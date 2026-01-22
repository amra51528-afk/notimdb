package com.example.notimdb.repository;


import com.example.notimdb.pojo.entity.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository <Review, Integer> {
}
