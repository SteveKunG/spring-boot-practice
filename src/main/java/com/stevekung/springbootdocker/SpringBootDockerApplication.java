package com.stevekung.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDockerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootDockerApplication.class, args);
    }

    @GetMapping("/muhahaha")
    public String get()
    {
        return "Muhahaha";
    }
}