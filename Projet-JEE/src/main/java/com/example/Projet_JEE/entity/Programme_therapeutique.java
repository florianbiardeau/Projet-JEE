package com.example.Projet_JEE.entity;


import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;


@Entity
public class Programme_therapeutique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programme_therapeutique")
    private Long idProgrammeTherapeutique;

    @Setter
    @Column(name = "nom_programme_therapeutique", nullable = false)
    private String nomProgrammeTherapeutique;

    @Setter
    @ManyToOne
    @JoinColumn(
            name = "id_utilisateur",
            referencedColumnName = "id_utilisateur",
            nullable = false)
    private Utilisateur utilisateur;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "programme_therapeutique_activite",
            joinColumns = @JoinColumn(name = "id_programme_therapeutique"),
            inverseJoinColumns = @JoinColumn(name = "id_activite"))
    private List<Activite> activites;

    private String note;

    public Long getIdProgrammeTherapeutique() {
        return idProgrammeTherapeutique;
    }

    public String getNomProgrammeTherapeutique() {
        return nomProgrammeTherapeutique;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
