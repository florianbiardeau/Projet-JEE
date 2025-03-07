package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
public class Evaluation_activite {

    @EmbeddedId
    private Evaluation_activite_Id id = new Evaluation_activite_Id();

    @ManyToOne
    @MapsId("idActivite")
    @JoinColumn(name = "id_activite", nullable = false)
    private Activite activite;

    @ManyToOne
    @MapsId("idUtilisateur")
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private int note;

    public Evaluation_activite() {
        this.id = new Evaluation_activite_Id(); // Initialisation explicite
    }

    public Evaluation_activite_Id getId() {
        return id;
    }

    public void setId(Evaluation_activite_Id id) {
        this.id = id;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}