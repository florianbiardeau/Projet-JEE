package com.example.Projet_JEE.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActivite;

    @Column(nullable = false, unique = true)
    private String nomActivite;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String discipline;

    @Column(nullable = false)
    private String pathologiePrevention;

    @Column(nullable = false)
    private int duree;

    @Column(nullable = false)
    private String pays;

    @Column(nullable = false)
    private String ville;

    @Column(nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String rue;

    @Column(nullable = false)
    private int numeroDeRue;

    @OneToMany(mappedBy = "activite")
    private List<Evaluation_activite> evaluations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "programme_therapeutique_activite",
            joinColumns = @JoinColumn(name = "idActivite"),
            inverseJoinColumns = @JoinColumn(name = "idProgrammeTherapeutique")
    )
    private List<Programme_therapeutique> programmes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "pathologie_activite",
            joinColumns = @JoinColumn(name = "idActivite"),
            inverseJoinColumns = @JoinColumn(name = "idPathologie")
    )
    private List<Pathologie> pathologies = new ArrayList<>();

    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long idActivite) {
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

    public List<Evaluation_activite> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation_activite> evaluations) {
        this.evaluations = evaluations;
    }

    public List<Programme_therapeutique> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<Programme_therapeutique> programmes) {
        this.programmes = programmes;
    }

    public List<Pathologie> getPathologies() {
        return pathologies;
    }

    public void setPathologies(List<Pathologie> pathologies) {
        this.pathologies = pathologies;
    }
}
