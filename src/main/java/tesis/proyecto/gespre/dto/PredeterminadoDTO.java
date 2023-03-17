package tesis.proyecto.gespre.dto;

public class PredeterminadoDTO  {


    private Integer id;

     private String confec_por;
     private String entidad;
    private String organismo;
    private String area;
   

    public PredeterminadoDTO() {
        super();
    }

    public String getConfec_por() {
        return confec_por;
    }

    public void setConfec_por(String confec_por) {
        this.confec_por = confec_por;
    }

    
    public Integer getId() {
        return id;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }



}
