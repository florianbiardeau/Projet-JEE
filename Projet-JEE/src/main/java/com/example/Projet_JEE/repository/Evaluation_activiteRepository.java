package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Evaluation_activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Evaluation_activiteRepository extends JpaRepository<Evaluation_activite, Integer> {
}
