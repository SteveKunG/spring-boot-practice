package com.stevekung.springbootmongodb.data;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "food")
public class Food
{
    @Id
    private String id;

    private String name;

    private LocalDate expiredDate;
}