package com.example.Projet_JEE.dto;

public class UtilisateurDTO {
    private String nom_utilisateur;
    private String mot_de_passe;
    private Integer age;
    private String genre;

    // Getters et setters
    public String getNomUtilisateur() { return nom_utilisateur; }
    public void setNomUtilisateur(String nom_utilisateur) { this.nom_utilisateur = nom_utilisateur; }

    public String getMotDePasse() { return mot_de_passe; }
    public void setMotDePasse(String mot_de_passe) { this.mot_de_passe = mot_de_passe; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
