package com.example.Projet_JEE.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EmbeddedId;

@Entity
public class Programme_therapeutique_activite {

    @EmbeddedId
    private Programme_therapeutique_activite_Id idProgrammeTherapeutiqueActivite;
    @ManyToOne
    @JoinColumn(
            name = "idProgrammeTherapeutique",
            referencedColumnName = "idProgrammeTherapeutique",
            nullable = false,
            insertable = false,
            updatable = false)
    private Programme_therapeutique idProgrammeTherapeutique;

    @ManyToOne
    @JoinColumn(
            name = "idActivite",
            referencedColumnName = "idActivite",
            nullable = false,
            insertable = false,
            updatable = false)
    private Activite activite;
}
