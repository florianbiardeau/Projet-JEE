package com.example.Projet_JEE.entity;

import java.io.Serializable;
import java.util.Objects;

public class Pathologie_utilisateur_Id implements Serializable {
    private Long idPathologie;
    private Long idUtilisateur;

    public Pathologie_utilisateur_Id() {}

    public Pathologie_utilisateur_Id(Long idPathologie, Long idUtilisateur) {
        this.idPathologie = idPathologie;
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pathologie_utilisateur_Id that = (Pathologie_utilisateur_Id) o;
        return Objects.equals(idPathologie, that.idPathologie) && Objects.equals(idUtilisateur, that.idUtilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPathologie, idUtilisateur);
    }
}
