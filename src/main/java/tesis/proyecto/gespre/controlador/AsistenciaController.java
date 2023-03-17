/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.controlador;

import java.util.Comparator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tesis.proyecto.gespre.dto.AsistenciaDTO;
import tesis.proyecto.gespre.dto.AsistenciasRespuesta;
import tesis.proyecto.gespre.dto.HojaFirmaDTO;
import tesis.proyecto.gespre.dto.HojaFirmaRespuesta;
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.modelo.Asistencia;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.servicio.IAsistenciaService;
import tesis.proyecto.gespre.servicio.IHojaFirmaService;
import tesis.proyecto.gespre.utilerias.AppConstantes;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/asistencia")
public class AsistenciaController {


    @Autowired
    IAsistenciaService asistService;

    
     @GetMapping(path = "/a/{idHojaF}")
    public AsistenciasRespuesta obtenrAsistencias(@PathVariable(value = "idHojaF") Integer idHojaF) {
        return asistService.findByHojaFirma(idHojaF);
    }

    // esto es por si el profe quiere q para cuando firmen la asistencias aparesca ya firmado anteriormente
    
    @PostMapping(path = "/a/{idHojaF}")
    public ResponseEntity<AsistenciaDTO> obtenrAsistenciasporDia(
            @PathVariable(value = "idHojaF") Integer idHojaF,
            @RequestBody AsistenciaDTO asistenciaDTO) {
        return new ResponseEntity<>(asistService.findByHojaFirmaYasistencia(idHojaF,asistenciaDTO), HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<AsistenciaDTO> salvarAsistencia(@RequestBody AsistenciaDTO asistenciaDTO) {
        return new ResponseEntity<>(asistService.Salvar( asistenciaDTO), HttpStatus.CREATED);
    }
    
    @PostMapping(path = "{idHojaF}")
    public ResponseEntity<AsistenciaDTO> salvarAsistenciaPorHojaFirma(
             @PathVariable(value = "idHojaF") Integer idHojaF,
            @RequestBody AsistenciaDTO asistenciaDTO) {
        return new ResponseEntity<>(asistService.SalvarPorId(idHojaF,asistenciaDTO), HttpStatus.CREATED);
    }
   
}


