/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.seguridad;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;

/**
 *
 * @author Ale
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private IUsuarioRepo usuarioRepo;
            
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepo.findByusuarios(username)
              .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese usuarios :"+username));
        System.out.println(" usuario q encuentra como username: "+username+" ,usuario q encontro con el findBy:"+usuario.getUsuarios());
        System.out.println("y su pass es:"+usuario.getPass()+" y su rol es:" + usuario.getRol());
       ////////////// AQUI SE AGREGAN LOS ROLES 
        List<GrantedAuthority> authoritys = new ArrayList<>();
        authoritys.add(new SimpleGrantedAuthority(usuario.getRol()));
      
      return new User(usuario.getUsuarios(),usuario.getPass() , authoritys);
    }
    
}
