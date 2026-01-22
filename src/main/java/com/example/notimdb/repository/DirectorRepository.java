package com.example.notimdb.repository;


import com.example.notimdb.pojo.entity.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository <Director, Integer> {
}

