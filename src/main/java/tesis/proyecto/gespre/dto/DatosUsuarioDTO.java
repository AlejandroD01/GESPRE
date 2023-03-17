package tesis.proyecto.gespre.dto;

public class DatosUsuarioDTO{

    private Integer id;
    private String no_exp;
    private String nombreApellidos;
    private String CD;
    private String CC;
    private String Cargos;  
    private int[] claves;  
    private String extra;
    private String turno;
    private String diasF;
    private float  noct1;
    private float noct2;
    private String observaciones;

    public DatosUsuarioDTO() {
    }

    
    public int[] getClaves() {
        return claves;
    }

    public void setClaves(int[] claves) {
        this.claves = claves;
    }
         
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo_exp() {
        return no_exp;
    }

    public void setNo_exp(String no_exp) {
        this.no_exp = no_exp;
    }

   

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getCD() {
        return CD;
    }

    public void setCD(String CD) {
        this.CD = CD;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getCargos() {
        return Cargos;
    }

    public void setCargos(String Cargos) {
        this.Cargos = Cargos;
    }

   

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDiasF() {
        return diasF;
    }

    public void setDiasF(String diasF) {
        this.diasF = diasF;
    }

    public float getNoct1() {
        return noct1;
    }

    public void setNoct1(float noct1) {
        this.noct1 = noct1;
    }

    public float getNoct2() {
        return noct2;
    }

    public void setNoct2(float noct2) {
        this.noct2 = noct2;
    }

 

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
