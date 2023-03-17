/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.controlador;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.proyecto.gespre.dto.LoginDTO;
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;
import tesis.proyecto.gespre.seguridad.JWTAuthResonseDTO;
import tesis.proyecto.gespre.seguridad.JwtTokenProvider;

/**
 *
 * @author Ale
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/gespre/auth")
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsuarios(), loginDTO.getPass()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);
        
        return  ResponseEntity.ok(new JWTAuthResonseDTO(token));
    }

    @PostMapping("/registrarse")
    public ResponseEntity<?> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
      System.out.print(usuarioDTO.getApellidos()+"jajajja");
      
      if(usuarioRepo.existsByUsuarios(usuarioDTO.getUsuarios())){
          return new ResponseEntity<>("Ya existe el usuario: "+usuarioDTO.getUsuarios(),HttpStatus.BAD_REQUEST);
      }
          
      
        Usuario usuario = mapearEntidad(usuarioDTO);
        usuario.setPass(passwordEncoder.encode(usuario.getPass()));
        usuarioRepo.save(usuario);

        return new ResponseEntity<>("Usuario registrado con exito", HttpStatus.OK);
    }
    //convierte de DTO a entidad
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return usuario;
    }
}
