package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.dto.UtilisateurDTO;
import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EnregistrementController {

    private final UtilisateurService utilisateurService;

    public EnregistrementController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateurDTO", new UtilisateurDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String nomUtilisateur,
            @RequestParam String motDePasse,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String genre,
            RedirectAttributes redirectAttributes) {

        if (utilisateurService.nomUtilisateurExists(nomUtilisateur)) {
            redirectAttributes.addFlashAttribute("error", "Ce nom d'utilisateur existe déjà");
            return "redirect:/register";
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(nomUtilisateur);
        utilisateur.setPassword(motDePasse);
        utilisateur.setAge(age);
        utilisateur.setGenre(genre);

        utilisateurService.creerUtilisateur(utilisateur);

        redirectAttributes.addFlashAttribute("success", "Compte créé avec succès !");
        return "redirect:/login";
    }

}