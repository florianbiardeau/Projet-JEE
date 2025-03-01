package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Programme_therapeutique_activite_Id.class)
public class Programme_therapeutique_activite {

    @Id
    @Column(name = "id_programme_therapeutique")
    private Long idProgrammeTherapeutique;

    @Id
    @Column(name = "id_activite")
    private Long idActivite;

    @ManyToOne
    @JoinColumn(name = "id_programme_therapeutique", insertable = false, updatable = false)
    private Programme_therapeutique programmeTherapeutique;

    @ManyToOne
    @JoinColumn(name = "id_activite", insertable = false, updatable = false)
    private Activite activite;

    public Long getIdProgrammeTherapeutique() {
        return idProgrammeTherapeutique;
    }

    public void setIdProgrammeTherapeutique(Long idProgrammeTherapeutique) {
        this.idProgrammeTherapeutique = idProgrammeTherapeutique;
    }

    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public Programme_therapeutique getProgrammeTherapeutique() {
        return programmeTherapeutique;
    }

    public void setProgrammeTherapeutique(Programme_therapeutique programmeTherapeutique) {
        this.programmeTherapeutique = programmeTherapeutique;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
}
