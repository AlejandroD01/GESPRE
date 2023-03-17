package tesis.proyecto.gespre.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import tesis.proyecto.gespre.modelo.Asistencia;
import tesis.proyecto.gespre.modelo.Usuario;

/**
 *
 * @author Ale
 */
public class HojaFirmaDTO {

    private Integer id;
   
   
    private String organismo;
   
    private String direccion;
    
    private String area;
  
    //no le puede poner @NotEmpty a los tipos de datos ""int"" 
    private int mes;

    private String nombreYapellidos;
    
    //no le puede poner @NotEmpty a los tipos de datos ""int"" 
    
    private String no_Expediente;
    
    private String aprobadoPor;
    private int annio;
    private int usuarioID;
    
    private Usuario usuario;
    private Set<Asistencia> asistencias;

    public HojaFirmaDTO() {
    }

  

    public Integer getId() {
        return id;
    }

    public String getAprobadoPor() {
        return aprobadoPor;
    }

    public void setAprobadoPor(String aprobadoPor) {
        this.aprobadoPor = aprobadoPor;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganismo() {
        return organismo;
    }

    public Set<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Set<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }


    public String getNombreYapellidos() {
        return nombreYapellidos;
    }

    public void setNombreYapellidos(String nombreYapellidos) {
        this.nombreYapellidos = nombreYapellidos;
    }

    public String getNo_Expediente() {
        return no_Expediente;
    }

    public void setNo_Expediente(String no_Expediente) {
        this.no_Expediente = no_Expediente;
    }


    public int getAnnio() {
        return annio;
    }

    public void setAnnio(int annio) {
        this.annio = annio;
    }

}
