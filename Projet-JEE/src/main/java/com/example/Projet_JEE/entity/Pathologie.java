package com.example.Projet_JEE.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pathologie {

    @Id
    private int idPathologie;
    @Column(nullable = false, unique=true)
    private String nom;
}
