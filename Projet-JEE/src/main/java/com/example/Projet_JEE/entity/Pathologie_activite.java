package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Pathologie_activite_Id.class)
public class Pathologie_activite {

    @Id
    private Long idPathologie;

    @Id
    private Long idActivite;

    /*
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
    */

}
