package com.stevekung.springbootdocker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stevekung.springbootdocker.data.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>
{
    List<Ingredient> findAllByType(String type);

    Optional<Ingredient> findByName(String name);

    Optional<Ingredient> findByType(String type);

    boolean existsByName(String name);

    boolean existsByType(String type);

    void deleteByName(String name);

    void deleteByType(String type);
}