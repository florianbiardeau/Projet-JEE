package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Pathologie_activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pathologie_activiteRepository extends JpaRepository<Pathologie_activite, Integer> {
}
