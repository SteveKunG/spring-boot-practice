package com.stevekung.springbootdocker.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stevekung.springbootdocker.data.Food;

@RestController
@RequestMapping("/api/v1/food")
public record FoodController(FoodService foodService)
{
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Food food)
    {
        return this.foodService.add(food);
    }

    @GetMapping
    public List<Food> getAll()
    {
        return this.foodService.getFoods();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> getById(@PathVariable(name = "id") Long id)
    {
        return this.foodService.getById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<String> getByName(@PathVariable(name = "name") String name)
    {
        return this.foodService.getByName(name);
    }

    @PutMapping(path = "{id}")
    public void updateSimple(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) @DateTimeFormat LocalDate expiredDate)
    {
        this.foodService.updateSimple(id, name, expiredDate);
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id)
    {
        return this.foodService.deleteById(id);
    }

    @DeleteMapping(path = "/name/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable(name = "name") String name)
    {
        return this.foodService.deleteByName(name);
    }
}