/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Ale
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // en el viedo se puso la variable de abajo automatico de herencia
    private static final long serialVersionUID = 1L;
    private String nombreDelRecurso;
    private String nombreDelCampo;
    private int valorDelCampo;

    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, int valorDelCampo) {
        super(String.format("%s no encontrado con : %s : '%s'", nombreDelRecurso, nombreDelCampo, valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }

    public String getNombreDelRecurso() {
        return nombreDelRecurso;
    }

    public void setNombreDelRecurso(String nombreDelRecurso) {
        this.nombreDelRecurso = nombreDelRecurso;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }

    public int getValorDelCampo() {
        return valorDelCampo;
    }

    public void setValorDelCampo(int valorDelCampo) {
        this.valorDelCampo = valorDelCampo;
    }

}
