/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.Usuario;

/**
 *
 * @author mapa
 */
@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

    public abstract Optional<Usuario> findByusuarios(String usuarios);
    public abstract Optional<Usuario> findBysolapin(String usuarios);
    public abstract Usuario findByidExpediente(String idExpediente);
    
    public Boolean existsByUsuarios(String usuarios);
//
//    public abstract Optional<Usuario> findBysolapin(String solapin);
    //findBy es una palabra reserva por tanto se puede utilizar 
    //para modificar lo q uno desee, en este caso buscar poor nombre
    // es el atributo nombre pero despues de un findBy lo q sigue es con mayuscula

}
