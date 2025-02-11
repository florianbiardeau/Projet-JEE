package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Pathologie_utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pathologie_utilisateurRepository extends JpaRepository<Pathologie_utilisateur, Integer> {
}
