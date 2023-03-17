/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.HojaFirma;

/**
 *
 * @author mapa
 */
@Repository
public interface IHojaFirmaRepo extends JpaRepository<HojaFirma, Integer> {//<tipo_de_entidad, tipo_de_id>

    public List<HojaFirma> findByUsuario_Id(int idUsuario);

    public List<HojaFirma> findByMes(int annio);
//    public abstract HojaFirma findByNoExpediente(String no_Expediente);
}
