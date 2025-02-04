package com.example.Projet_JEE.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Pathologie_utilisateur_Id {
    private int idPathologie;
    private int idUtilisateur;
}
