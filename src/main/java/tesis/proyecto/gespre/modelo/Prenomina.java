/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ale
 */
@Entity
@Table(name = "TablePrenomina")
public class Prenomina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    private String organismo;
    private String entidad;
    private String area;
    private String fecha;
    private String confec_por;
    private String revisado_por;
    private String aprobado_por ;
    
//    @JsonBackReference
//    @OneToMany(mappedBy = "prenomina", cascade = {CascadeType.REMOVE})
//    private List<HojaFirma> hojaFirma = new ArrayList<>();
//    
//    @OneToMany(mappedBy = "prenominaD", cascade = {CascadeType.REMOVE})
//    private List<DatosUsuario> datosUsuarios = new ArrayList<>();
    
    
   
    public Prenomina() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConfec_por() {
        return confec_por;
    }

    public void setConfec_por(String confec_por) {
        this.confec_por = confec_por;
    }

    public String getRevisado_por() {
        return revisado_por;
    }

    public void setRevisado_por(String revisado_por) {
        this.revisado_por = revisado_por;
    }

    public String getAprobado_por() {
        return aprobado_por;
    }

    public void setAprobado_por(String aprobado_por) {
        this.aprobado_por = aprobado_por;
    }

//    public List<HojaFirma> getHojaFirma() {
//        return hojaFirma;
//    }
//
//    public void setHojaFirma(List<HojaFirma> hojaFirma) {
//        this.hojaFirma = hojaFirma;
//    }
//
//    public List<DatosUsuario> getDatosUsuarios() {
//        return datosUsuarios;
//    }
//
//    public void setDatosUsuarios(List<DatosUsuario> datosUsuarios) {
//        this.datosUsuarios = datosUsuarios;
//    }

   


}
