/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Ale
 */
@Entity
@Table(name = "TableUser", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuarios"})})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "nombres")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "usuarios")
    private String usuarios;

    @Column(name = "solapines")
    private String solapin;

    @Column(name = "pass")
    private String pass;

    @Column(name = "roles")
    private String rol;
    
    @Column(name = "cargo")
    private String cargo;
    
    @Column(name = "IdExpediente")
    private String idExpediente;

    // el eliminar en cascada y oorphanRemoval son de prueba en mi
    // proyecto no van por su logica de negocio, no puedo eliminar una hoja 
    //de firma xq esta le va a pertenecer tambien a su prenomina// me dio error a la hora de modificar
    @JsonBackReference
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.REMOVE})
    private Set<HojaFirma> hojaFirmas = new HashSet<>();

    public Usuario() {
        super();
    }

    public Usuario(Integer id, String nombre, String apellidos, String usuarios, String solapin, String pass, String rol, String cargo, String idExpediente) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuarios = usuarios;
        this.solapin = solapin;
        this.pass = pass;
        this.rol = rol;
        this.cargo = cargo;
        this.idExpediente = idExpediente;
    }

    public String getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(String idExpediente) {
        this.idExpediente = idExpediente;
    }

 
  

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSolapin() {
        return solapin;
    }

    public void setSolapin(String solapin) {
        this.solapin = solapin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<HojaFirma> getHojaFirmas() {
        return hojaFirmas;
    }

    public void setHojaFirmas(Set<HojaFirma> hojaFirmas) {
        this.hojaFirmas = hojaFirmas;
    }

}
