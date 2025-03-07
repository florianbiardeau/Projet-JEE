package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Pathologie;
import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.repository.PathologieRepository;
import com.example.Projet_JEE.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PathologieService {

    private final PathologieRepository pathologieRepository;
    private final UtilisateurRepository utilisateurRepository;

    public PathologieService(PathologieRepository pathologieRepository, UtilisateurRepository utilisateurRepository) {
        this.pathologieRepository = pathologieRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Pathologie> getAllPathologies() {
        return pathologieRepository.findAll();
    }

    @Transactional
    public void ajouterPathologiePourUtilisateur(Long idUtilisateur, Long idPathologie) {
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(idUtilisateur);
        Pathologie pathologie = pathologieRepository.findByIdPathologie(idPathologie);
        utilisateur.getPathologies().add(pathologie);
        pathologie.getUtilisateurs().add(utilisateur);

        utilisateurRepository.save(utilisateur);
        pathologieRepository.save(pathologie);
    }

    @Transactional
    public void supprimerPathologiePourUtilisateur(Long idUtilisateur, Long idPathologie) {
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(idUtilisateur);
        Pathologie pathologie = pathologieRepository.findByIdPathologie(idPathologie);
        utilisateur.getPathologies().remove(pathologie);
        pathologie.getUtilisateurs().remove(utilisateur);

        utilisateurRepository.save(utilisateur);
        pathologieRepository.save(pathologie);
    }
}
