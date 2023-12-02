package com.stevekung.springbootmongodb.template;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ServiceTemplate<DTO>
{
    List<DTO> getAll();

    ResponseEntity<String> getById(String id);

    ResponseEntity<String> getByName(String name);

    ResponseEntity<String> add(DTO obj);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteByName(String name);
}