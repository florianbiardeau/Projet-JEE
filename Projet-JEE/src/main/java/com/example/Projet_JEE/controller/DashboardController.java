package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import org.springframework.ui.Model;


@Controller
public class DashboardController {

    private final Programme_therapeutiqueService programmeTherapeutiqueService;
    private final UtilisateurService utilisateurService;
    private final ActiviteService activiteService;

    public DashboardController(Programme_therapeutiqueService programmeTherapeutiqueService, UtilisateurService utilisateurService, ActiviteService activiteService) {
        this.programmeTherapeutiqueService = programmeTherapeutiqueService;
        this.utilisateurService = utilisateurService;
        this.activiteService = activiteService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Obtenir l'authentification en cours
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername(); // Nom de l'utilisateur

            Long idUtilisateur = utilisateurService.idParNomUtilisateur(username);

            // Récupérer la liste des programmes depuis le service
            List<Programme_therapeutique> programmes = programmeTherapeutiqueService.obtenirProgrammesParUtilisateur(idUtilisateur);

            // Récupérer la liste des programmes depuis le service
            List<Activite> activitesRecommandees = activiteService.obtenirActiviteParRecommandation(idUtilisateur);

            // Ajouter la liste des programmes au modèle
            model.addAttribute("programmes", programmes);
            model.addAttribute("activitesRecommandees", activitesRecommandees);
        }

        return "dashboard"; // Correspond au template dashboard.html
    }

    @PostMapping("/ajouter-programme")
    public String ajouterProgramme(@RequestParam("nomProgramme") String nomProgramme) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Programme_therapeutique nouveauProgramme = new Programme_therapeutique();
            nouveauProgramme.setNomProgrammeTherapeutique(nomProgramme);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername(); // Nom de l'utilisateur

            Long idUtilisateur = utilisateurService.idParNomUtilisateur(username);
            nouveauProgramme.setUtilisateur(utilisateurService.getUtilisateurById(idUtilisateur));

            programmeTherapeutiqueService.ajouterProgramme(nouveauProgramme);
            return "redirect:/dashboard?idUtilisateur=" + idUtilisateur;
        }
        return null;
    }

    @GetMapping("/programme/{id}")
    public String afficherDetailsProgramme(@PathVariable("id") Long id, Model model) {
        Programme_therapeutique programme = programmeTherapeutiqueService.obtenirProgrammeParId(id);

        if (programme == null) {
            return "redirect:/dashboard"; // Redirige si l'ID est invalide
        }

        model.addAttribute("programme", programme);
        return "programme"; // Correspond à programme-details.html
    }
}