package com.example.Projet_JEE.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Evaluation_activite_Id implements Serializable {
    private Long idUtilisateur;
    private Long idActivite;

    public Evaluation_activite_Id() {}

    public Evaluation_activite_Id(Long idUtilisateur, Long idActivite) {
        this.idUtilisateur = idUtilisateur;
        this.idActivite = idActivite;
    }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Long getIdActivite() { return idActivite; }
    public void setIdActivite(Long idActivite) { this.idActivite = idActivite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation_activite_Id that = (Evaluation_activite_Id) o;
        return Objects.equals(idUtilisateur, that.idUtilisateur) &&
                Objects.equals(idActivite, that.idActivite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idActivite);
    }
}