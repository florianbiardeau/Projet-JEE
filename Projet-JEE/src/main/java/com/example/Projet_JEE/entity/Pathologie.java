package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
public class Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pathologie")
    private int idPathologie;

    @Column(nullable = false, unique=true)
    private String nom;
}
