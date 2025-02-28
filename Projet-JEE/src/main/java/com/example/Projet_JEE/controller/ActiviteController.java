package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.repository.Evaluation_activiteRepository;
import com.example.Projet_JEE.service.ActiviteService;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/activites")
public class ActiviteController {

    private final ActiviteService activiteService;
    private final Evaluation_activiteRepository evaluationRepository;
    private final Programme_therapeutiqueService programmeTherapeutiqueService;
    private final UtilisateurService utilisateurService;


    public ActiviteController(ActiviteService activiteService, Evaluation_activiteRepository evaluationRepository, Programme_therapeutiqueService programmeTherapeutiqueService, UtilisateurService utilisateurService) {
        this.activiteService = activiteService;
        this.evaluationRepository = evaluationRepository;
        this.programmeTherapeutiqueService = programmeTherapeutiqueService;
        this.utilisateurService = utilisateurService;
    }

//    // Pour l'interface utilisateur
//    @GetMapping
//    public String showActivites(
//            @RequestParam(value = "search", required = false) String search,
//            Model model
//    ) {
//        List<Activite> activites = activiteService.searchActivites(search);
//        if(search != null && !search.isEmpty()) {
//            model.addAttribute("activites", activiteService.rechercherActivites(search));
//            model.addAttribute("search", search.trim()); // Ajouter explicitement le terme de recherche
//        }
//        return "activites";
//    }

//    @GetMapping("/recherche")
//    public ResponseEntity<List<Activite>> rechercherActivites(@RequestParam String nom) {
//        List<Activite> activites = activiteService.rechercherActivites(nom);
//        return ResponseEntity.ok(activites);
//    }
//
//
//    @GetMapping("/api/recherche")
//    @ResponseBody
//    public List<Activite> rechercherActivitesApi(@RequestParam String nom) {
//        return activiteService.rechercherActivites(nom);
//    }

    @GetMapping
    public String showActivites(
            @RequestParam(value = "search", required = false) String search,
            Model model) {

        List<Activite> activites = activiteService.searchActivites(search);

        Map<Long, Double> notesMoyennes = new HashMap<>();
        Map<Long, Integer> nombreAvis = new HashMap<>();

        if (activites != null) {
            activites.forEach(activite -> {
                Long id = activite.getIdActivite();
                Double moyenne = evaluationRepository.findAverageNoteByActiviteId(id);
                int count = evaluationRepository.countByIdActivite(id);

                // Ajoutez des logs pour vérifier les valeurs
                System.out.println("Activité ID: " + id + ", Moyenne: " + moyenne + ", Nombre d'avis: " + count);

                notesMoyennes.put(id, moyenne);
                nombreAvis.put(id, count);
            });
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername(); // Nom de l'utilisateur

        Long idUtilisateur = utilisateurService.idParNomUtilisateur(username);
        List<Programme_therapeutique> programmes = programmeTherapeutiqueService.obtenirProgrammesParUtilisateur(idUtilisateur);
        System.out.println("prout");
        System.out.println(idUtilisateur);
        System.out.println(programmes);
        model.addAttribute("programmes", programmes);

        model.addAttribute("activites", activites != null ? activites : new ArrayList<>());
        model.addAttribute("notesMoyennes", notesMoyennes);
        model.addAttribute("nombreAvis", nombreAvis);
        model.addAttribute("search", search);

        return "activites";
    }
}