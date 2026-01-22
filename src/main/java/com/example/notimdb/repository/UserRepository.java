package com.example.notimdb.repository;


import com.example.notimdb.pojo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer> {
}
