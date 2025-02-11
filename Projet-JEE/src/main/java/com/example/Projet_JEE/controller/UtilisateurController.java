package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> inscription(@RequestParam String nomUtilisateur,
                                         @RequestParam String motDePasse,
                                         @RequestParam Integer age,
                                         @RequestParam String genre) {
        try {
            Utilisateur utilisateur = utilisateurService.creerCompte(nomUtilisateur, motDePasse, age, genre);
            return ResponseEntity.ok(utilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestParam String nomUtilisateur, @RequestParam String motDePasse) {
        Optional<Utilisateur> utilisateur = utilisateurService.authentifier(nomUtilisateur, motDePasse);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok("Connexion réussie !");
        } else {
            return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }
}
