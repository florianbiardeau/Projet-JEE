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

    /*@ManyToOne
    @JoinColumn(
            name = "id_programme_therapeutique",
            referencedColumnName = "id_programme_therapeutique",
            nullable = false,
            insertable = false,
            updatable = false)
    private Programme_therapeutique programmeTherapeutique;

    @ManyToOne
    @JoinColumn(
            name = "id_activite",
            referencedColumnName = "id_activite",
            nullable = false,
            insertable = false,
            updatable = false)
    private Activite activite;
    */

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
}
