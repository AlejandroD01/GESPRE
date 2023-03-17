/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.modelo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ale
 */
@Entity
@Table(name = "clave_table")
public class Clave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    private int clave;
    private String descripcion; 

    @JsonBackReference
    @OneToMany(mappedBy = "clave", cascade = {CascadeType.REMOVE})
    private List<Incidencia> incidencia = new ArrayList<>();

    public Clave() {
    }

    public Clave(Integer id, int clave, String descripcion) {
        this.id = id;
        this.clave = clave;
        this.descripcion = descripcion;
    }

    public List<Incidencia> getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(List<Incidencia> incidencia) {
        this.incidencia = incidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
