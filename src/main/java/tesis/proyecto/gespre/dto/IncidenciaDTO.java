package tesis.proyecto.gespre.dto;

import tesis.proyecto.gespre.modelo.Clave;
import tesis.proyecto.gespre.modelo.HojaFirma;

public class IncidenciaDTO {

    private Integer id;
    private String fecha;
    private String descripcion;
    private String nombreyApellidos;
    private int cantDias;
    private Clave clave;
    private HojaFirma hojafirmaI;

    public IncidenciaDTO() {
    }
    public HojaFirma getHojafirmaI() {
        return hojafirmaI;
    }

    public void setHojafirmaI(HojaFirma hojafirmaI) {
        this.hojafirmaI = hojafirmaI;
    }

    public String getNombreyApellidos() {
        return nombreyApellidos;
    }

    public void setNombreyApellidos(String nombreyApellidos) {
        this.nombreyApellidos = nombreyApellidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Clave getClave() {
        return clave;
    }

    public void setClave(Clave clave) {
        this.clave = clave;
    }

}
