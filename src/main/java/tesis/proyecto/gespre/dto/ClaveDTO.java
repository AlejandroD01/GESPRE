
package tesis.proyecto.gespre.dto;


public class ClaveDTO  {


    private Integer id;
    private int clave;
    private String descripcion; 


    public ClaveDTO() {
    }

    public ClaveDTO(Integer id, int clave, String descripcion) {
        this.id = id;
        this.clave = clave;
        this.descripcion = descripcion;
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
