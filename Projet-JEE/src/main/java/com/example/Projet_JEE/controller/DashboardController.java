package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.EvaluationService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ui.Model;


@Controller
public class DashboardController {

    private final Programme_therapeutiqueService programmeTherapeutiqueService;
    private final UtilisateurService utilisateurService;
    private final ActiviteService activiteService;
    private final EvaluationService evaluationService;

    public DashboardController(Programme_therapeutiqueService programmeTherapeutiqueService, UtilisateurService utilisateurService, ActiviteService activiteService, EvaluationService evaluationService) {
        this.programmeTherapeutiqueService = programmeTherapeutiqueService;
        this.utilisateurService = utilisateurService;
        this.activiteService = activiteService;
        this.evaluationService = evaluationService;
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
                String moyenne = "Pas de note";
                if (!notesActivites.isEmpty()) {
                    moyenne = String.format("%.2f", notesActivites.stream().mapToInt(Integer::intValue).average().orElse(0));
                }
                programme.setNote(moyenne);
            }

            List<Activite> activitesRecommandees = activiteService.obtenirActiviteParRecommandation(idUtilisateur);
            List<Long> activiteIds = activitesRecommandees.stream()
                    .map(Activite::getIdActivite)
                    .collect(Collectors.toList());

            // Calcul des moyennes et nombre d'avis pour CHAQUE activité
            Map<Long, Double> notesMoyennes = new HashMap<>();
            Map<Long, Integer> nombreAvis = new HashMap<>();

            for (Long activiteId : activiteIds) {
                Double moyenne = evaluationService.findAverageNoteByActiviteId(activiteId);
                Integer nbAvis = evaluationService.countByIdActivite(activiteId);
                notesMoyennes.put(activiteId, moyenne != null ? moyenne : 0.0);
                nombreAvis.put(activiteId, nbAvis != null ? nbAvis : 0);
            }

            List<Activite> activites = activiteService.obtenirToutesLesActivites();

            // Ajouter la liste des programmes au modèle
            model.addAttribute("programmes", programmes);
            model.addAttribute("activitesRecommandees", activitesRecommandees);
            model.addAttribute("notesMoyennes", notesMoyennes);
            model.addAttribute("nombreAvis", nombreAvis);
            model.addAttribute("activites", activites);

            model.addAttribute("activePage", "dashboard");
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

    @PostMapping("/ajouter-activite-au-programme")
    @ResponseBody
    public ResponseEntity<String> ajouterActiviteAuProgramme(@RequestBody Map<String, Long> data) {
        System.out.println("ActiviteID " + data.get("activiteId"));
        System.out.println("programmeId " + data.get("programmeId"));
        List<Long> listActiviteId = Collections.singletonList(data.get("activiteId"));
        Long programmeId = data.get("programmeId");

        programmeTherapeutiqueService.ajouterActivitesAuProgramme(programmeId, listActiviteId);

        return ResponseEntity.ok("Activité ajoutée au programme");
    }
}