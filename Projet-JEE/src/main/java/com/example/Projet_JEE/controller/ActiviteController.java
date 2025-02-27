package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.service.ActiviteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/activites")
public class ActiviteController {

    private final ActiviteService activiteService;

    public ActiviteController(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    // Pour l'interface utilisateur
    @GetMapping
    public String showActivites(
            @RequestParam(required = false) String search,
            Model model
    ) {
        if(search != null && !search.isEmpty()) {
            model.addAttribute("activites", activiteService.rechercherActivites(search));
            model.addAttribute("search", search.trim()); // Ajouter explicitement le terme de recherche
        }
        return "activites";
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Activite>> rechercherActivites(@RequestParam String nom) {
        List<Activite> activites = activiteService.rechercherActivites(nom);
        return ResponseEntity.ok(activites);
    }


    // Pour les API REST (optionnel - à garder seulement si nécessaire)
    @GetMapping("/api/recherche")
    @ResponseBody
    public List<Activite> rechercherActivitesApi(@RequestParam String nom) {
        return activiteService.rechercherActivites(nom);
    }
}