package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Utilisateur;
import com.example.Projet_JEE.repository.UtilisateurRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getUsername(),
                utilisateur.getPassword(), // Doit être le hash BCrypt
                Collections.emptyList()
        );
    }

    public boolean nomUtilisateurExists(String nom_utilisateur) {
        return utilisateurRepository.findByNomUtilisateur(nom_utilisateur).isPresent();
    }

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> authentifier(String nom_utilisateur, String mot_de_passe) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByNomUtilisateur(nom_utilisateur);

        if(utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            boolean match = passwordEncoder.matches(mot_de_passe, utilisateur.getPassword());
            System.out.println("Mot de passe match : " + match);
            System.out.println("Hash stocké : " + utilisateur.getPassword());
        } else {
            System.out.println("Utilisateur non trouvé : " + nom_utilisateur);
        }

        return utilisateurOpt.filter(u -> passwordEncoder.matches(mot_de_passe, u.getPassword()));
    }

    public Long idParNomUtilisateur(String nomUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(nomUtilisateur).orElse(null);

        return utilisateur.getIdUtilisateur();
    }

    public Utilisateur getUtilisateurById(Long idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur).orElse(null);

        return utilisateur;

    }

}