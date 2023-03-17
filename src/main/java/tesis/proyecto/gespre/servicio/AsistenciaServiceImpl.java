/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import tesis.proyecto.gespre.dto.AsistenciaDTO;
import tesis.proyecto.gespre.dto.AsistenciasRespuesta;
import tesis.proyecto.gespre.dto.HojaFirmaDTO;
import tesis.proyecto.gespre.dto.HojaFirmaRespuesta;
import tesis.proyecto.gespre.excepciones.PrenominaAppException;
import tesis.proyecto.gespre.excepciones.ResourceNotFoundException;
import tesis.proyecto.gespre.modelo.Asistencia;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IAsistenciaRepo;
import tesis.proyecto.gespre.repositorio.IHojaFirmaRepo;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;

/**
 *
 * @author mapa
 */
@Service
public class AsistenciaServiceImpl implements IAsistenciaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IHojaFirmaRepo hFirmaRepo;

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Autowired
    private IAsistenciaRepo asistenciaRepo;

    private AsistenciaDTO mapearDTO(Asistencia asistencia) {
        AsistenciaDTO asistenciaDTO = modelMapper.map(asistencia, AsistenciaDTO.class);

        return asistenciaDTO;
    }

    //convierte de DTO a entidad
    private Asistencia mapearEntidad(AsistenciaDTO asistenciaDTO) {
        Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);
        return asistencia;
    }

    @Override
    public AsistenciaDTO Salvar(AsistenciaDTO p) {
        Asistencia asis = mapearEntidad(p);
        Asistencia asistRespuesta = asistenciaRepo.save(asis);
        return mapearDTO(asistRespuesta);
    }

    @Override
    public AsistenciaDTO findByHojaFirmaYasistencia(int idHojaFirma, AsistenciaDTO asistenciaDTO) {
        HojaFirma hojaFirma = hFirmaRepo.findById(idHojaFirma).orElse(null);
        List<Asistencia> asis = hojaFirma.getAsistencias();
        int a = -1;
        for (int i = 0; i < asis.size(); i++) {
            if (asistenciaDTO.getDia() == asis.get(i).getDia()) {
                a = i;
            }
        }
        return mapearDTO(asis.get(a));
    }

    @Override
    public AsistenciasRespuesta findByHojaFirma(int id) {
        HojaFirma hojaFirma = hFirmaRepo.findById(id).orElse(null);
        List<Asistencia> ass = hojaFirma.getAsistencias();
        Collections.sort(ass, new SortBydia());
        List<Asistencia> asis1 = new ArrayList<>();
        List<Asistencia> asis2 = new ArrayList<>();
        int cont = 0;
        for (Asistencia a : ass) {
            if (cont < 16) {
                cont++;
                asis1.add(a);
            } else {
                asis2.add(a);
            }
        }
        return new AsistenciasRespuesta(asis1, asis2);
    }

    @Override
    public AsistenciaDTO SalvarPorId(int idHojaF, AsistenciaDTO asistenciaDTO) {
        HojaFirma hojaFirma = hFirmaRepo.findById(idHojaF).orElse(null);
        System.out.println("idHojaF:" + idHojaF);
        List<Asistencia> ass = hojaFirma.getAsistencias();
        Asistencia asistenciaRespuesta = new Asistencia();
        int a = -1;
        for (int i = 0; i < ass.size(); i++) {
            if (asistenciaDTO.getDia() == ass.get(i).getDia()) {
                System.out.println("dias asistencia:" + i);
                if (ass.get(i).getEntrada().isEmpty()) {
                    ass.get(i).setEntrada(asistenciaDTO.getEntrada());
                }
                if(ass.get(i).getEntrada2().isEmpty()){
                    ass.get(i).setEntrada2(asistenciaDTO.getEntrada2());
                }
                if(ass.get(i).getSalida().isEmpty()){
                     ass.get(i).setSalida(asistenciaDTO.getSalida());
                }
                if(ass.get(i).getSalida2().isEmpty()){
                    ass.get(i).setSalida2(asistenciaDTO.getSalida2());
                }
                
                ass.get(i).setFirma(asistenciaDTO.getFirma());
                ass.get(i).setInicio(asistenciaDTO.getInicio());
               
                
                ass.get(i).setTerminacion(asistenciaDTO.getTerminacion());
                asistenciaRepo.save(ass.get(i));
                a = i;
            }
        }

        return mapearDTO(asistenciaRespuesta);
    }

}

class SortBydia implements Comparator<Asistencia> {

    @Override
    public int compare(Asistencia o1, Asistencia o2) {
        int asistencia_dia1 = o1.getDia();
        int asistencia_dia2 = o2.getDia();
        if (asistencia_dia1 > asistencia_dia2) {
            return 1;
        } else if (asistencia_dia1 < asistencia_dia2) {
            return -1;
        } else {
            return 0;
        }
    }
}
