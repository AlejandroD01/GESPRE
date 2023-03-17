/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.dto.UsuarioRespuesta;
import tesis.proyecto.gespre.modelo.Usuario;

/**
 *
 * @author mapa
 */
public interface IUsuarioService {

    public UsuarioDTO Salvar(UsuarioDTO usuarioDTO);

    public UsuarioRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor);

    public UsuarioDTO ObtenerPorId(int i);

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, int id);

    public void Eliminar(Integer id);
    
    
    public Integer GenerarUsuariosServicio();

    // metodos mios
//    public void Del(Usuario l);
//
//    public Usuario findByusuarios(String nombre);
//
//    public Usuario findBysolapin(String solapin);
}
