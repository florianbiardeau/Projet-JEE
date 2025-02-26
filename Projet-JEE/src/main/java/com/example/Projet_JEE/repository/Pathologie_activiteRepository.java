package com.example.Projet_JEE.repository;

import com.example.Projet_JEE.entity.Activite;
import com.example.Projet_JEE.entity.Pathologie_activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pathologie_activiteRepository extends JpaRepository<Pathologie_activite, Integer> {
    @Query("SELECT DISTINCT a FROM Activite a JOIN Pathologie_activite pa ON a.idActivite = pa.idActivite WHERE pa.idPathologie IN :idsPathologie")
    List<Activite> findActivitesByIdsPathologie(@Param("idsPathologie") List<Long> idsPathologie);
}
