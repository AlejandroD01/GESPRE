
package tesis.proyecto.gespre.dto;

public class PrenominaDTO{


    private Integer id;
    private String organismo;
    private String entidad;
    private String area;
    private String fecha;
    private String confec_por;
    private String revisado_por;
    private String aprobado_por ;

    public PrenominaDTO() {

    }

    public PrenominaDTO(Integer id, String organismo, String entidad, String area, String fecha, String confec_por, String revisado_por, String aprobado_por) {
        this.id = id;
        this.organismo = organismo;
        this.entidad = entidad;
        this.area = area;
        this.fecha = fecha;
        this.confec_por = confec_por;
        this.revisado_por = revisado_por;
        this.aprobado_por = aprobado_por;
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




}
