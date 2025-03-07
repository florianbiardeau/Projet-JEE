package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Programme_therapeutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Programme_therapeutiqueRepository extends JpaRepository<Programme_therapeutique, Integer> {
    List<Programme_therapeutique> findByUtilisateurIdUtilisateur(Long idUtilisateur);

    Programme_therapeutique findByIdProgrammeTherapeutique(Long idUtilisateur);

    @Modifying
    @Query(value = "DELETE FROM Programme_therapeutique_activite WHERE id_programme_therapeutique = :idProgramme", nativeQuery = true)
    void deleteAssociationsByIdProgrammeTherapeutique(@Param("idProgramme") Long idProgramme);

    @Modifying
    @Query("DELETE FROM Programme_therapeutique p WHERE p.idProgrammeTherapeutique = :idProgramme")
    Integer deleteByIdProgrammeTherapeutique(@Param("idProgramme") Long idProgramme);

    @Query("SELECT pt.utilisateur.idUtilisateur FROM Programme_therapeutique pt WHERE pt.idProgrammeTherapeutique = :idProgrammeTherapeutique")
    Long getIdUtilisateurById(Long idProgrammeTherapeutique);
}
