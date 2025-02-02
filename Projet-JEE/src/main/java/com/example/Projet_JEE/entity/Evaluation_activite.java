package com.example.Projet_JEE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EmbeddedId;

@Entity
public class Evaluation_activite {

    @EmbeddedId
    private Evaluation_activite_Id idEvaluationActivite;
    @ManyToOne
    @JoinColumn(
            name = "idUtilisateur",
            referencedColumnName = "idUtilisateur",
            nullable = false,
            insertable = false,
            updatable = false)
    private Utilisateur idUtilisateurFK;

    @ManyToOne
    @JoinColumn(
            name = "idActivite",
            referencedColumnName = "idActivite",
            nullable = false,
            insertable = false,
            updatable = false)
    private Activite activite;

    private int note;
}
