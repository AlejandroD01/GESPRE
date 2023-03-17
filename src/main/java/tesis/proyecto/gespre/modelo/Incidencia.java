/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.modelo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ale
 */
@Entity
@Table(name = "incidencia_table")
public class Incidencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    private String fecha;
    private String descripcion;
    private String nombreyApellidos;
    private int cantDias;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hojaFirma_id")
    private HojaFirma hojafirmaI;
    
     @ManyToOne
     @JoinColumn(name="clave_id")
    private Clave clave;

    public Incidencia() {
    }

    public Incidencia(Integer id, String fecha, String descripcion, String nombreyApellidos, int cantDias, HojaFirma hojafirmaI, Clave clave) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.nombreyApellidos = nombreyApellidos;
        this.cantDias = cantDias;
        this.hojafirmaI = hojafirmaI;
        this.clave = clave;
    }

 

    public String getNombreyApellidos() {
        return nombreyApellidos;
    }

    public void setNombreyApellidos(String nombreyApellidos) {
        this.nombreyApellidos = nombreyApellidos;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HojaFirma getHojafirmaI() {
        return hojafirmaI;
    }

    public void setHojafirmaI(HojaFirma hojafirmaI) {
        this.hojafirmaI = hojafirmaI;
    }

    public Clave getClave() {
        return clave;
    }

    public void setClave(Clave clave) {
        this.clave = clave;
    }

}
