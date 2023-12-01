package com.stevekung.springbootpostgresql.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.stevekung.springbootpostgresql.data.Ingredient;
import com.stevekung.springbootpostgresql.repo.IngredientRepository;

@Service
public class IngredientService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredients()
    {
        var list = this.ingredientRepository.findAll();

        if (list.isEmpty())
        {
            throw new RuntimeException("Ingredient List is empty!");
        }
        return list;
    }

    public ResponseEntity<String> getById(Long id)
    {
        var optional = this.ingredientRepository.findById(id);

        if (!optional.isPresent())
        {
            return new ResponseEntity<>("Ingredient ID '" + id + "' does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Ingredient: " + optional.get().toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> getByName(String name)
    {
        var optional = this.ingredientRepository.findByName(name);

        if (!optional.isPresent())
        {
            return new ResponseEntity<>("Ingredient Name '" + name + "' does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Ingredient: " + optional.get().toString(), HttpStatus.OK);
    }

    public ResponseEntity<List<Ingredient>> getByType(String type)
    {
        var list = this.ingredientRepository.findAllByType(type);

        if (list.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<String> add(Ingredient obj)
    {
        var optional = this.ingredientRepository.findByName(obj.getName());

        if (optional.isPresent())
        {
            return new ResponseEntity<>("Ingredient Name '" + obj.getName() + "' already registered!", HttpStatus.CONFLICT);
        }

        this.ingredientRepository.save(obj);

        LOGGER.info("Saving Ingredient: {}", obj);
        return new ResponseEntity<>("Saving Ingredient: " + obj.toString(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<List<String>> updateSimple(Long id, String name, String type)
    {
        var list = new ArrayList<String>();
        var object = this.ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient ID with '" + id + "' does not exist!"));

        if (StringUtils.hasText(name) && !Objects.equals(object.getName(), name))
        {
            LOGGER.info("Update Name from '{}' to '{}'", object.getName(), name);
            list.add("Update Name from '%s' to '%s'".formatted(object.getName(), name));
            object.setName(name);
        }

        if (StringUtils.hasText(type) && !Objects.equals(object.getType(), type))
        {
            LOGGER.info("Update Type from '{}' to '{}'", object.getType(), type);
            list.add("Update Type from '%s' to '%s'".formatted(object.getType(), type));
            object.setType(type);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(Long id)
    {
        if (!this.ingredientRepository.existsById(id))
        {
            return new ResponseEntity<>("Ingredient with ID '" + id + "' does not exist!", HttpStatus.NOT_FOUND);
        }

        this.ingredientRepository.deleteById(id);

        LOGGER.info("Deleting Ingredient by ID: {}", id);
        return new ResponseEntity<>("Deleting Ingredient by ID: " + id, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteByName(String name)
    {
        if (!this.ingredientRepository.existsByName(name))
        {
            return new ResponseEntity<>("Ingredient with name '" + name + "' does not exist!", HttpStatus.NOT_FOUND);
        }

        this.ingredientRepository.deleteByName(name);

        LOGGER.info("Deleting Ingredient by Name: {}", name);
        return new ResponseEntity<>("Deleting Ingredient by Name: " + name, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<List<String>> deleteByType(String type)
    {
        if (!this.ingredientRepository.existsByType(type))
        {
            return new ResponseEntity<>(List.of("Ingredient with type '" + type + "' does not exist!"), HttpStatus.NOT_FOUND);
        }

        var deletedList = new ArrayList<String>();

        for (var ingredient : this.ingredientRepository.findAllByType(type))
        {
            this.ingredientRepository.deleteByName(ingredient.getName());
            deletedList.add("Type '%s' with Name '%s' has been deleted".formatted(type, ingredient.getName()));
            LOGGER.info("Deleting Ingredient by Type with Name: {}, {}", type, ingredient.getName());
        }
        return new ResponseEntity<>(deletedList, HttpStatus.OK);
    }
}