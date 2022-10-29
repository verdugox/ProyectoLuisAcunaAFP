package com.ProyectoLAAR.RegistroAFP.service;

import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;
import com.ProyectoLAAR.RegistroAFP.repository.IRetreatAFPRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class RetreatAFPServiceImplement implements IRetreatAFPService {

    //SLF4J's Se logea e instancia la clase
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private IRetreatAFPRepository repository;


    @Override
    public RetreatAFP create(RetreatAFP r) throws Exception {
        return repository.save(r);
    }

    @Override
    public List<RetreatAFP> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public RetreatAFP findId(Integer id) throws Exception {
        Optional<RetreatAFP> optionalClient = repository.findById(id);
        return optionalClient.isPresent() ? optionalClient.get(): new RetreatAFP();
    }

    @Override
    public List<RetreatAFP> searchDNI(Integer DNI) throws Exception {
        return repository.searchDNI(DNI);
    }


    @Override
    public RetreatAFP update(RetreatAFP r, Integer id) throws Exception {
        Optional<RetreatAFP> optionalClient = repository.findById(id);
        if(optionalClient.isPresent()){
            RetreatAFP retreatDB = optionalClient.get();
            retreatDB.setDNI(r.getDNI());
            retreatDB.setAmountRetired(r.getAmountRetired());
            retreatDB.setAFP(r.getAFP());
            retreatDB.setDateRetired(r.getDateRetired());
            retreatDB.setNroAccount(r.getNroAccount());
            log.info("Se registro correctamente la solicitud de retiro de su AFP : " +id);
            LOGGER.info("Se registro correctamente la solicitud de retiro de su AFP : " +id);
            return repository.save(retreatDB);
        }else {
            log.severe("No se encuentra registrado el cliente {}\"");
            LOGGER.error("No se encuentra registrado el cliente {}");
        }
        return new RetreatAFP();
    }

    @Override
    public void delete(Integer id) throws Exception {
        log.info("Se eliminó el usuario que tiene por ID: " +id);
        LOGGER.info("Se eliminó el usuario que tiene por ID: " +id);
        repository.deleteById(id);
    }
}
