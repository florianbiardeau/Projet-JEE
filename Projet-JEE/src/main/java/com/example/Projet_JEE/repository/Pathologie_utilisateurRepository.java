package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Pathologie;
import com.example.Projet_JEE.entity.Pathologie_utilisateur;
import com.example.Projet_JEE.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Pathologie_utilisateurRepository extends JpaRepository<Pathologie_utilisateur, Integer> {
    @Query("SELECT pu.idPathologie FROM Pathologie_utilisateur pu WHERE pu.idUtilisateur = :idUtilisateur")
    List<Long> findPathologieByIdUtilisateur(@Param("idUtilisateur") Long idUtilisateur);

    @Query("SELECT p FROM Pathologie p JOIN Pathologie_utilisateur pu ON p.idPathologie = pu.idPathologie WHERE pu.idUtilisateur = :idUtilisateur")
    List<Pathologie> findPathologiesByIdUtilisateur(@Param("idUtilisateur") Long idUtilisateur);

    @Modifying
    @Query("DELETE FROM Pathologie_utilisateur pu WHERE pu.idPathologie = :pathologieId AND pu.idUtilisateur = :idUtilisateur")
    void deleteByIdPathologieAndIdUtilisateur(@Param("pathologieId") Long pathologieId, @Param("idUtilisateur") Long idUtilisateur);

}
