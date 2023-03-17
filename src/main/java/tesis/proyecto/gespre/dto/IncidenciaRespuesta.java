/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.dto;

import java.util.List;

public class IncidenciaRespuesta {

    private List<IncidenciaDTO> contenido;
    private int numeroDePagina;
    private int medidaDePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

    public IncidenciaRespuesta() {
    }

    public List<IncidenciaDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<IncidenciaDTO> contenido) {
        this.contenido = contenido;
    }


    public int getNumeroDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public int getMedidaDePagina() {
        return medidaDePagina;
    }

    public void setMedidaDePagina(int medidaDePagina) {
        this.medidaDePagina = medidaDePagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

}
