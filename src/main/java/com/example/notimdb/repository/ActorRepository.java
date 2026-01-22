package com.example.notimdb.repository;


import com.example.notimdb.pojo.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository <Actor, Integer> {
}
