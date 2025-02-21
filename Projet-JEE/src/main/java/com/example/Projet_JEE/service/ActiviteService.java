package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.repository.ActiviteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActiviteService {

    private final ActiviteRepository activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public List<Activite> rechercherActivites(String nom) {
        return activiteRepository.findByNomActiviteContainingIgnoreCase(nom);
    }
}
