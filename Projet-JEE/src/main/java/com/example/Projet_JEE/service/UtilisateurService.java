package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur creerCompte(String nomUtilisateur, String motDePasse, Integer age, String genre) {
        if (utilisateurRepository.findByNomUtilisateur(nomUtilisateur).isPresent()) {
            throw new RuntimeException("Nom d'utilisateur déjà pris");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(nomUtilisateur);
        utilisateur.setMotDePasse(passwordEncoder.encode(motDePasse)); // Hachage du mot de passe
        utilisateur.setAge(age);
        utilisateur.setGenre(genre);

        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> authentifier(String nomUtilisateur, String motDePasse) {
        return utilisateurRepository.findByNomUtilisateur(nomUtilisateur)
                .filter(utilisateur -> passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse()));
    }
}
