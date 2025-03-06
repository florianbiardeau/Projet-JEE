package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.entity.Programme_therapeutique_activite;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Programme_therapeutiqueRepository;
import com.example.Projet_JEE.repository.Programme_therapeutique_activiteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Programme_therapeutiqueService {

    private Programme_therapeutiqueRepository programmeTherapeutiqueRepository;

    private Programme_therapeutique_activiteRepository programmeTherapeutiqueActiviteRepository;

    private ActiviteRepository activiteRepository;

    private PasswordEncoder passwordEncoder;

    public Programme_therapeutiqueService(Programme_therapeutiqueRepository programmeTherapeutiqueRepository, Programme_therapeutique_activiteRepository programmeTherapeutiqueActiviteRepository, ActiviteRepository activiteRepository,
                              PasswordEncoder passwordEncoder) {
        this.programmeTherapeutiqueRepository = programmeTherapeutiqueRepository;
        this.programmeTherapeutiqueActiviteRepository = programmeTherapeutiqueActiviteRepository;
        this.activiteRepository = activiteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Programme_therapeutique> obtenirProgrammesParUtilisateur(Long idUtilisateur) {
        return programmeTherapeutiqueRepository.findByUtilisateurIdUtilisateur(idUtilisateur);
    }

    public Long obtenirIdUtilisateurParIdProgramme(Long idProgramme) {
        return programmeTherapeutiqueRepository.getIdUtilisateurById(idProgramme);
    }

    public Programme_therapeutique obtenirProgrammeParId(Long id) {
        return programmeTherapeutiqueRepository.findByIdProgrammeTherapeutique(id);
    }

    public void ajouterActivitesAuProgramme(Long programmeId, List<Long> activitesIds) {
        Programme_therapeutique programme = programmeTherapeutiqueRepository.findByIdProgrammeTherapeutique(programmeId);

        if (programme != null) {
            List<Activite> activites = new ArrayList<>();
            for (Long activiteId : activitesIds) {
                activites.add(activiteRepository.findByIdActivite(activiteId));
            }
            programme.getActivites().addAll(activites);
            programmeTherapeutiqueRepository.save(programme);
        }
    }

    public void ajouterProgramme(Programme_therapeutique programmeTherapeutique) {
        programmeTherapeutiqueRepository.save(programmeTherapeutique);
    }

    public void supprimerProgramme(Long idProgrammeTherapeutique) {
        programmeTherapeutiqueRepository.deleteByIdProgrammeTherapeutique(idProgrammeTherapeutique);
    }

    public void supprimerActiviteDuProgramme(Long idProgramme, Long idActivite) {
        programmeTherapeutiqueActiviteRepository.deleteByIdProgrammeTherapeutique(idProgramme, idActivite);
    }

    public void sauvegarder(Programme_therapeutique programme) {
        programmeTherapeutiqueRepository.save(programme);
    }

    public List<Long> obtenirActivitesParProgramme(Long programmeId) {
        return programmeTherapeutiqueActiviteRepository.findActiviteParProgramme(programmeId);
    }

}
