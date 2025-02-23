package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.entity.Programme_therapeutique_activite;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Programme_therapeutiqueRepository;
import com.example.Projet_JEE.repository.Programme_therapeutique_activiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Programme_therapeutiqueService {

    @Autowired
    private Programme_therapeutiqueRepository programmeTherapeutiqueRepository;

    @Autowired
    private Programme_therapeutique_activiteRepository programmeTherapeutiqueActiviteRepository;

    @Autowired
    private ActiviteRepository activiteRepository;

    public void ajouterActiviteAuProgramme(int idProgramme, int idActivite) {
        // Trouver le programme thérapeutique par ID
        Programme_therapeutique programme = programmeTherapeutiqueRepository.findById(idProgramme)
                .orElseThrow(() -> new RuntimeException("Programme non trouvé"));

        // Trouver l'activité par ID
        Activite activite = activiteRepository.findById(idActivite)
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));

        // Créer une nouvelle relation entre le programme et l'activité
        Programme_therapeutique_activite programmeTherapeutiqueActivite = new Programme_therapeutique_activite();
        programmeTherapeutiqueActivite.setIdProgrammeTherapeutique(programme);
        programmeTherapeutiqueActivite.setActivite(activite);

        // Sauvegarder la relation dans la base de données
        programmeTherapeutiqueActiviteRepository.save(programmeTherapeutiqueActivite);
    }
}
