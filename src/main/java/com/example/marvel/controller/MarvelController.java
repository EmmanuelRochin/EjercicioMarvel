package com.example.marvel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarvelController {


    @GetMapping("/characters")
    public String obtenerPersonaje() {
        return "personajes";
    }


}
