
package tesis.proyecto.gespre.servicio;

import tesis.proyecto.gespre.dto.ClaveDTO;
import tesis.proyecto.gespre.dto.ClaveRespuesta;


public interface IClaveService {

    public ClaveDTO Salvar(ClaveDTO claveDTO);

    public ClaveRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor);

    public ClaveDTO ObtenerPorId(int i);

    public ClaveDTO actualizarClave(ClaveDTO claveDTO, int id);

    public void Eliminar(Integer id);

}
