package com.stevekung.springbootdocker.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stevekung.springbootdocker.data.Ingredient;

@RestController
@RequestMapping("/api/v1/ingredient")
public record IngredientController(IngredientService ingredientService)
{
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Ingredient food)
    {
        return this.ingredientService.add(food);
    }

    @GetMapping
    public List<Ingredient> getAll()
    {
        return this.ingredientService.getIngredients();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> getById(@PathVariable(name = "id") Long id)
    {
        return this.ingredientService.getById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<String> getByName(@PathVariable(name = "name") String name)
    {
        return this.ingredientService.getByName(name);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Ingredient>> getByType(@PathVariable(name = "type") String type)
    {
        return this.ingredientService.getByType(type);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<List<String>> updateSimple(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String type)
    {
        return this.ingredientService.updateSimple(id, name, type);
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id)
    {
        return this.ingredientService.deleteById(id);
    }

    @DeleteMapping(path = "/name/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable(name = "name") String name)
    {
        return this.ingredientService.deleteByName(name);
    }

    @DeleteMapping(path = "/type/{type}")
    public ResponseEntity<List<String>> deleteByType(@PathVariable(name = "type") String type)
    {
        return this.ingredientService.deleteByType(type);
    }
}