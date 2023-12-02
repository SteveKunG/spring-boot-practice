package com.stevekung.springbootpostgresql.template;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ServiceTemplate<DTO>
{
    List<DTO> getAll();

    ResponseEntity<String> getById(Long id);

    ResponseEntity<String> getByName(String name);

    ResponseEntity<String> add(DTO obj);

    ResponseEntity<String> deleteById(Long id);

    ResponseEntity<String> deleteByName(String name);
}