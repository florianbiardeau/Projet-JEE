package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Pathologie_utilisateur_Id.class)
public class Pathologie_utilisateur {

    @Id
    private Long idPathologie;

    @Id
    private Long idUtilisateur;

    /*
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
     */
}
