package com.example.Projet_JEE.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    @Column(nullable = false, unique = true)
    private String nomUtilisateur;

    @Column(nullable = false)
    private String motDePasse;

    private Integer age;
    private String genre;

    // Ajoute d'autres colonnes si nécessaire
}
