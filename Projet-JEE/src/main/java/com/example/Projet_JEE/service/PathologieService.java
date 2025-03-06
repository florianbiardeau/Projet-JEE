package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Pathologie;
import com.example.Projet_JEE.entity.Pathologie_utilisateur;
import com.example.Projet_JEE.repository.PathologieRepository;
import com.example.Projet_JEE.repository.Pathologie_utilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PathologieService {

    private final PathologieRepository pathologieRepository;
    private final Pathologie_utilisateurRepository pathologieUtilisateurRepository;

    public PathologieService(PathologieRepository pathologieRepository,
                             Pathologie_utilisateurRepository pathologieUtilisateurRepository) {
        this.pathologieRepository = pathologieRepository;
        this.pathologieUtilisateurRepository = pathologieUtilisateurRepository;
    }

    public List<Pathologie> getAllPathologies() {
        return pathologieRepository.findAll();
    }

    public List<Pathologie> getPathologiesForUser(Long idUtilisateur) {
        return pathologieUtilisateurRepository.findPathologiesByIdUtilisateur(idUtilisateur);
    }

    @Transactional
    public void ajouterPathologiePourUtilisateur(Long idUtilisateur, Long pathologieId) {
        Pathologie_utilisateur association = new Pathologie_utilisateur();
        association.setIdUtilisateur(idUtilisateur);
        association.setIdPathologie(pathologieId);
        pathologieUtilisateurRepository.save(association);
    }

    @Transactional
    public void supprimerPathologiePourUtilisateur(Long idUtilisateur, Long pathologieId) {
        pathologieUtilisateurRepository.deleteByIdPathologieAndIdUtilisateur(pathologieId, idUtilisateur);
    }
}
