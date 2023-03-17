/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ale
 */
@Entity
public class Asistencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int dia;
    private String entrada;
    private String salida;

    private String entrada2;
    private String salida2;

    private String inicio;
    private String terminacion;
    private String firma;

   
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hojafirma_id")
    private HojaFirma hojafirma;

    public Asistencia() {
    }

    public Asistencia(Integer id, int dia, String entrada, String salida, String entrada2, String salida2, String inicio, String terminacion, String firma, HojaFirma hojafirma) {
        this.id = id;
        this.dia = dia;
        this.entrada = entrada;
        this.salida = salida;
        this.entrada2 = entrada2;
        this.salida2 = salida2;
        this.inicio = inicio;
        this.terminacion = terminacion;
        this.firma = firma;
        this.hojafirma = hojafirma;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTerminacion() {
        return terminacion;
    }

    public void setTerminacion(String terminacion) {
        this.terminacion = terminacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(String entrada2) {
        this.entrada2 = entrada2;
    }

    public String getSalida2() {
        return salida2;
    }

    public void setSalida2(String salida2) {
        this.salida2 = salida2;
    }

    public HojaFirma getHojafirma() {
        return hojafirma;
    }

    public void setHojafirma(HojaFirma hojafirma) {
        this.hojafirma = hojafirma;
    }

}
