package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Programme_therapeutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Programme_therapeutiqueRepository extends JpaRepository<Programme_therapeutique, Integer> {

}
