/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import com.example.clientewsdl.generated.ASSET.Persona;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import tesis.proyecto.gespre.clientewsdl.ClienteAssetsUCIWSDL;
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.dto.UsuarioRespuesta;
import tesis.proyecto.gespre.excepciones.ResourceNotFoundException;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;

/**
 *
 * @author mapa
 */
@Service
public class ServiceImplUsuario implements IUsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUsuarioRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ClienteAssetsUCIWSDL assetsUCIWSDL;

    @Override
    public UsuarioDTO Salvar(UsuarioDTO usuarioDTO) {

        Usuario usuario = mapearEntidad(usuarioDTO);
        usuario.setPass(encoder.encode(usuario.getPass()));
        Usuario newUser = repo.save(usuario);
        UsuarioDTO newDTO = mapearDTO(newUser);

        return newDTO;
    }

    @Override
    public UsuarioRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, Sort.by(ordenarPor));

// List<Usuario> usuariosS = repo.findAll().stream().filter(x -> x.getNombre().equals("ADMIN")).collect(Collectors.toList());
        Page<Usuario> page = repo.findAll(pageable);
  
        List<Usuario> us = page.getContent();
        List<UsuarioDTO> contenido = us.stream().filter(x -> !x.getNombre().equals("ADMIN")).map(usuario
                -> mapearDTO(usuario)).collect(Collectors.toList());
        UsuarioRespuesta usuarioRespuesta = new UsuarioRespuesta();
        usuarioRespuesta.setContenido(contenido);
        usuarioRespuesta.setNumeroDePagina(page.getNumber());
        usuarioRespuesta.setMedidaDePagina(page.getSize());
        usuarioRespuesta.setTotalElementos(page.getTotalElements());
        usuarioRespuesta.setTotalPaginas(page.getTotalPages());
        usuarioRespuesta.setUltima(page.isLast());

        return usuarioRespuesta;
    }

    @Override
    public UsuarioDTO ObtenerPorId(int id) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return mapearDTO(usuario);
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, int id) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        Usuario userNew = mapearEntidad(usuarioDTO);
        userNew.setId(usuario.getId());

        Usuario uAct = repo.save(userNew);

        return mapearDTO(uAct);
    }

    @Override
    public void Eliminar(Integer id) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        repo.delete(usuario);
    }

    //convierte entidad a DTO
    private UsuarioDTO mapearDTO(Usuario usuario) {
        UsuarioDTO userDto = modelMapper.map(usuario, UsuarioDTO.class);
        return userDto;
    }

    //convierte de DTO a entidad
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return usuario;
    }
//    @Override
//    public void Del(Usuario l) {
//        repo.delete(l);
//    }
//
//    @Override
//    public Usuario findByusuarios(String usuarios) {
//        return repo.findByusuarios(usuarios).orElse(null);
//    }
//
//    @Override
//    public Usuario findBysolapin(String solapin) {
//        return repo.findBysolapin(solapin).orElse(null);
//    }

    @Override
    public Integer GenerarUsuariosServicio() {
        // para ver la cantidad de usuarios generados
        int cont = 0;
        try {
            Persona[] personas = assetsUCIWSDL.obtenerPersonasDadoIdArea("240500");
            for (Persona persona : personas) {
                if (repo.findByidExpediente(persona.getIdExpediente()) == null) {
                    System.out.println("jajaja");
                    Usuario u = new Usuario();
                    u.setNombre(persona.getNombres().replaceAll("Ã³", "ó").replaceAll("Ã¡", "á").replaceAll("Ã­", "í").replaceAll("Ã", "í") );
                    System.out.println(u.getNombre());
                    u.setApellidos(persona.getApellidos().replaceAll("Ã³", "ó").replaceAll("Ã¡", "á").replaceAll("Ã­", "í").replaceAll("Ã", "é"));
                    u.setIdExpediente(persona.getIdExpediente());
                    //cargo:
                    String[] cargos=persona.getCargo().getNombreCargo().split(" ");
                    String  cargo="";
                    for (int i = 0; i < cargos.length; i++) {
                        cargo += cargos[i].charAt(0);
                    }
                    u.setCargo(cargo);
                    repo.save(u);
                    cont++;
                }
            }
        } catch (Exception e) {
            System.out.println(" los servicios UCI estan cahidos ");
            cont = -1;
        }

        return cont;
    }
}
