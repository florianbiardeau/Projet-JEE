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
                utilisateur.getNomUtilisateur(),
                utilisateur.getMotDePasse(), // Doit être le hash BCrypt
                Collections.emptyList()
        );
    }

    public boolean nomUtilisateurExists(String nomUtilisateur) {
        return utilisateurRepository.findByNomUtilisateur(nomUtilisateur).isPresent();
    }

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> authentifier(String nomUtilisateur, String motDePasse) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByNomUtilisateur(nomUtilisateur);

        if(utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            boolean match = passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse());
            System.out.println("Mot de passe match : " + match);
            System.out.println("Hash stocké : " + utilisateur.getMotDePasse());
        } else {
            System.out.println("Utilisateur non trouvé : " + nomUtilisateur);
        }

        return utilisateurOpt.filter(u -> passwordEncoder.matches(motDePasse, u.getMotDePasse()));
    }
}