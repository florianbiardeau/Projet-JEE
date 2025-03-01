package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
@IdClass(Pathologie_activite_Id.class)
public class Pathologie_activite {

    @Id
    @Column(name = "id_pathologie")
    private Long idPathologie;

    @Id
    @Column(name = "id_activite")
    private Long idActivite;

    @ManyToOne
    @JoinColumn(name = "id_pathologie", insertable = false, updatable = false)
    private Pathologie pathologie;

    @ManyToOne
    @JoinColumn(name = "id_activite", insertable = false, updatable = false)
    private Activite activite;

    public Long getIdPathologie() {
        return idPathologie;
    }

    public void setIdPathologie(Long idPathologie) {
        this.idPathologie = idPathologie;
    }

    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public Pathologie getPathologie() {
        return pathologie;
    }

    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
}
