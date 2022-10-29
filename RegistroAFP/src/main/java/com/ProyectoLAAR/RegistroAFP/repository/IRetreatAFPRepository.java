package com.ProyectoLAAR.RegistroAFP.repository;

import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRetreatAFPRepository extends JpaRepository<RetreatAFP, Integer> {

    @Query(value = "SELECT * FROM clients WHERE DNI = :DNI" ,nativeQuery = true)
    public List<RetreatAFP> searchDNI(@Param("DNI") Integer DNI);

}
