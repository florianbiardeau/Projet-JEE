package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPathologie;

    @Column(nullable = false, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "pathologies")
    private List<Activite> activites = new ArrayList<>();

    @ManyToMany(mappedBy = "pathologies")
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    public Long getIdPathologie() {
        return idPathologie;
    }

    public void setIdPathologie(Long idPathologie) {
        this.idPathologie = idPathologie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
