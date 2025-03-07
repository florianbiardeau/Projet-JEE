package com.example.Projet_JEE.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    @Column(nullable = false, unique = true)
    private String nomUtilisateur;

    @Column(nullable = false)
    private String motDePasse;

    private Integer age;

    private String genre;

    @OneToMany(mappedBy = "utilisateur")
    private List<Programme_therapeutique> programmesTherapeutiques;

    @OneToMany(mappedBy = "utilisateur")
    private List<Evaluation_activite> evaluations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "pathologie_utilisateur",
            joinColumns = @JoinColumn(name = "idUtilisateur"),
            inverseJoinColumns = @JoinColumn(name = "idPathologie")
    )
    private List<Pathologie> pathologies = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return nomUtilisateur;
    }

    public void setUsername(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    public void setPassword(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Programme_therapeutique> getProgrammesTherapeutiques() {
        return programmesTherapeutiques;
    }

    public void setProgrammesTherapeutiques(List<Programme_therapeutique> programmesTherapeutiques) {
        this.programmesTherapeutiques = programmesTherapeutiques;
    }

    public List<Evaluation_activite> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation_activite> evaluations) {
        this.evaluations = evaluations;
    }

    public List<Pathologie> getPathologies() {
        return pathologies;
    }

    public void setPathologies(List<Pathologie> pathologies) {
        this.pathologies = pathologies;
    }
}