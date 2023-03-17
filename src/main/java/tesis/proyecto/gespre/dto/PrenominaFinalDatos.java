
package tesis.proyecto.gespre.dto;

import java.util.List;

public class PrenominaFinalDatos {
    private List<DatosUsuarioDTO> datosUsuarioDTOs;
    private int[] clavesPrenomina;
    private PrenominaDTO prenominaDTO;

    public PrenominaFinalDatos() {
    }

    public List<DatosUsuarioDTO> getDatosUsuarioDTOs() {
        return datosUsuarioDTOs;
    }

    public void setDatosUsuarioDTOs(List<DatosUsuarioDTO> datosUsuarioDTOs) {
        this.datosUsuarioDTOs = datosUsuarioDTOs;
    }

    public int[] getClavesPrenomina() {
        return clavesPrenomina;
    }

    public void setClavesPrenomina(int[] clavesPrenomina) {
        this.clavesPrenomina = clavesPrenomina;
    }

    public PrenominaDTO getPrenominaDTO() {
        return prenominaDTO;
    }

    public void setPrenominaDTO(PrenominaDTO prenominaDTO) {
        this.prenominaDTO = prenominaDTO;
    }
    
    
}
