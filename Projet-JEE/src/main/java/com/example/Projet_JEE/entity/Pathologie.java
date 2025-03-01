package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
public class Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pathologie")
    private Long idPathologie;

    @Column(nullable = false, unique = true)
    private String nom;

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
}
