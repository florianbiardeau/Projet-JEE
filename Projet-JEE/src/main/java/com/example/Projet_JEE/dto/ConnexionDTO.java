package com.example.Projet_JEE.dto;

public class ConnexionDTO {
    private String nomUtilisateur;
    private String motDePasse;

    // Getters et setters
    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}
