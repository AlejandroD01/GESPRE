
package tesis.proyecto.gespre.controlador;

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
import tesis.proyecto.gespre.dto.PrenominaDTO;
import tesis.proyecto.gespre.dto.PrenominaFinalDatos;
import tesis.proyecto.gespre.dto.PrenominaRespuesta;
import tesis.proyecto.gespre.servicio.IPrenominaService;
import tesis.proyecto.gespre.utilerias.AppConstantes;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/prenomina")
public class PrenominaController {

    @Autowired
    IPrenominaService prenominaService;

      @GetMapping
    public PrenominaRespuesta obtenrPrenomina(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor
    ) {
        return prenominaService.findAll(numeroDePagina, medidaDePagina, ordenarPor);
    }
    
     @GetMapping(path = "/{idPrenomina}")
     public PrenominaFinalDatos ActualizarPrenomina(
            @PathVariable(value = "idPrenomina") Integer idPrenomina) {
     return prenominaService.GetPrenominaFinalDatos(idPrenomina);
     }
    
     @PostMapping()
    public ResponseEntity<PrenominaDTO> salvarPrenomina(@RequestBody PrenominaDTO prenominaDTO) {
        return new ResponseEntity<>(prenominaService.Salvar(prenominaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{idPrenomina}")
    public ResponseEntity<String> eliminarPrenomina(
            @PathVariable(value = "idPrenomina") Integer idPrenomina) {

        prenominaService.Eliminar(idPrenomina);
        return new ResponseEntity<>("Prenomina eliminado con exito", HttpStatus.OK);
    }

        @PutMapping(path = "/{id}")
    public ResponseEntity<PrenominaDTO> ActualizarPrenomina(@Valid @RequestBody PrenominaDTO prenominaDTO, @PathVariable("id") Integer id) {
        PrenominaDTO prenoNEW = prenominaService.actualizarPrenomina(prenominaDTO, id);
        return new ResponseEntity<>(prenoNEW, HttpStatus.OK);
    }
}
