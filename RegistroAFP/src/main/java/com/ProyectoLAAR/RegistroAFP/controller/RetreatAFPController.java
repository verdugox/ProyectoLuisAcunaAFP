package com.ProyectoLAAR.RegistroAFP.controller;

import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;
import com.ProyectoLAAR.RegistroAFP.service.IRetreatAFPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/laar-retreatAFP")
public class RetreatAFPController {

    @Autowired
    private IRetreatAFPService service;

    @GetMapping("/findAll")
    public List<RetreatAFP> findAll() throws Exception{
        return service.findAll();
    }

    @GetMapping("/findId/{id}")
    public RetreatAFP findId(@PathVariable("id") Integer id) throws  Exception{
        return service.findId(id);
    }

    @GetMapping("/ClientForAFP/{AFP}")
    public List<RetreatAFP> getClientForAFP(@PathVariable("DNI") Integer DNI) throws  Exception{
        return service.searchDNI(DNI);
    }

    @PostMapping("/create")
    public RetreatAFP create(@RequestBody RetreatAFP retreatAFP)throws Exception{

        return service.create(retreatAFP);
    }

    @PutMapping("/update/{id}")
    public RetreatAFP update(@PathVariable("id") Integer id, @RequestBody RetreatAFP retreatAFP) throws Exception{
        return service.update(retreatAFP, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
    }


}
