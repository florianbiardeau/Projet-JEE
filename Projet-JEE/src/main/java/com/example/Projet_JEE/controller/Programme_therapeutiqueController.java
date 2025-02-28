package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;

import com.example.Projet_JEE.repository.Evaluation_activiteRepository;
import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.EvaluationService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Evaluation_activiteRepository evaluationRepository;
    private EvaluationService evaluationService;


    public Programme_therapeutiqueController(Programme_therapeutiqueService programmeTherapeutiqueService, ActiviteService activiteService, Evaluation_activiteRepository evaluationRepository, EvaluationService evaluationService) {
        this.programmeTherapeutiqueService = programmeTherapeutiqueService;
        this.activiteService = activiteService;
        this.evaluationRepository = evaluationRepository;
        this.evaluationService = evaluationService;
    }

    @PostMapping("/programme/{id}/ajouter-activites")
    public String ajouterActivitesAuProgramme(@PathVariable("id") Long id, @RequestParam("activitesSelectionnees") List<Long> activitesIds) {
        System.out.println("Requête reçue pour ajouter l'activité " + activitesIds + " au programme " + id);
        programmeTherapeutiqueService.ajouterActivitesAuProgramme(id, activitesIds);
        return "redirect:/programme/" + id; // Redirige vers la page du programme
    }

    @Transactional
    @PostMapping("/programme/{id}/supprimer-activite")
    public String supprimerActiviteDansProgramme(@PathVariable("id") Long id, @RequestParam("idActivite") Long idActivite) {
        System.out.println("Requête reçue pour supprimer l'activité " + idActivite + " du programme " + id);
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

        // Récupérer l'id utilisateur
        Long idUtilisateur = programmeTherapeutiqueService.obtenirIdUtilisateurParIdProgramme(id);
        // Récupérer toutes les activités liées au programme
        List<Long> activitesIds = programmeTherapeutiqueService.obtenirActivitesParProgramme(id);
        // Récupérer les notes mise par l'utilisateur auquel appartient le programme des activités liées au programme
        List<Integer> notesActivites = activiteService.getNotesPourActivitesEtUtilisateur(activitesIds, idUtilisateur);

        // Calcul des moyennes et nombre d'avis pour CHAQUE activité
        Map<Long, Double> notesMoyennes = new HashMap<>();
        Map<Long, Integer> nombreAvis = new HashMap<>();
        Map<Long, Integer> noteAvis = new HashMap<>();

        //Integer note = evaluationRepository.findNoteByActiviteIdAndUtilisateurId(id, idUtilisateur);

        for (Long activiteId : activitesIds) {
            Double moyenne = evaluationRepository.findAverageNoteByActiviteId(activiteId);
            Integer nbAvis = evaluationRepository.countByIdActivite(activiteId);
            Integer note = evaluationRepository.findNoteByActiviteIdAndUtilisateurId(activiteId, idUtilisateur);
            notesMoyennes.put(activiteId, moyenne != null ? moyenne : 0.0);
            nombreAvis.put(activiteId, nbAvis != null ? nbAvis : 0);
            noteAvis.put(activiteId, note != null ? note : 0);
        }

        // Calculer la moyenne des notes des activités
        String moyenne = "Vous n'avez pas encore donné de note pour les activités de ce programme";
        if (!notesActivites.isEmpty()) {
            moyenne = String.format("%.2f", notesActivites.stream().mapToInt(Integer::intValue).average().orElse(0));
        }

        List<Activite> activitesDisponibles = activiteService.obtenirToutesLesActivitesSaufDejaDansProgramme(id);

        if (programme == null) {
            return "redirect:/dashboard"; // Redirige si l'ID est invalide
        }
        System.out.println("kk");
        System.out.println(noteAvis);
        model.addAttribute("note", noteAvis);
        model.addAttribute("programme", programme);
        model.addAttribute("moyenneNote", moyenne);
        model.addAttribute("activitesDisponibles", activitesDisponibles);
        model.addAttribute("notesMoyennes", notesMoyennes);
        model.addAttribute("nombreAvis", nombreAvis);
        return "programme"; // Correspond à programme-details.html
    }
}
