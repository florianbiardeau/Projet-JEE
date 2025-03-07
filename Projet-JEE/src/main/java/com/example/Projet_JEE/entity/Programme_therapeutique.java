package com.example.Projet_JEE.entity;


import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Programme_therapeutique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgrammeTherapeutique;

    @Column(nullable = false)
    private String nomProgrammeTherapeutique;

    private String note;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToMany(mappedBy = "programmes")
    private List<Activite> activites = new ArrayList<>();

    public Long getIdProgrammeTherapeutique() {
        return idProgrammeTherapeutique;
    }

    public void setIdProgrammeTherapeutique(Long idProgrammeTherapeutique) {
        this.idProgrammeTherapeutique = idProgrammeTherapeutique;
    }

    public String getNomProgrammeTherapeutique() {
        return nomProgrammeTherapeutique;
    }

    public void setNomProgrammeTherapeutique(String nomProgrammeTherapeutique) {
        this.nomProgrammeTherapeutique = nomProgrammeTherapeutique;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }
}
