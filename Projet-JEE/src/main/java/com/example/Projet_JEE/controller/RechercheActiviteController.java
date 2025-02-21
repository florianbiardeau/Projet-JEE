package com.example.Projet_JEE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RechercheActiviteController {

    @GetMapping("/rechercheActivite")
    public String index(Model model) {
        return "rechercheActivite"; // correspondra à index.html dans resources/templates
    }
}
