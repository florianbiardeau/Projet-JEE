package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Programme_therapeutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {

    Activite findByIdActivite(Long idActivite);

    @Query("SELECT a FROM Activite a WHERE " +
            "LOWER(a.nomActivite) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(a.discipline) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(a.ville) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Activite> search(@Param("searchTerm") String searchTerm);

    @Query("SELECT a FROM Activite a " +
            "WHERE a.idActivite NOT IN " +
            "(SELECT a2.idActivite FROM Programme_therapeutique p " +
            "JOIN p.activites a2 WHERE p.idProgrammeTherapeutique = :idProgramme)")
    List<Activite> findActivitesPasDansProgramme(@Param("idProgramme") Long idProgramme);

    @Query("SELECT a FROM Activite a WHERE a.idActivite IN :idsActivite")
    List<Activite> findAllByIdsActivite(@Param("idsActivite") List<Long> idsActivite);
}
