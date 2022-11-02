package com.ProyectoLAAR.RegistroAFP.service;

import com.ProyectoLAAR.RegistroAFP.entities.Client;
import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;
import com.ProyectoLAAR.RegistroAFP.repository.IRetreatAFPRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.util.*;

@Service
@Slf4j

public class RetreatAFPServiceImplement implements IRetreatAFPService {

    //SLF4J's Se logea e instancia la clase
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private IRetreatAFPRepository repository;

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public RetreatAFP create(RetreatAFP r) throws Exception {
        Map<String,Integer> pathVariables = new HashMap<String, Integer>();
        pathVariables.put("DNI", r.getDNI());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = "http://localhost:8080/laar-afp/ClientForDNI/{DNI}";
        Client[] client= clienteRest.exchange(url, HttpMethod.GET, entity, Client[].class, pathVariables).getBody();

        if(client.length>0)
        {
            if(client[0].getDNI().intValue() == r.getDNI().intValue())
            {
                if(client[0].getAmountAvailable().intValue() > r.getAmountRetired().intValue())
                {
                    if(r.getAFP().equals(client[0].getAFP()))
                    {
                        if(r.getAmountRetired().intValue() >= client[0].getAmountAvailable().intValue() *  0.5)
                        {
                            log.error("Se realizo el registro correcto del retiro para el AFP del cliente que tiene por DNI:  " + client[0].getDNI().intValue());
                            return repository.save(r);
                        }
                        else{
                            log.error("Monto mínimo no cubierto por favor revise el monto mínimo a retirar " + client[0].getAmountAvailable().intValue() *  0.5);
                            throw new DataIntegrityViolationException("Monto mínimo no cubierto por favor revise el monto mínimo a retirar " + client[0].getAmountAvailable().intValue() *  0.5) ;
                        }
                    }
                    else{
                        log.error("El afp ingresado para el retiro, no es el que tiene vinculado en su solicitud, su afp registrado es: " + client[0].getAFP().toString());
                        throw new DataIntegrityViolationException("El afp ingresado para el retiro, no es el que tiene vinculado en su solicitud, su afp registrado es: " + client[0].getAFP().toString()) ;
                    }
                }
                else{
                    log.error("No se puede registrar la solicitud. Monto mayor que el permitido " + client[0].getAmountAvailable().intValue());
                    throw new DataIntegrityViolationException("No se puede registrar la solicitud. Monto mayor que el permitido " + client[0].getAmountAvailable().intValue()) ;
                }
            }
            else{
                log.error("El DNI que ingreso no se encuentra registrado en nuestro AFP");
                throw new DataIntegrityViolationException("El DNI que ingreso no se encuentra registrado en nuestro AFP") ;
            }
        }
        else
        {
            log.error("El DNI que ingreso no se encuentra registrado en nuestro AFP");
            throw new DataIntegrityViolationException("El DNI que ingreso no se encuentra registrado en nuestro AFP") ;
        }



    }

    @Override
    public List<RetreatAFP> findAll() throws Exception {
        log.info("Se genero la lista de retiros registrados del AFP");
        return repository.findAll();
    }

    @Override
    public RetreatAFP findId(Integer id) throws Exception {
        Optional<RetreatAFP> optionalClient = repository.findById(id);
        log.info("Se encontro el registro del retiro de afp que tiene por id : " +id);
        return optionalClient.isPresent() ? optionalClient.get(): new RetreatAFP();
    }
    @Override
    public List<RetreatAFP> searchDNI(Integer DNI) throws Exception {
        log.info("Se encontro el registro del cliente que tiene el DNI : " +DNI);
        return repository.searchDNI(DNI);
    }

    @Override
    public RetreatAFP update(RetreatAFP r, Integer id) throws Exception {
        Optional<RetreatAFP> optionalClient = repository.findById(id);

        Map<String,Integer> pathVariables = new HashMap<String, Integer>();
        pathVariables.put("DNI", r.getDNI());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = "http://localhost:8080/laar-afp/ClientForDNI/{DNI}";
        Client[] client= clienteRest.exchange(url, HttpMethod.GET, entity, Client[].class, pathVariables).getBody();

        if(client.length>0)
        {
            if(client[0].getDNI().intValue() == r.getDNI().intValue())
            {
                if(client[0].getAmountAvailable().intValue() > r.getAmountRetired().intValue())
                {
                    if(r.getAFP().equals(client[0].getAFP()))
                    {
                        if(r.getAmountRetired().intValue() >= client[0].getAmountAvailable().intValue() *  0.5)
                        {
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
                                log.error("No se encuentra registrado el cliente {}");
                                LOGGER.error("No se encuentra registrado el cliente {}");
                            }
                        }
                        else{
                            log.error("Monto mínimo no cubierto por favor revise el monto mínimo a retirar " + client[0].getAmountAvailable().intValue() *  0.5);
                            throw new DataIntegrityViolationException("Monto mínimo no cubierto por favor revise el monto mínimo a retirar " + client[0].getAmountAvailable().intValue() *  0.5) ;
                        }
                    }
                    else{
                        log.error("El afp ingresado para el retiro, no es el que tiene vinculado en su solicitud, su afp registrado es: " + client[0].getAFP().toString());
                        throw new DataIntegrityViolationException("El afp ingresado para el retiro, no es el que tiene vinculado en su solicitud, su afp registrado es: " + client[0].getAFP().toString()) ;
                    }
                }
                else{
                    log.error("No se puede actualizar la solicitud. Monto mayor que el permitido " + client[0].getAmountAvailable().intValue());
                    throw new DataIntegrityViolationException("No se puede actualizar la solicitud. Monto mayor que el permitido " + client[0].getAmountAvailable().intValue()) ;
                }
            }
            else{
                log.error("El DNI que ingreso no se encuentra registrado en nuestro AFP");
                throw new DataIntegrityViolationException("El DNI que ingreso no se encuentra registrado en nuestro AFP") ;
            }
        }
        else
        {
            log.error("El DNI que ingreso no se encuentra registrado en nuestro AFP");
            throw new DataIntegrityViolationException("El DNI que ingreso no se encuentra registrado en nuestro AFP") ;
        }
        return new RetreatAFP();
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void delete(Integer id) throws Exception {
        log.info("Se eliminó el registro de retiro AFP que tiene por ID: " +id);
        LOGGER.info("Se eliminó el registro de retiro AFP que tiene por ID: " +id);
        repository.deleteById(id);
    }
}
