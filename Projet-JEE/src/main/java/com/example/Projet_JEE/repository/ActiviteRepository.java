package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {
    @Query("SELECT a FROM Activite a WHERE " +
            "LOWER(a.nomActivite) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(a.discipline) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(a.ville) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Activite> searchActivites(@Param("term") String term);
    List<Activite> findByNomActiviteContainingIgnoreCase(String nomActivite);

}
