package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Programme_therapeutique;

import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class Programme_therapeutiqueController {

    private Programme_therapeutiqueService programmeTherapeutiqueService;

    public Programme_therapeutiqueController(Programme_therapeutiqueService programmeTherapeutiqueService) {
        this.programmeTherapeutiqueService = programmeTherapeutiqueService;
    }

    /*
    @PostMapping("/{idProgramme}/activites")
    public ResponseEntity<Void> ajouterActiviteAuProgramme(@PathVariable Long idProgramme, @RequestBody Long idActivite) {
        programmeTherapeutiqueService.ajouterActiviteAuProgramme(idProgramme, idActivite);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/programme/{id}/ajouter-activites")
    public String ajouterActivitesAuProgramme(@PathVariable("id") Long id, @RequestParam("activitesSelectionnees") List<Long> activitesIds) {
        System.out.println("Requête reçue pour ajouter l'activité " + activitesIds + " au programme " + id);
        programmeTherapeutiqueService.ajouterActivitesAuProgramme(id, activitesIds);
        return "redirect:/programme/" + id; // Redirige vers la page du programme
    }
     */

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
}
