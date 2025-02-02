package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
public class Utilisateur {

    @Id
    /*@SequenceGenerator(
            name = "UtilisateurSequence",
            sequenceName = "UtilisateurSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "UtilisateurSequence"
    )
    */
    private int idUtilisateur;
    @Column(nullable = false, unique = true)
    private String nomUtilisateur;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false)
    private int age;    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String pathologie;
}
