package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.ui.Model;


@Controller
public class DashboardController {

    private Programme_therapeutiqueService programmeTherapeutiqueService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @RequestParam("idUtilisateur") Long idUtilisateur) {
        // Récupérer la liste des programmes depuis le service
        Long idUtilisateurLong = 0L;
        List<Programme_therapeutique> programmes = programmeTherapeutiqueService.obtenirProgrammesParUtilisateur(idUtilisateurLong);

        System.out.println(programmes);

        // Ajouter la liste des programmes au modèle
        model.addAttribute("programmes", programmes);

        return "dashboard"; // Correspond au template dashboard.html
    }
}