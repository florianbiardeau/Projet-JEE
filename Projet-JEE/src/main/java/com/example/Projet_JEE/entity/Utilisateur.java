package com.example.Projet_JEE.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements UserDetails { // Implémentez UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "nom_utilisateur", nullable = false, unique = true)
    private String nomUtilisateur;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    private Integer age;

    private String genre;

    @OneToMany(mappedBy = "utilisateur")
    private List<Programme_therapeutique> programmesTherapeutiques;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    public void setPassword(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String getUsername() {
        return nomUtilisateur;
    }

    public void setUsername(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
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
}