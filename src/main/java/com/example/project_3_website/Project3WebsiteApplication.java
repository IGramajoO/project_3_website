package com.example.project_3_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class Project3WebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project3WebsiteApplication.class, args);
    }

}
