package com.stevekung.springbootpostgresql.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.stevekung.springbootpostgresql.data.Ingredient;
import com.stevekung.springbootpostgresql.data.dto.IngredientDTO;
import com.stevekung.springbootpostgresql.repo.IngredientRepository;
import com.stevekung.springbootpostgresql.template.ServiceTemplate;

@Service
public class IngredientServiceImpl implements ServiceTemplate<IngredientDTO>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientServiceImpl.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<IngredientDTO> getAll()
    {
        var list = this.ingredientRepository.findAll();

        if (list.isEmpty())
        {
            throw new RuntimeException("Ingredient List is empty!");
        }
        return list.stream().map(obj -> this.modelMapper.map(obj, IngredientDTO.class)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getById(Long id)
    {
        var optional = this.ingredientRepository.findById(id);
        return optional.map(ingredient -> new ResponseEntity<>("Ingredient: " + ingredient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("Ingredient ID '" + id + "' does not exist!", HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getByName(String name)
    {
        var optional = this.ingredientRepository.findByName(name);
        return optional.map(ingredient -> new ResponseEntity<>("Ingredient: " + ingredient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("Ingredient Name '" + name + "' does not exist!", HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Ingredient>> getByType(String type)
    {
        var list = this.ingredientRepository.findAllByType(type);

        if (list.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> add(IngredientDTO obj)
    {
        var optional = this.ingredientRepository.findByName(obj.getName());

        if (optional.isPresent())
        {
            return new ResponseEntity<>("Ingredient Name '" + obj.getName() + "' already registered!", HttpStatus.CONFLICT);
        }

        var ingredient = this.modelMapper.map(obj, Ingredient.class);
        this.ingredientRepository.save(ingredient);

        LOGGER.info("Saving Ingredient: {}", ingredient);
        return new ResponseEntity<>("Saving Ingredient: " + ingredient, HttpStatus.OK);
    }

    @Transactional
    public void updateSimple(Long id, IngredientDTO dto)
    {
        var object = this.ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient ID with '" + id + "' does not exist!"));
        var name = dto.getName();
        var type = dto.getType();

        if (StringUtils.hasText(name) && !Objects.equals(object.getName(), name))
        {
            LOGGER.info("Update Name from '{}' to '{}'", object.getName(), name);
            object.setName(name);
        }

        if (StringUtils.hasText(type) && !Objects.equals(object.getType(), type))
        {
            LOGGER.info("Update Type from '{}' to '{}'", object.getType(), type);
            object.setType(type);
        }
    }

    @Override
    @Transactional
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

    @Override
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