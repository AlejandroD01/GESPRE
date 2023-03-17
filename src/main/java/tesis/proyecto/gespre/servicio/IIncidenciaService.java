/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import tesis.proyecto.gespre.dto.IncidenciaDTO;
import tesis.proyecto.gespre.dto.IncidenciaRespuesta;



public interface IIncidenciaService {
    
public IncidenciaDTO Salvar( IncidenciaDTO p);
public IncidenciaDTO SalvarPorIdHf( IncidenciaDTO p,int idHojaF);

 public IncidenciaRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor);

    public IncidenciaDTO ObtenerPorId(int i);

    public IncidenciaDTO actualizarIncidencia(IncidenciaDTO incidenciaDTO, int id);

    public void Eliminar(Integer id);

}
