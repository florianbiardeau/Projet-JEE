package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;

import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.EvaluationService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Programme_therapeutiqueController {

    private Programme_therapeutiqueService programmeTherapeutiqueService;
    private ActiviteService activiteService;
    private EvaluationService evaluationService;


    public Programme_therapeutiqueController(Programme_therapeutiqueService programmeTherapeutiqueService, ActiviteService activiteService, EvaluationService evaluationService) {
        this.programmeTherapeutiqueService = programmeTherapeutiqueService;
        this.activiteService = activiteService;
        this.evaluationService = evaluationService;
    }

    @PostMapping("/programme/{id}/ajouter-activites")
    public String ajouterActivitesAuProgramme(@PathVariable("id") Long id, @RequestParam("activitesSelectionnees") List<Long> activitesIds) {
        try {
            programmeTherapeutiqueService.ajouterActivitesAuProgramme(id, activitesIds);
        } catch (Exception e) {

        }
        return "redirect:/programme/" + id;
    }

    @Transactional
    @PostMapping("/programme/{id}/supprimer-activite")
    public String supprimerActiviteDansProgramme(@PathVariable("id") Long id, @RequestParam("idActivite") Long idActivite) {
        programmeTherapeutiqueService.supprimerActiviteDuProgramme(id, idActivite);
        return "redirect:/programme/" + id;
    }

    @PostMapping("/programme/{id}/modifier-nom")
    @ResponseBody
    public ResponseEntity<String> modifierNomProgramme(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Programme_therapeutique programme = programmeTherapeutiqueService.obtenirProgrammeParId(id);

        programme.setNomProgrammeTherapeutique(request.get("nomProgramme"));
        programmeTherapeutiqueService.sauvegarder(programme);
        return ResponseEntity.ok("Nom mis à jour !");
    }

    @GetMapping("/programme/{id}")
    public String afficherDetailsProgramme(@PathVariable("id") Long id, Model model) {
        Programme_therapeutique programme = programmeTherapeutiqueService.obtenirProgrammeParId(id);

        Long idUtilisateur = programmeTherapeutiqueService.obtenirIdUtilisateurParIdProgramme(id);
        List<Long> activitesIds = programmeTherapeutiqueService.obtenirActivitesParProgramme(id);
        List<Integer> notesActivites = activiteService.getNotesPourActivitesEtUtilisateur(activitesIds, idUtilisateur);

        Map<Long, Double> notesMoyennes = new HashMap<>();
        Map<Long, Integer> nombreAvis = new HashMap<>();
        Map<Long, Integer> noteAvis = new HashMap<>();

        for (Long activiteId : activitesIds) {
            Double moyenne = evaluationService.findAverageNoteByActiviteId(activiteId);
            Integer nbAvis = evaluationService.countByIdActivite(activiteId);
            Integer note = evaluationService.findNoteByActiviteIdAndUtilisateurId(activiteId, idUtilisateur);
            notesMoyennes.put(activiteId, moyenne != null ? moyenne : 0.0);
            nombreAvis.put(activiteId, nbAvis != null ? nbAvis : 0);
            noteAvis.put(activiteId, note != null ? note : 0);
        }

        String moyenne = "Vous n'avez pas encore donné de note pour les activités de ce programme";
        if (!notesActivites.isEmpty()) {
            moyenne = String.format("%.2f", notesActivites.stream().mapToInt(Integer::intValue).average().orElse(0));
        }

        List<Activite> activitesDisponibles = activiteService.obtenirToutesLesActivitesSaufDejaDansProgramme(id);

        if (programme == null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("note", noteAvis);
        model.addAttribute("programme", programme);
        model.addAttribute("moyenneNote", moyenne);
        model.addAttribute("activitesDisponibles", activitesDisponibles);
        model.addAttribute("notesMoyennes", notesMoyennes);
        model.addAttribute("nombreAvis", nombreAvis);

        model.addAttribute("activePage", "dashboard");

        return "programme";
    }
}
