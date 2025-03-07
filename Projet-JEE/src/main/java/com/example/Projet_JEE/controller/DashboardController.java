package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.EvaluationService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername(); // Nom de l'utilisateur

            Long idUtilisateur = utilisateurService.idParNomUtilisateur(username);

            List<Programme_therapeutique> programmes = programmeTherapeutiqueService.obtenirProgrammesParUtilisateur(idUtilisateur);

            for (Programme_therapeutique programme : programmes) {
                List<Activite> activites = programme.getActivites();

                List<Long> activitesIds = activites.stream()
                        .map(Activite::getIdActivite)
                        .collect(Collectors.toList());

                List<Integer> notesActivites = activiteService.getNotesPourActivitesEtUtilisateur(activitesIds, idUtilisateur);

                String moyenne = "Pas de note";
                if (!notesActivites.isEmpty()) {
                    moyenne = String.format("%.2f", notesActivites.stream().mapToInt(Integer::intValue).average().orElse(0));
                }
                programme.setNote(moyenne);
            }

            List<Activite> activitesRecommandees = activiteService.obtenirActivitesParRecommandation(idUtilisateur);
            List<Long> activiteIds = activitesRecommandees.stream()
                    .map(Activite::getIdActivite)
                    .collect(Collectors.toList());

            Map<Long, Double> notesMoyennes = new HashMap<>();
            Map<Long, Integer> nombreAvis = new HashMap<>();

            for (Long activiteId : activiteIds) {
                Double moyenne = evaluationService.findAverageNoteByActiviteId(activiteId);
                Integer nbAvis = evaluationService.countByIdActivite(activiteId);
                notesMoyennes.put(activiteId, moyenne != null ? moyenne : 0.0);
                nombreAvis.put(activiteId, nbAvis != null ? nbAvis : 0);
            }

            List<Activite> activites = activiteService.obtenirToutesLesActivites();

            model.addAttribute("programmes", programmes);
            model.addAttribute("activitesRecommandees", activitesRecommandees);
            model.addAttribute("notesMoyennes", notesMoyennes);
            model.addAttribute("nombreAvis", nombreAvis);
            model.addAttribute("activites", activites);

            model.addAttribute("activePage", "dashboard");
        }

        return "dashboard";
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
        List<Long> listActiviteId = Collections.singletonList(data.get("activiteId"));
        Long programmeId = data.get("programmeId");

        programmeTherapeutiqueService.ajouterActivitesAuProgramme(programmeId, listActiviteId);

        return ResponseEntity.ok("Activité ajoutée au programme");
    }
}