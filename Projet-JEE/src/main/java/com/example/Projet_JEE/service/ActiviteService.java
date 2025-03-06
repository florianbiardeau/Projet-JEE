package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Evaluation_activiteRepository;
import com.example.Projet_JEE.repository.Pathologie_activiteRepository;
import com.example.Projet_JEE.repository.Pathologie_utilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActiviteService {

    private final ActiviteRepository activiteRepository;
    private final Pathologie_utilisateurRepository pathologieUtilisateurRepository;
    private final Pathologie_activiteRepository pathologieActiviteRepository;
    private final Evaluation_activiteRepository evaluation_activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository, Pathologie_utilisateurRepository pathologieUtilisateurRepository, Pathologie_activiteRepository pathologieActiviteRepository, Evaluation_activiteRepository evaluation_activiteRepository) {
        this.activiteRepository = activiteRepository;
        this.pathologieUtilisateurRepository = pathologieUtilisateurRepository;
        this.pathologieActiviteRepository = pathologieActiviteRepository;
        this.evaluation_activiteRepository = evaluation_activiteRepository;
    }


    public List<Activite> obtenirActiviteParRecommandation(Long idUtilisateur) {
        List<Long> idsPathologie = pathologieUtilisateurRepository.findPathologieByIdUtilisateur(idUtilisateur);
        return pathologieActiviteRepository.findActivitesByIdsPathologie(idsPathologie);
    }

    public List<Activite> obtenirToutesLesActivitesSaufDejaDansProgramme(Long idProgramme) {
        return activiteRepository.findActivitePasDansProgramme(idProgramme);
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