package com.stevekung.springbooth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootH2Application
{
    // Driver Class : org.h2.Driver
    // Database : jdbc:h2:mem:food
    // Web : http://localhost:8080/h2-console/
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootH2Application.class, args);
    }

    @GetMapping("/")
    public String get()
    {
        return "Muhahaha";
    }
}