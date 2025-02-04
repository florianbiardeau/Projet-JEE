package com.example.Projet_JEE.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pathologie_utilisateur {

    @EmbeddedId
    private Pathologie_utilisateur_Id idPathologieUtlisateur;
    @ManyToOne
    @JoinColumn(
            name = "idPathologie",
            referencedColumnName = "idPathologie",
            nullable = false,
            insertable = false,
            updatable = false)
    private Pathologie pathologie;

    @ManyToOne
    @JoinColumn(
            name = "idUtilisateur",
            referencedColumnName = "idUtilisateur",
            nullable = false,
            insertable = false,
            updatable = false)
    private Utilisateur utilisateur;
}
