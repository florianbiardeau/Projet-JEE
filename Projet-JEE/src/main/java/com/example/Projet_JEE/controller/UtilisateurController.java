package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.dto.ConnexionDTO;
import com.example.Projet_JEE.dto.UtilisateurDTO;
import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
            // Vérification de l'existence du nom d'utilisateur
            if (utilisateurService.nomUtilisateurExists(utilisateurDTO.getNomUtilisateur())) {
                return ResponseEntity.badRequest().body("Ce nom d'utilisateur existe déjà");
            }

            // Création de l'utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
            utilisateur.setMotDePasse(utilisateurDTO.getMotDePasse());
            utilisateur.setAge(utilisateurDTO.getAge());
            utilisateur.setGenre(utilisateurDTO.getGenre());

            Utilisateur nouvelUtilisateur = utilisateurService.creerUtilisateur(utilisateur);

            return ResponseEntity.ok(nouvelUtilisateur);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la création du compte");
        }
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestBody ConnexionDTO connexionDTO) {
        Optional<Utilisateur> utilisateur = utilisateurService.authentifier(
                connexionDTO.getNomUtilisateur(),
                connexionDTO.getMotDePasse()
        );

        if (utilisateur.isPresent()) {
            // Crée une authentification Spring Security
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    utilisateur.get().getNomUtilisateur(),
                    null,
                    // Ajoutez les rôles/authorities si nécessaire
                    Collections.emptyList()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, "JSESSIONID=...").body("Connecté");
        }
        else {
            return ResponseEntity.status(401).body("Échec de la connexion");
        }
    }

    @PostMapping("/deconnexion")
    public ResponseEntity<?> deconnexion() {
        // Logique de déconnexion à implémenter selon votre gestion de session
        return ResponseEntity.ok("Déconnexion réussie !");
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateurDTO", new UtilisateurDTO());
        return "register";
    }
}