package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Programme_therapeutique_activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Programme_therapeutique_activiteRepository extends JpaRepository<Programme_therapeutique_activite, Integer> {

}
