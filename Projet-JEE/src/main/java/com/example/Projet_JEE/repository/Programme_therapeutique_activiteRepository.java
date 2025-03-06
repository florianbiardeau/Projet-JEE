package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Programme_therapeutique_activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
public interface Programme_therapeutique_activiteRepository extends JpaRepository<Programme_therapeutique_activite, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Programme_therapeutique_activite pta WHERE pta.idProgrammeTherapeutique = :idProgrammeTherapeutique AND pta.idActivite = :idActivite")
    void deleteByIdProgrammeTherapeutique(@Param("idProgrammeTherapeutique") Long idProgrammeTherapeutique, @Param("idActivite") Long idActivite);

    @Query("SELECT pta.idActivite FROM Programme_therapeutique_activite pta WHERE pta.idProgrammeTherapeutique = :idProgrammeTherapeutique")
    List<Long> findActiviteParProgramme(@Param("idProgrammeTherapeutique") Long idProgrammeTherapeutique);

}
