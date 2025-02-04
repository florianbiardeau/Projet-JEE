package com.example.Projet_JEE.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pathologie_activite {

    @EmbeddedId
    private Pathologie_activite_Id idPathologieActivite;
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
            name = "idActivite",
            referencedColumnName = "idActivite",
            nullable = false,
            insertable = false,
            updatable = false)
    private Activite activite;
}
