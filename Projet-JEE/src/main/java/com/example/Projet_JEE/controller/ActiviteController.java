package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.service.ActiviteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activites")
public class ActiviteController {

    private final ActiviteService activiteService;

    public ActiviteController(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Activite>> rechercherActivites(@RequestParam String nom) {
        List<Activite> activites = activiteService.rechercherActivites(nom);
        return ResponseEntity.ok(activites);
    }
}
