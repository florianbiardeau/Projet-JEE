package com.example.Projet_JEE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/accueil")
    public String accueil(Model model) {
        
        model.addAttribute("message", "Bienvenue  !");
        return "accueil"; // correspondra à accueil.html dans resources/templates
    }
}
