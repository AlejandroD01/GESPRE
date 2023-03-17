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
import tesis.proyecto.gespre.dto.HojaFirmaDTO;
import tesis.proyecto.gespre.dto.HojaFirmaRespuesta;
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.servicio.IAsistenciaService;
import tesis.proyecto.gespre.servicio.IHojaFirmaService;
import tesis.proyecto.gespre.utilerias.AppConstantes;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/hojaFirma")
public class HojaFirmaController {

    @Autowired
    IHojaFirmaService firmaService;
    @Autowired
    IAsistenciaService asistService;

    @GetMapping(path = "/{idHojaF}")
    public ResponseEntity<HojaFirmaDTO> GetHojaFirmas(@PathVariable(value = "idHojaF") Integer idHojaF) {
        HojaFirmaDTO dTO = firmaService.getHojaFirma(idHojaF);
        return new ResponseEntity<>(dTO, HttpStatus.OK);
    }

    @GetMapping
    public HojaFirmaRespuesta obtenrHojasFirmas(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor
    ) {
        return firmaService.findAll(numeroDePagina, medidaDePagina, ordenarPor);
    }

    @PostMapping("/usuario")
    public HojaFirmaDTO obtenrHojaFirmaBYUser(@RequestBody UsuarioDTO usuario) {
        System.out.println("kjajajajajajjajajaolapin :" + usuario.getSolapin());
//        return firmaService.getHojaFirmaByUser(usuario);
        return firmaService.getHojaFirmaByBarCode(usuario);
    }

    @PostMapping()
    public ResponseEntity<HojaFirmaDTO> salvarHojaFirma(@RequestBody HojaFirmaDTO firmaDTO) {   
        return new ResponseEntity<>(firmaService.Salvar(firmaDTO), HttpStatus.CREATED);
    }

    @PostMapping("/generar")
    public ResponseEntity<Integer> GenerarHojasFirmas() {    
        return new ResponseEntity<>(firmaService.generarHojaFirmas(), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{idHojaF}")
    public ResponseEntity<HojaFirmaDTO> actualizarHojaFirma(
            @PathVariable(value = "idHojaF") Integer idHojaF,
            @Valid @RequestBody HojaFirmaDTO firmaDTO) {

        HojaFirmaDTO actualizadoHFirma = firmaService.actualizarHojaFirma(idHojaF, firmaDTO);
        return new ResponseEntity<>(actualizadoHFirma, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{idHojaF}")
    public ResponseEntity<String> eliminarHojaFirma(
            @PathVariable(value = "idHojaF") Integer idHojaF) {

        firmaService.eliminarHojaFirma(idHojaF);
        return new ResponseEntity<>("Hoja de Firma eliminado con exito", HttpStatus.OK);
    }

}
