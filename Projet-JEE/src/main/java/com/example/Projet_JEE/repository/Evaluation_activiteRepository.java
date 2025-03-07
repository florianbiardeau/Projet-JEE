package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Evaluation_activite;
import com.example.Projet_JEE.entity.Evaluation_activite_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Evaluation_activiteRepository extends JpaRepository<Evaluation_activite, Evaluation_activite_Id> {

    Optional<Evaluation_activite> findByUtilisateurIdUtilisateurAndActiviteIdActivite(Long idUtilisateur, Long idActivite);

    @Query("SELECT COALESCE(AVG(e.note), 0.0) FROM Evaluation_activite e WHERE e.id.idActivite = :idActivite")
    Double findAverageNoteByActiviteId(@Param("idActivite") Long idActivite);

    @Query("SELECT COUNT(e) FROM Evaluation_activite e WHERE e.id.idActivite = :idActivite")
    int countByIdActivite(@Param("idActivite") Long idActivite);

    @Query("SELECT e.note FROM Evaluation_activite e WHERE e.id.idActivite = :idActivite AND e.id.idUtilisateur = :idUtilisateur")
    Integer findNoteByActiviteIdAndUtilisateurId(Long idActivite, Long idUtilisateur);
}