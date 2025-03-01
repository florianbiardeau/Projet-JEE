package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Pathologie;
import com.example.Projet_JEE.service.PathologieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pathologies")
public class PathologieController {

    private final PathologieService pathologieService;

    public PathologieController(PathologieService pathologieService) {
        this.pathologieService = pathologieService;
    }

    @GetMapping
    public String showPathologies(Model model) {
        // Ici, on simule l'utilisateur connecté avec l'id 1L.
        // Remplacez ceci par l'obtention dynamique via votre SecurityContext.
        Long idUtilisateur = 1L;

        // Récupérer les pathologies associées à l'utilisateur
        List<Pathologie> pathologies = pathologieService.getPathologiesForUser(idUtilisateur);
        model.addAttribute("pathologies", pathologies);

        // Récupérer toutes les pathologies disponibles pour alimenter le select dans le modal "ajouter"
        List<Pathologie> toutesPathologies = pathologieService.getAllPathologies();
        model.addAttribute("toutesPathologies", toutesPathologies);

        model.addAttribute("activePage", "pathologies");

        return "pathologies"; // renvoie à pathologies.html
    }

    @PostMapping("/ajouter")
    public String ajouterPathologie(@RequestParam("pathologieId") Long pathologieId) {
        Long idUtilisateur = 1L; // Remplacer par l'utilisateur connecté
        pathologieService.ajouterPathologiePourUtilisateur(idUtilisateur, pathologieId);
        return "redirect:/pathologies";
    }

    @PostMapping("/supprimer")
    public String supprimerPathologie(@RequestParam("pathologieId") Long pathologieId) {
        Long idUtilisateur = 1L; // Remplacer par l'utilisateur connecté
        pathologieService.supprimerPathologiePourUtilisateur(idUtilisateur, pathologieId);
        return "redirect:/pathologies";
    }
}
