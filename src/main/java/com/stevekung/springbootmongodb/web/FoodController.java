package com.stevekung.springbootmongodb.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stevekung.springbootmongodb.data.dto.FoodDTO;
import com.stevekung.springbootmongodb.template.ControllerTemplate;

@RestController
@RequestMapping("/api/v1/food")
public record FoodController(FoodServiceImpl foodService) implements ControllerTemplate<FoodDTO>
{
    @Override
    @PostMapping
    public ResponseEntity<String> add(@RequestBody FoodDTO object)
    {
        return this.foodService.add(object);
    }

    @Override
    @GetMapping
    public List<FoodDTO> getAll()
    {
        return this.foodService.getAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> getById(@PathVariable(name = "id") String id)
    {
        return this.foodService.getById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<String> getByName(@PathVariable(name = "name") String name)
    {
        return this.foodService.getByName(name);
    }

    @PutMapping(path = "{id}")
    public void updateSimple(@PathVariable(name = "id") String id, @RequestParam(required = false) String name, @RequestParam(required = false) @DateTimeFormat LocalDate expiredDate)
    {
        this.foodService.updateSimple(id, name, expiredDate);
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") String id)
    {
        return this.foodService.deleteById(id);
    }

    @DeleteMapping(path = "/name/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable(name = "name") String name)
    {
        return this.foodService.deleteByName(name);
    }
}