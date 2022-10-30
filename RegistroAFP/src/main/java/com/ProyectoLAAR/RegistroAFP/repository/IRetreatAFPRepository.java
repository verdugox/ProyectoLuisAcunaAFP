package com.ProyectoLAAR.RegistroAFP.repository;

import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRetreatAFPRepository extends JpaRepository<RetreatAFP, Integer> {
    @Query(value = "SELECT * FROM retreatsafp WHERE DNI = :DNI" ,nativeQuery = true)
    public List<RetreatAFP> searchDNI(@Param("DNI") Integer DNI);
}
