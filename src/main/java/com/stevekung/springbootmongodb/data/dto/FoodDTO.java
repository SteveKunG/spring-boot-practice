package com.stevekung.springbootmongodb.data.dto;

import java.time.LocalDate;

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
    private String id;
    private String name;
    private LocalDate expiredDate;
}