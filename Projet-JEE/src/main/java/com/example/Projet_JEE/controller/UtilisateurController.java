package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.dto.ConnexionDTO;
import com.example.Projet_JEE.dto.UtilisateurDTO;
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
    public ResponseEntity<?> inscription(@RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            Utilisateur utilisateur = utilisateurService.creerCompte(
                    utilisateurDTO.getNomUtilisateur(),
                    utilisateurDTO.getMotDePasse(),
                    utilisateurDTO.getAge(),
                    utilisateurDTO.getGenre()
            );
            return ResponseEntity.ok(utilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestBody ConnexionDTO connexionDTO) {
        Optional<Utilisateur> utilisateur = utilisateurService.authentifier(
                connexionDTO.getNomUtilisateur(),
                connexionDTO.getMotDePasse()
        );

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok("Connexion réussie !");
        } else {
            return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    @PostMapping("/deconnexion")
    public ResponseEntity<?> deconnexion() {
        return ResponseEntity.ok("Déconnexion réussie !");
    }
}
