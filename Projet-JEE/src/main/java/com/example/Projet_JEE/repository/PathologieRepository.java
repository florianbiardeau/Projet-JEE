package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Pathologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathologieRepository extends JpaRepository<Pathologie, Integer> {

    Pathologie findByIdPathologie(Long idPathologie);

    @Query("SELECT DISTINCT a " +
            "FROM Activite a " +
            "WHERE EXISTS (" +
            "    SELECT p FROM a.pathologies p " +
            "    WHERE p IN (SELECT up FROM Utilisateur u JOIN u.pathologies up WHERE u.idUtilisateur = :userId)" +
            ")")
    List<Activite> findActivitesBySharedPathologies(@Param("userId") Long userId);
}
