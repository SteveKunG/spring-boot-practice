package com.stevekung.springbootpostgresql.web;

import java.time.LocalDate;
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

import com.stevekung.springbootpostgresql.data.Food;
import com.stevekung.springbootpostgresql.repo.FoodRepository;

@Service
public class FoodService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getFoods()
    {
        var list = this.foodRepository.findAll();

        if (list.isEmpty())
        {
            throw new RuntimeException("Food List is empty!");
        }
        return list;
    }

    public ResponseEntity<String> getById(Long id)
    {
        var optional = this.foodRepository.findById(id);

        if (!optional.isPresent())
        {
            return new ResponseEntity<>("Food ID '" + id + "' does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Food: " + optional.get().toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> getByName(String name)
    {
        var optional = this.foodRepository.findByName(name);

        if (!optional.isPresent())
        {
            return new ResponseEntity<>("Food Name '" + name + "' does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Food: " + optional.get().toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> add(Food obj)
    {
        var optional = this.foodRepository.findByName(obj.getName());

        if (optional.isPresent())
        {
            return new ResponseEntity<>("Food Name '" + obj.getName() + "' already registered!", HttpStatus.CONFLICT);
        }

        this.foodRepository.save(obj);

        LOGGER.info("Saving Food: {}", obj);
        return new ResponseEntity<>("Saving Food: " + obj.toString(), HttpStatus.OK);
    }

    @Transactional
    public void updateSimple(Long id, String name, LocalDate expiredDate)
    {
        var object = this.foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food ID with '" + id + "' does not exist!"));

        if (StringUtils.hasText(name) && !Objects.equals(object.getName(), name))
        {
            LOGGER.info("Update Name from '{}' to '{}'", object.getName(), name);
            object.setName(name);
        }

        if (Objects.nonNull(expiredDate) && !expiredDate.isEqual(object.getExpiredDate()))
        {
            LOGGER.info("Update Expired Date from '{}' to '{}'", object.getExpiredDate(), expiredDate);
            object.setExpiredDate(expiredDate);
        }
    }

    public ResponseEntity<String> deleteById(Long id)
    {
        if (!this.foodRepository.existsById(id))
        {
            return new ResponseEntity<>("Food with ID '" + id + "' does not exist!", HttpStatus.NOT_FOUND);
        }

        this.foodRepository.deleteById(id);

        LOGGER.info("Deleting Food by ID: {}", id);
        return new ResponseEntity<>("Deleting Food by ID: " + id, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteByName(String name)
    {
        if (!this.foodRepository.existsByName(name))
        {
            return new ResponseEntity<>("Food with name '" + name + "' does not exist!", HttpStatus.NOT_FOUND);
        }

        this.foodRepository.deleteByName(name);

        LOGGER.info("Deleting Food by Name: {}", name);
        return new ResponseEntity<>("Deleting Food by Name: " + name, HttpStatus.OK);
    }
}