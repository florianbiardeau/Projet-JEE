package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Programme_therapeutiqueRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Programme_therapeutiqueService {

    private Programme_therapeutiqueRepository programmeTherapeutiqueRepository;

    private ActiviteRepository activiteRepository;

    private PasswordEncoder passwordEncoder;

    public Programme_therapeutiqueService(Programme_therapeutiqueRepository programmeTherapeutiqueRepository, ActiviteRepository activiteRepository,
                              PasswordEncoder passwordEncoder) {
        this.programmeTherapeutiqueRepository = programmeTherapeutiqueRepository;
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

    public void ajouterActivitesAuProgramme(Long idProgramme, List<Long> activitesIds) {
        Programme_therapeutique programme = programmeTherapeutiqueRepository.findByIdProgrammeTherapeutique(idProgramme);

        List<Activite> activites = activiteRepository.findAllByIdsActivite(activitesIds);

        activites.forEach(activite -> {
            if(!programme.getActivites().contains(activite)) {
                programme.getActivites().add(activite);
                activite.getProgrammes().add(programme);
            }
        });

        programmeTherapeutiqueRepository.save(programme);
        activiteRepository.saveAll(activites);
    }

    public void ajouterProgramme(Programme_therapeutique programmeTherapeutique) {
        System.out.println("Programmee : " + programmeTherapeutique.getActivites());
        programmeTherapeutiqueRepository.save(programmeTherapeutique);
    }

    public void supprimerProgramme(Long idProgrammeTherapeutique) {
        programmeTherapeutiqueRepository.deleteAssociationsByIdProgrammeTherapeutique(idProgrammeTherapeutique);
        programmeTherapeutiqueRepository.deleteByIdProgrammeTherapeutique(idProgrammeTherapeutique);
    }

    public void supprimerActiviteDuProgramme(Long idProgramme, Long idActivite) {
        Programme_therapeutique programmeTherapeutique = programmeTherapeutiqueRepository.findByIdProgrammeTherapeutique(idProgramme);
        Activite activite = activiteRepository.findByIdActivite(idActivite);
        programmeTherapeutique.getActivites().remove(activite);
        activite.getProgrammes().remove(programmeTherapeutique);

        programmeTherapeutiqueRepository.save(programmeTherapeutique);
        activiteRepository.save(activite);
    }

    public void sauvegarder(Programme_therapeutique programme) {
        programmeTherapeutiqueRepository.save(programme);
    }

    public List<Long> obtenirActivitesParProgramme(Long programmeId) {
        Programme_therapeutique programmeTherapeutique = programmeTherapeutiqueRepository.findByIdProgrammeTherapeutique(programmeId);
        return programmeTherapeutique.getActivites().stream().map(Activite::getIdActivite).collect(Collectors.toList());
    }

}
