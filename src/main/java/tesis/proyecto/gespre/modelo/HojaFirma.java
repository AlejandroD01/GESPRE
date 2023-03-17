package tesis.proyecto.gespre.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ale
 */
@Entity
@Table(name = "hojas_de_firmas")
public class HojaFirma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "organismos", nullable = false)
    private String organismo;

    @Column(name = "direcciones", nullable = false)
    private String direccion;

    @Column(name = "areas", nullable = false)
    private String area;

    @Column(name = "meses", nullable = false)
    private int mes;

    @Column(name = "nombresYApellidos", nullable = false)
    private String nombreYapellidos;

    @Column(name = "NoExpedientes", nullable = false)
    private String no_Expediente;

    private String aprobadoPor;
    
    @Column(name = "años", nullable = false)
    private int annio;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
 ////////// Esto es veneno por poco me suicido tratando de buscar el error
    // y era este: //    @JsonBackReference
   @OneToMany(mappedBy = "hojafirma", cascade = {CascadeType.REMOVE})
    private List<Asistencia> asistencias = new ArrayList<>();
    
 ////////// Esto es veneno por poco me suicido tratando de buscar el error
    // y era este: //    @JsonBackReference
      @OneToMany(mappedBy = "hojafirmaI", cascade = {CascadeType.REMOVE})
    private List<Incidencia> incidencia = new ArrayList<>();

    public HojaFirma() {
        super();
    }

    public HojaFirma(Integer id, String organismo, String direccion, String area, int mes, String nombreYapellidos, String no_Expediente, String aprobadoPor, int annio, Usuario usuario) {
        this.id = id;
        this.organismo = organismo;
        this.direccion = direccion;
        this.area = area;
        this.mes = mes;
        this.nombreYapellidos = nombreYapellidos;
        this.no_Expediente = no_Expediente;
        this.aprobadoPor = aprobadoPor;
        this.annio = annio;
        this.usuario = usuario;
    }

    public String getAprobadoPor() {
        return aprobadoPor;
    }

    public void setAprobadoPor(String aprobadoPor) {
        this.aprobadoPor = aprobadoPor;
    }

  

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

   



    public String getOrganismo() {
        return organismo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Incidencia> getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(List<Incidencia> incidencia) {
        this.incidencia = incidencia;
    }

}
