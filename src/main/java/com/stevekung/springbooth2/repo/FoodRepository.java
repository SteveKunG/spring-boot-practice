package com.stevekung.springbooth2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stevekung.springbooth2.data.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>
{
    Optional<Food> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}