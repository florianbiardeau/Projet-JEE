package com.example.Projet_JEE.entity;


import jakarta.persistence.*;

@Entity
public class Programme_therapeutique {

    @Id
    private int idProgrammeTherapeutique;
    @Column(nullable = false)
    private String nomProgrammeTherapeutique;
    @ManyToOne
    @JoinColumn(
            name = "idUtilisateur",
            referencedColumnName = "idUtilisateur",
            nullable = false)
    private Utilisateur idUtilisateurFK;
}
