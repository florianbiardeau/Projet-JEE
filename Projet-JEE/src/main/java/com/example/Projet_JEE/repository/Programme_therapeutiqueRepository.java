package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Programme_therapeutique;
import com.example.Projet_JEE.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Programme_therapeutiqueRepository extends JpaRepository<Programme_therapeutique, Integer> {
    List<Programme_therapeutique> findByUtilisateurIdUtilisateur(Long idUtilisateur);

    Programme_therapeutique findByIdProgrammeTherapeutique(Long idUtilisateur);

    Integer deleteByIdProgrammeTherapeutique(Long idProgrammeTherapeutique);

    @Query("SELECT pt.utilisateur.idUtilisateur FROM Programme_therapeutique pt WHERE pt.idProgrammeTherapeutique = :idProgrammeTherapeutique")
    Long getIdUtilisateurById(Long idProgrammeTherapeutique);
}
