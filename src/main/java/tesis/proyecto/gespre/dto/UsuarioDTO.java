package tesis.proyecto.gespre.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import tesis.proyecto.gespre.modelo.HojaFirma;

/**
 *
 * @author Ale
 */
public class UsuarioDTO {

    private Integer id;

   
    private String nombre;
    private String apellidos;
    
    private String usuarios;
    private String solapin;
    
    private String cargo;
    private String idExpediente;
    
    //Cannot call sendError() after the response has been committed
    //esto me da un error si no pongo el "@JsonBackReference" por causa de la conversion
    //de jason y eso en la clase Usuario.class
    // Esto es por una talla ahi recursiva el Usuario muestra su hoja de firma y esta a su vez el usuario, y asi susecivamente
//    @JsonBackReference
    private Set<HojaFirma> hojaFirmas;
    // yo no voy a usar lo de arriba

    public UsuarioDTO() {
        super();
    }

    public UsuarioDTO(Integer id, String nombre, String apellidos, String usuarios, String solapin, String cargo, String idExpediente, Set<HojaFirma> hojaFirmas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuarios = usuarios;
        this.solapin = solapin;
        this.cargo = cargo;
        this.idExpediente = idExpediente;
        this.hojaFirmas = hojaFirmas;
    }

 

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getSolapin() {
        return solapin;
    }

    public void setSolapin(String solapin) {
        this.solapin = solapin;
    }

    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(String idExpediente) {
        this.idExpediente = idExpediente;
    }

   

    public Set<HojaFirma> getHojaFirmas() {
        return hojaFirmas;
    }

    public void setHojaFirmas(Set<HojaFirma> hojaFirmas) {
        this.hojaFirmas = hojaFirmas;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

}
