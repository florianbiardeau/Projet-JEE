package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Pathologie;
import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.service.PathologieService;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    private final UtilisateurService utilisateurService;
    private final PathologieService pathologieService;
    private Long idUtilisateur;

    public ProfilController(UtilisateurService utilisateurService, PathologieService  pathologieService) {
        this.utilisateurService = utilisateurService;
        this.pathologieService = pathologieService;
    }

    @GetMapping
    public String showProfil(Model model, Authentication authentication) {
        Long idUtilisateur = utilisateurService.idParNomUtilisateur(authentication.getName());
        this.idUtilisateur = idUtilisateur;
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);

        // Récupérer les pathologies associées à l'utilisateur
        List<Pathologie> pathologies = pathologieService.getPathologiesForUser(idUtilisateur);
        model.addAttribute("pathologies", pathologies);

        // Récupérer toutes les pathologies disponibles pour alimenter le select dans le modal "ajouter"
        List<Pathologie> toutesPathologies = pathologieService.getAllPathologies();

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("toutesPathologies", toutesPathologies);

        model.addAttribute("activePage", "profil");

        return "profil"; // renvoie à profil.html
    }

    @PostMapping("/ajouter")
    public String ajouterPathologie(@RequestParam("pathologieId") Long pathologieId) {
        pathologieService.ajouterPathologiePourUtilisateur(idUtilisateur, pathologieId);
        return "redirect:/profil";
    }

    @PostMapping("/supprimer")
    public String supprimerPathologie(@RequestParam("pathologieId") Long pathologieId) {
        pathologieService.supprimerPathologiePourUtilisateur(idUtilisateur, pathologieId);
        return "redirect:/profil";
    }

    // Traitement de la modification
    @PostMapping("/modifier")
    public String updateProfil(
            @RequestParam String nomUtilisateur,
            @RequestParam(required = false) Integer age,
            @RequestParam String genre,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        try {
            // Récupérer l'utilisateur connecté
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);

            // Mettre à jour les informations
            utilisateur.setNomUtilisateur(nomUtilisateur);
            utilisateur.setAge(age);
            utilisateur.setGenre(genre);

            // Sauvegarder
            utilisateurService.sauvegarder(utilisateur);

            redirectAttributes.addFlashAttribute("success", "Profil mis à jour avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour : " + e.getMessage());
        }

        return "redirect:/profil";
    }
}
