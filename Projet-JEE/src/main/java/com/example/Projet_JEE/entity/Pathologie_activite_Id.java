package com.example.Projet_JEE.entity;


import java.io.Serializable;
import java.util.Objects;

public class Pathologie_activite_Id implements Serializable {
    private Long idPathologie;
    private Long idActivite;

    public Pathologie_activite_Id() {}

    public Pathologie_activite_Id(Long idPathologie, Long idActivite) {
        this.idPathologie = idPathologie;
        this.idActivite = idActivite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pathologie_activite_Id that = (Pathologie_activite_Id) o;
        return Objects.equals(idPathologie, that.idPathologie) && Objects.equals(idActivite, that.idActivite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPathologie, idActivite);
    }
}
