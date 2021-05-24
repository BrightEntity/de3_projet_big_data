package de3.vanelle_fiorini.projet_big_data.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultEndpoint {
    @RequestMapping("/")
    public String index() {
        return "Test";
    }
}
