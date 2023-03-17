/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.repositorio;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.Prenomina;

/**
 *
 * @author mapa
 */
@Repository
public interface IPrenominaRepo extends JpaRepository<Prenomina, Integer> {

 
}
