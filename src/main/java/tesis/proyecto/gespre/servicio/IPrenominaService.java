
package tesis.proyecto.gespre.servicio;


import tesis.proyecto.gespre.dto.PrenominaDTO;
import tesis.proyecto.gespre.dto.PrenominaFinalDatos;
import tesis.proyecto.gespre.dto.PrenominaRespuesta;




/**
 *
 * @author mapa
 */
public interface IPrenominaService {

    public PrenominaDTO Salvar(PrenominaDTO p);

    public void Eliminar(Integer id);

     public PrenominaDTO actualizarPrenomina(PrenominaDTO prenominaDTO, int id);

    public PrenominaRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor);

    public PrenominaFinalDatos GetPrenominaFinalDatos(Integer id);
}
