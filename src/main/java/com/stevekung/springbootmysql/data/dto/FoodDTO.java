package com.stevekung.springbootmysql.data.dto;

import java.time.LocalDate;
import java.util.List;

import com.stevekung.springbootmysql.data.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// Data Transfer Object
public class FoodDTO
{
    private Long id;
    private String name;
    private List<Ingredient> ingredient;
    private LocalDate expiredDate;
}