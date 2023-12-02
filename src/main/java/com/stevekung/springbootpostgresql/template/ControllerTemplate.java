package com.stevekung.springbootpostgresql.template;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ControllerTemplate<DTO>
{
    ResponseEntity<String> add(@RequestBody DTO object);

    List<DTO> getAll();
}