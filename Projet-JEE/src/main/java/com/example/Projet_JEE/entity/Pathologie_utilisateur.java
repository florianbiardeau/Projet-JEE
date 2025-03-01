package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Pathologie_utilisateur_Id.class)
public class Pathologie_utilisateur {

    @Id
    @Column(name = "id_pathologie")
    private Long idPathologie;

    @Id
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_pathologie", insertable = false, updatable = false)
    private Pathologie pathologie;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
    private Utilisateur utilisateur;

    public Long getIdPathologie() {
        return idPathologie;
    }

    public void setIdPathologie(Long idPathologie) {
        this.idPathologie = idPathologie;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

}
