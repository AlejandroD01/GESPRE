/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import org.springframework.http.ResponseEntity;
import tesis.proyecto.gespre.dto.AsistenciaDTO;
import tesis.proyecto.gespre.dto.AsistenciasRespuesta;


/**
 *
 * @author mapa
 */
public interface IAsistenciaService {
    
public AsistenciaDTO Salvar( AsistenciaDTO p);
public AsistenciaDTO SalvarPorId( int idHojaF,AsistenciaDTO asistenciaDTO);
public AsistenciasRespuesta findByHojaFirma(int idHojaFirma);

public AsistenciaDTO findByHojaFirmaYasistencia(int idHojaFirma,AsistenciaDTO asistenciaDTO);

}
