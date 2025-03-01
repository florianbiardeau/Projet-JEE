package com.example.Projet_JEE.entity;

import java.io.Serializable;
import java.util.Objects;

public class Programme_therapeutique_activite_Id implements Serializable {
    private Long idProgrammeTherapeutique;
    private Long idActivite;

    public Programme_therapeutique_activite_Id() {}

    public Programme_therapeutique_activite_Id(Long idProgrammeTherapeutique, Long idActivite) {
        this.idProgrammeTherapeutique = idProgrammeTherapeutique;
        this.idActivite = idActivite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programme_therapeutique_activite_Id that = (Programme_therapeutique_activite_Id) o;
        return Objects.equals(idProgrammeTherapeutique, that.idProgrammeTherapeutique) && Objects.equals(idActivite, that.idActivite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProgrammeTherapeutique, idActivite);
    }
}
