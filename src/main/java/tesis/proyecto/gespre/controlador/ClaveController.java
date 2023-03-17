/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.controlador;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import tesis.proyecto.gespre.dto.ClaveDTO;
import tesis.proyecto.gespre.dto.ClaveRespuesta;
import tesis.proyecto.gespre.servicio.IClaveService;


import tesis.proyecto.gespre.utilerias.AppConstantes;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/clave")
public class ClaveController {

    @Autowired
    IClaveService claveService;


    @GetMapping
    public ClaveRespuesta obtenrClave(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor
    ) {
        return claveService.findAll(numeroDePagina, medidaDePagina, ordenarPor);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClaveDTO> obtenerClavePorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(claveService.ObtenerPorId(id));
    }

  
    @PostMapping
    public ResponseEntity<ClaveDTO> salvarClave(
            @Valid @RequestBody ClaveDTO claveDTO) {
        return new ResponseEntity<>(claveService.Salvar(claveDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClaveDTO> ActualizarClave(
            @Valid @RequestBody ClaveDTO claveDTO, 
            @PathVariable("id") Integer id) {
        ClaveDTO claveNew = claveService.actualizarClave(claveDTO, id);
        return new ResponseEntity<>(claveNew, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarClavePorId(@PathVariable("id") Integer id) {
        claveService.Eliminar(id);
        return new ResponseEntity<>(" Clave eliminado con exito ", HttpStatus.OK);
    }
}
