package com.ProyectoLAAR.RegistroAFP.controller;

import com.ProyectoLAAR.RegistroAFP.entities.RetreatAFP;
import com.ProyectoLAAR.RegistroAFP.service.IRetreatAFPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Listar todos los retiros de AFP registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se listaron todos los retiros de AFP",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RetreatAFP.class)) }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros",
                    content = @Content) })
    @GetMapping("/findAll")
    public List<RetreatAFP> findAll() throws Exception{
        return service.findAll();
    }
    @Operation(summary = "Listar registros de retiro de AFP por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontró el retiro de AFP vinculado al id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RetreatAFP.class)) }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros de retiro de AFP por id",
                    content = @Content) })
    @GetMapping("/findId/{id}")
    public RetreatAFP findId(@PathVariable("id") Integer id) throws  Exception{
        return service.findId(id);
    }
    @Operation(summary = "Listar registros de retiro de AFP por DNI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontró el retiro de AFP vinculado por DNI",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RetreatAFP.class)) }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros de retiro de AFP por DNI",
                    content = @Content) })
    @GetMapping("/searchDNI/{DNI}")
    public List<RetreatAFP> searchDNI(@PathVariable("DNI") Integer DNI) throws  Exception{

        return service.searchDNI(DNI);
    }

    @Operation(summary = "Registro del retiro de AFP vinculado a un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se creo el registro del retiro AFP del cliente de forma exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RetreatAFP.class)) }),
            @ApiResponse(responseCode = "400", description = "No se pudo registrar el retiro de AFP",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Parametros invalidos al registrar",
                    content = @Content) })
    @PostMapping("/create")
    public RetreatAFP create(@RequestBody RetreatAFP retreatAFP)throws Exception{

        return service.create(retreatAFP);
    }

    @Operation(summary = "Actualización del retiro de AFP vinculado a un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el registro del retiro AFP del cliente de forma exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RetreatAFP.class)) }),
            @ApiResponse(responseCode = "400", description = "No se pudo actualizar el retiro de AFP",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Parametros invalidos al actualizar",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public RetreatAFP update(@PathVariable("id") Integer id, @RequestBody RetreatAFP retreatAFP) throws Exception{
        return service.update(retreatAFP, id);
    }

    @Operation(summary = "Eliminación del registro de retiro de AFP por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimino el registro de retiro de AFP forma exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RetreatAFP.class)) }),
            @ApiResponse(responseCode = "400", description = "Retiro de AFP no se pudo eliminar de forma correcta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Parametros invalidos al eliminar",
                    content = @Content) })
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
    }


}
