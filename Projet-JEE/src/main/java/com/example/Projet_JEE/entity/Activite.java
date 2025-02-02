package com.example.Projet_JEE.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Activite {

    @Id
    private int idActivite;
    @Column(nullable = false, unique = true)
    private String nomActivite;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String discipline;
    @Column(nullable = false)
    private String Pathologie_prevention;
    @Column(nullable = false)
    private int duree;
    @Column(nullable = false, unique = true)
    private String url;
    @Column(nullable = false)
    private String pays;
    @Column(nullable = false)
    private String ville;
    @Column(nullable = false)
    private String codePostal;
    @Column(nullable = false)
    private String rue;
    @Column(nullable = false)
    private int numeroDeRue;
}
