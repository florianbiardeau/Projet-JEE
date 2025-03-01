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
                notesMoyennes.put(id, moyenne);
                nombreAvis.put(id, count);
            });
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Long idUtilisateur = utilisateurService.idParNomUtilisateur(username);
        List<Programme_therapeutique> programmes = programmeTherapeutiqueService.obtenirProgrammesParUtilisateur(idUtilisateur);

        model.addAttribute("programmes", programmes);

        model.addAttribute("activites", activites != null ? activites : new ArrayList<>());
        model.addAttribute("notesMoyennes", notesMoyennes);
        model.addAttribute("nombreAvis", nombreAvis);
        model.addAttribute("search", search);

        model.addAttribute("activePage", "activites");

        return "activites";
    }
}