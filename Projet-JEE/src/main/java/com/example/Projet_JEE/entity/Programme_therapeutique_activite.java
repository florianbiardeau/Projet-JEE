package com.example.Projet_JEE.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EmbeddedId;

@Entity
public class Programme_therapeutique_activite {

    @EmbeddedId
    private Programme_therapeutique_activite_Id idProgrammeTherapeutiqueActivite;
    @ManyToOne
    @JoinColumn(
            name = "idProgrammeTherapeutique",
            referencedColumnName = "idProgrammeTherapeutique",
            nullable = false,
            insertable = false,
            updatable = false)
    private Programme_therapeutique idProgrammeTherapeutique;

    @ManyToOne
    @JoinColumn(
            name = "idActivite",
            referencedColumnName = "idActivite",
            nullable = false,
            insertable = false,
            updatable = false)
    private Activite activite;

    public Programme_therapeutique_activite_Id getIdProgrammeTherapeutiqueActivite() {
        return idProgrammeTherapeutiqueActivite;
    }

    public void setIdProgrammeTherapeutiqueActivite(Programme_therapeutique_activite_Id idProgrammeTherapeutiqueActivite) {
        this.idProgrammeTherapeutiqueActivite = idProgrammeTherapeutiqueActivite;
    }

    public Programme_therapeutique getIdProgrammeTherapeutique() {
        return idProgrammeTherapeutique;
    }

    public void setIdProgrammeTherapeutique(Programme_therapeutique idProgrammeTherapeutique) {
        this.idProgrammeTherapeutique = idProgrammeTherapeutique;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
}
