
package tesis.proyecto.gespre.controlador;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.proyecto.gespre.dto.PredeterminadoDTO;
import tesis.proyecto.gespre.servicio.IPredeterminadoService;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/predeterminado")
public class PredeterminadoController {

    @Autowired
    IPredeterminadoService iPredeterminadoService;

    @GetMapping()
    public ResponseEntity<PredeterminadoDTO> obtenerPredeterminadoPorId() {
        return ResponseEntity.ok(iPredeterminadoService.getPredeterminado());
    }

    @PostMapping
    public ResponseEntity<PredeterminadoDTO> salvarPredeterminado(@Valid @RequestBody PredeterminadoDTO predeterminadoDTO) {
        return new ResponseEntity<>(iPredeterminadoService.Salvar(predeterminadoDTO), HttpStatus.CREATED);
    }

}
