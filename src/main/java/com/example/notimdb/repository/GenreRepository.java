package com.example.notimdb.repository;


import com.example.notimdb.pojo.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository <Genre, Integer> {
}
