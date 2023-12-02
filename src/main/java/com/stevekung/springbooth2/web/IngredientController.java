package com.stevekung.springbooth2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stevekung.springbooth2.data.Ingredient;
import com.stevekung.springbooth2.data.dto.IngredientDTO;
import com.stevekung.springbooth2.template.ControllerTemplate;

@RestController
@RequestMapping("/api/v1/ingredient")
public record IngredientController(IngredientServiceImpl ingredientService) implements ControllerTemplate<IngredientDTO>
{
    @Override
    @PostMapping
    public ResponseEntity<String> add(@RequestBody IngredientDTO object)
    {
        return this.ingredientService.add(object);
    }

    @Override
    @GetMapping
    public List<IngredientDTO> getAll()
    {
        return this.ingredientService.getAll();
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
    public void updateSimple(@PathVariable(name = "id") Long id, @RequestBody IngredientDTO object)
    {
        this.ingredientService.updateSimple(id, object);
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id)
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