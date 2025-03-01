package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Evaluation_activite_Id.class)
public class Evaluation_activite {

    @Id
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Id
    @Column(name = "id_activite")
    private Long idActivite;

    @Column(nullable = false)
    private int note;

    @ManyToOne
    @JoinColumn(name = "id_activite", insertable = false, updatable = false)
    private Activite activite;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
    private Utilisateur utilisateur;

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Long getIdActivite() { return idActivite; }
    public void setIdActivite(Long idActivite) { this.idActivite = idActivite; }

    public int getNote() { return note; }
    public void setNote(int note) { this.note = note; }
}