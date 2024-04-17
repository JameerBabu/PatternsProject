package com.example.dms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.dms.models.Dog;

public interface DogRepository extends CrudRepository<Dog,Integer>{
  List<Dog> findByName(String name);
}
