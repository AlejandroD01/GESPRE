/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.controlador;

import java.util.List;
import javax.validation.Valid;
import org.apache.catalina.connector.Response;
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
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.dto.UsuarioRespuesta;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.servicio.IUsuarioService;
import tesis.proyecto.gespre.utilerias.AppConstantes;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/usuario")
public class UsuarioController {

    @Autowired
    IUsuarioService iUsuarioService;

    //tiene q ir primero q todos los demas metodos no se pq,
    //x el orden de los (Get,post,put,delete)
    //tiene paginacio paginacion pero yo no la utilizo en el fronte
    @GetMapping
    public UsuarioRespuesta obtenrUsuarios(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor
    ) {
        return iUsuarioService.findAll(numeroDePagina, medidaDePagina, ordenarPor);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(iUsuarioService.ObtenerPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
//      System.out.print(usuarioDTO.getApellidos());
        return new ResponseEntity<>(iUsuarioService.Salvar(usuarioDTO), HttpStatus.CREATED);
    }
    
    @PostMapping("/generar")
    public ResponseEntity<Integer> GenerarUsuariosServicio() {        
        return new ResponseEntity<>( iUsuarioService.GenerarUsuariosServicio(),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> ActualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable("id") Integer id) {
        UsuarioDTO usuarioNew = iUsuarioService.actualizarUsuario(usuarioDTO, id);
        return new ResponseEntity<>(usuarioNew, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarUsuarioPorId(@PathVariable("id") Integer id) {
        iUsuarioService.Eliminar(id);
        return new ResponseEntity<>(" Usuario eliminado con exito ", HttpStatus.OK);
    }
}
