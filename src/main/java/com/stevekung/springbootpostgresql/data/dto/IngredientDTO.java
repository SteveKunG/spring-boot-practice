package com.stevekung.springbootpostgresql.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// Data Transfer Object
public class IngredientDTO
{
    private Long id;
    private String name;
    private String type;
}