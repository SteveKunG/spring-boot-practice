package com.stevekung.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootMongoDBApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootMongoDBApplication.class, args);
    }

    @GetMapping("/")
    public String get()
    {
        return "Muhahaha";
    }
}