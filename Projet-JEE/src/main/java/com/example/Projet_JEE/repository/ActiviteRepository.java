package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {

}
