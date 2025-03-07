package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Evaluation_activiteRepository;
import com.example.Projet_JEE.repository.PathologieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActiviteService {

    private final ActiviteRepository activiteRepository;
    private final Evaluation_activiteRepository evaluation_activiteRepository;
    private final PathologieRepository pathologieRepository;

    public ActiviteService(ActiviteRepository activiteRepository, Evaluation_activiteRepository evaluation_activiteRepository, PathologieRepository pathologieRepository) {
        this.activiteRepository = activiteRepository;
        this.evaluation_activiteRepository = evaluation_activiteRepository;
        this.pathologieRepository = pathologieRepository;
    }


    public List<Activite> obtenirActivitesParRecommandation(Long idUtilisateur) {
        return pathologieRepository.findActivitesBySharedPathologies(idUtilisateur);
    }

    public List<Activite> obtenirToutesLesActivitesSaufDejaDansProgramme(Long idProgramme) {
        return activiteRepository.findActivitesPasDansProgramme(idProgramme);
    }

    public List<Activite> searchActivites(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return activiteRepository.findAll();
        }
        return activiteRepository.search(searchTerm.toLowerCase());
    }

    public List<Activite> obtenirActivitesParIds(List<Long> ids) {
        return activiteRepository.findAllByIdsActivite(ids);
    }

    public List<Activite> obtenirToutesLesActivites() {
        List<Activite> activites = activiteRepository.findAll();
        System.out.println("Activites tyr: ");
        System.out.println(activites);
        return activites;
    }

    public List<Integer> getNotesPourActivitesEtUtilisateur(List<Long> activitesIds, Long idUtilisateur) {
        List<Integer> notes = new ArrayList<>();
        for (Long activiteId : activitesIds) {
            Integer note = evaluation_activiteRepository.findNoteByActiviteIdAndUtilisateurId(activiteId, idUtilisateur);
            if (note != null) {
                notes.add(note);
            }
        }
        return notes;
    }
}