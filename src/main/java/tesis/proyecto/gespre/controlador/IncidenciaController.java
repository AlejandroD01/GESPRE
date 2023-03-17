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
import tesis.proyecto.gespre.dto.IncidenciaDTO;
import tesis.proyecto.gespre.dto.IncidenciaRespuesta;
import tesis.proyecto.gespre.servicio.IIncidenciaService;
import tesis.proyecto.gespre.utilerias.AppConstantes;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/Incidencia")
public class IncidenciaController {

    @Autowired
    IIncidenciaService incidenciaService;

    @GetMapping
    public IncidenciaRespuesta obtenrIncidencia(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor
    ) {
        return incidenciaService.findAll(numeroDePagina, medidaDePagina, ordenarPor);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<IncidenciaDTO> obtenerIncidenciaPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(incidenciaService.ObtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<IncidenciaDTO> salvarIcidencia(@RequestBody IncidenciaDTO incidenciaDTO) {
        return new ResponseEntity<>(incidenciaService.Salvar(incidenciaDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "{idHojaF}")
    public ResponseEntity<IncidenciaDTO> salvarIcidenciaPorIDHojaFirma(
            @PathVariable(value = "idHojaF") Integer idHojaF,
            @RequestBody IncidenciaDTO incidenciaDTO) {
        return new ResponseEntity<>(incidenciaService.SalvarPorIdHf(incidenciaDTO, idHojaF), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<IncidenciaDTO> ActualizarIncidencia(
            @Valid @RequestBody IncidenciaDTO inciDTO,
            @PathVariable("id") Integer id) {
        IncidenciaDTO incidenciaDTO = incidenciaService.actualizarIncidencia(inciDTO, id);
        return new ResponseEntity<>(incidenciaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarIncidenciaPorId(@PathVariable("id") Integer id) {
        incidenciaService.Eliminar(id);
        return new ResponseEntity<>(" Incidencia eliminado con exito ", HttpStatus.OK);
    }
}
