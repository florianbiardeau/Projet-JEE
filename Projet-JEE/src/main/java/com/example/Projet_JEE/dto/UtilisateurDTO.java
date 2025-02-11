package com.example.Projet_JEE.dto;

public class UtilisateurDTO {
    private String nomUtilisateur;
    private String motDePasse;
    private Integer age;
    private String genre;

    // Getters et setters
    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
