package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Pathologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathologieRepository extends JpaRepository<Pathologie, Integer> {

}
