package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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

            // Calculer la moyenne des notes pour chaque programme
            for (Programme_therapeutique programme : programmes) {
                // Récupère la liste des activités associées au programme
                List<Activite> activites = programme.getActivites();

                // Extraire les IDs des activités et les stocker dans une liste
                List<Long> activitesIds = activites.stream()
                        .map(Activite::getIdActivite) // Supposons que vous avez une méthode getIdActivite()
                        .collect(Collectors.toList());

                // Récupérer les notes mise par l'utilisateur auquel appartient le programme des activités liées au programme
                List<Integer> notesActivites = activiteService.getNotesPourActivitesEtUtilisateur(activitesIds, idUtilisateur);

                // Calculer la moyenne des notes des activités
                String moyenne = "Vous n'avez pas encore donné de note pour les activités de ce programme";
                if (!notesActivites.isEmpty()) {
                    moyenne = String.format("%.2f", notesActivites.stream().mapToInt(Integer::intValue).average().orElse(0));
                }
                programme.setNote(moyenne);
            }

            List<Activite> activitesRecommandees = activiteService.obtenirActiviteParRecommandation(idUtilisateur);

            List<Activite> activites = activiteService.obtenirToutesLesActivites();

            // Ajouter la liste des programmes au modèle
            model.addAttribute("programmes", programmes);
            model.addAttribute("activitesRecommandees", activitesRecommandees);
            model.addAttribute("activites", activites);
        }

        return "dashboard"; // Correspond au template dashboard.html
    }

    @PostMapping("/ajouter-programme")
    public String ajouterProgramme(@RequestParam("nomProgramme") String nomProgramme,
                                   @RequestParam(value = "activitesSelectionnees", required = false) List<Long> activitesSelectionnees) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Programme_therapeutique nouveauProgramme = new Programme_therapeutique();
            nouveauProgramme.setNomProgrammeTherapeutique(nomProgramme);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            Long idUtilisateur = utilisateurService.idParNomUtilisateur(username);
            nouveauProgramme.setUtilisateur(utilisateurService.getUtilisateurById(idUtilisateur));

            // Associer les activités sélectionnées au programme
            if (activitesSelectionnees != null && !activitesSelectionnees.isEmpty()) {
                List<Activite> activites = activiteService.obtenirActivitesParIds(activitesSelectionnees);
                nouveauProgramme.setActivites(activites);
            }

            programmeTherapeutiqueService.ajouterProgramme(nouveauProgramme);
            return "redirect:/dashboard?idUtilisateur=" + idUtilisateur;
        }
        return "redirect:/dashboard";
    }

    @Transactional
    @PostMapping("/supprimer-programme")
    public String supprimerProgramme(@RequestParam Long idProgrammeTherapeutique) {
        programmeTherapeutiqueService.supprimerProgramme(idProgrammeTherapeutique);
        return "redirect:/dashboard";
    }


}