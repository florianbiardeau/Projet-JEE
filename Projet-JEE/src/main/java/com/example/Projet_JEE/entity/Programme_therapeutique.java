package com.example.Projet_JEE.entity;


import jakarta.persistence.*;
import java.util.List;


@Entity
public class Programme_therapeutique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programme_therapeutique")
    private int idProgrammeTherapeutique;

    @Column(name = "nom_programme_therapeutique", nullable = false)
    private String nomProgrammeTherapeutique;

    @ManyToOne
    @JoinColumn(
            name = "id_utilisateur",
            referencedColumnName = "id_utilisateur",
            nullable = false)
    private Utilisateur utilisateur;

    @ManyToMany
    @JoinTable(
            name = "programme_therapeutique_activite",
            joinColumns = @JoinColumn(name = "id_programme_therapeutique"),
            inverseJoinColumns = @JoinColumn(name = "id_activite"))
    private List<Activite> activites;
}
