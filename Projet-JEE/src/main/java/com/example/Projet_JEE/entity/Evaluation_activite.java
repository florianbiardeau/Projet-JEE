package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Evaluation_activite_Id.class)
public class Evaluation_activite {

    @Id
    private Long idUtilisateur;

    @Id
    private Long idActivite;

    /*@ManyToOne
    @JoinColumn(
            name = "id_utilisateur",
            referencedColumnName = "id_utilisateur",
            nullable = false,
            insertable = false,
            updatable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(
            name = "id_activite",
            referencedColumnName = "id_activite",
            nullable = false,
            insertable = false,
            updatable = false)
    private Activite activite;
     */

    private int note;

}
