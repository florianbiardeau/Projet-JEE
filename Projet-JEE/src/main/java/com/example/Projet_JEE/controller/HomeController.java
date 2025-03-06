package com.example.Projet_JEE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Identifiants invalides");
        }
        return "login";
    }

    @GetMapping("/")
    public String home(Principal principal) {
        return principal != null ? "redirect:/dashboard" : "home";
    }

}