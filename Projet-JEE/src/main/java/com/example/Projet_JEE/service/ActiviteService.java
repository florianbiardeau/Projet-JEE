package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Pathologie_activite;
import com.example.Projet_JEE.entity.Pathologie_utilisateur;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Pathologie_activiteRepository;
import com.example.Projet_JEE.repository.Pathologie_utilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiviteService {

    private final ActiviteRepository activiteRepository;
    private final Pathologie_utilisateurRepository pathologieUtilisateurRepository;
    private final Pathologie_activiteRepository pathologieActiviteRepository;

    public ActiviteService(ActiviteRepository activiteRepository, Pathologie_utilisateurRepository pathologieUtilisateurRepository, Pathologie_activiteRepository pathologieActiviteRepository) {
        this.activiteRepository = activiteRepository;
        this.pathologieUtilisateurRepository = pathologieUtilisateurRepository;
        this.pathologieActiviteRepository = pathologieActiviteRepository;
    }

    public List<Activite> rechercherActivites(String nom) {
        return activiteRepository.findByNomActiviteContainingIgnoreCase(nom);
    }

    public List<Activite> obtenirActiviteParRecommandation(Long idUtilisateur) {
        List<Long> idsPathologie = pathologieUtilisateurRepository.findPathologieByIdUtilisateur(idUtilisateur);
        return pathologieActiviteRepository.findActivitesByIdsPathologie(idsPathologie);
    }

}
