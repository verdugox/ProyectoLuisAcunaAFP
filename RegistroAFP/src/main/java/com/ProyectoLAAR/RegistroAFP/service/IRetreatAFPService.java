package com.ProyectoLAAR.RegistroAFP.service;

import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;

import java.util.List;

public interface IRetreatAFPService {

    RetreatAFP create(RetreatAFP r) throws Exception;
    List<RetreatAFP> findAll() throws Exception;
    RetreatAFP findId(Integer id) throws Exception;

    List<RetreatAFP> searchDNI(Integer DNI) throws Exception;
    RetreatAFP update(RetreatAFP r, Integer id) throws Exception;
    void delete(Integer id)throws Exception;

}
