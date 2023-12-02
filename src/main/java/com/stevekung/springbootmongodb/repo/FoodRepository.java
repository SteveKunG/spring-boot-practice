package com.stevekung.springbootmongodb.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stevekung.springbootmongodb.data.Food;

@Repository
public interface FoodRepository extends MongoRepository<Food, String>
{
    Optional<Food> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}