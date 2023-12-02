package com.stevekung.springbootmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootMySQLApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootMySQLApplication.class, args);
    }

    @GetMapping("/")
    public String get()
    {
        return "Muhahaha";
    }
}