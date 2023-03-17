/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import java.util.List;
import tesis.proyecto.gespre.dto.HojaFirmaDTO;
import tesis.proyecto.gespre.dto.HojaFirmaRespuesta;
import tesis.proyecto.gespre.dto.UsuarioDTO;

/**
 *
 * @author mapa
 */
public interface IHojaFirmaService {

    public HojaFirmaDTO Salvar( HojaFirmaDTO p);

    public List<HojaFirmaDTO> ListarPorUsuario(int idUsuario);

    public HojaFirmaRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor);

    public HojaFirmaDTO obtenerHojaFirmaPorID(int idHojaF, int idUsuario);
    public HojaFirmaDTO getHojaFirma(int idHojaF);
    
    public HojaFirmaDTO getHojaFirmaByUser(UsuarioDTO usuario);
    public HojaFirmaDTO getHojaFirmaByBarCode(UsuarioDTO usuario);

    public HojaFirmaDTO actualizarHojaFirmaPorIDUsuario(int idHojaF, int idUsuario, HojaFirmaDTO firmaDTO);
    public HojaFirmaDTO actualizarHojaFirma(int idHojaF,HojaFirmaDTO firmaDTO);

    public void eliminarHojaFirma(int idHojaF);
    
    
    public Integer generarHojaFirmas();

}
