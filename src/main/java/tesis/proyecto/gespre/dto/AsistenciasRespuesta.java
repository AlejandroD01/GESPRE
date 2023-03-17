/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.dto;

import java.util.List;
import tesis.proyecto.gespre.modelo.Asistencia;

/**
 *
 * @author Ale
 */
public class AsistenciasRespuesta {

    private List<Asistencia> asis;
    private List<Asistencia> asis2;

    public AsistenciasRespuesta(List<Asistencia> asis, List<Asistencia> asis2) {
        this.asis = asis;
        this.asis2 = asis2;
    }


    
    public AsistenciasRespuesta() {
    }

    public List<Asistencia> getAsis() {
        return asis;
    }

    public void setAsis(List<Asistencia> asis) {
        this.asis = asis;
    }

    public List<Asistencia> getAsis2() {
        return asis2;
    }

    public void setAsis2(List<Asistencia> asis2) {
        this.asis2 = asis2;
    }

    
    
}
