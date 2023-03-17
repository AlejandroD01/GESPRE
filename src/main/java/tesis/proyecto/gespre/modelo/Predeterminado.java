package tesis.proyecto.gespre.modelo;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author Ale
 */
@Entity
public class Predeterminado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    
    private String organismo;
    private String area;
    private String confec_por;
    private String entidad;
    private int no_Expediente;

    public Predeterminado() {
        super();
    }

    public String getConfec_por() {
        return confec_por;
    }

    public void setConfec_por(String confec_por) {
        this.confec_por = confec_por;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNo_Expediente() {
        return no_Expediente;
    }

    public void setNo_Expediente(int no_Expediente) {
        this.no_Expediente = no_Expediente;
    }

}
