package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

@Entity
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activite")
    private int idActivite;

    @Column(name = "nom_activite", nullable = false, unique = true)
    private String nomActivite;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String discipline;

    @Column(name = "pathologie_prevention", nullable = false)
    private String pathologiePrevention;

    @Column(nullable = false)
    private int duree;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private String pays;

    @Column(nullable = false)
    private String ville;

    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String rue;

    @Column(name = "numero_de_rue", nullable = false)
    private int numeroDeRue;

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getPathologiePrevention() {
        return pathologiePrevention;
    }

    public void setPathologiePrevention(String pathologiePrevention) {
        this.pathologiePrevention = pathologiePrevention;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumeroDeRue() {
        return numeroDeRue;
    }

    public void setNumeroDeRue(int numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
    }

}
