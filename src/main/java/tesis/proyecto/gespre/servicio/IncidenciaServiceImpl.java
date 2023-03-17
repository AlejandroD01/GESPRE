/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tesis.proyecto.gespre.dto.IncidenciaDTO;
import tesis.proyecto.gespre.dto.IncidenciaRespuesta;
import tesis.proyecto.gespre.excepciones.ResourceNotFoundException;
import tesis.proyecto.gespre.modelo.Clave;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.modelo.Incidencia;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IClaveRepo;
import tesis.proyecto.gespre.repositorio.IHojaFirmaRepo;
import tesis.proyecto.gespre.repositorio.IIncidenciaRepo;

/**
 *
 * @author mapa
 */
@Service
public class IncidenciaServiceImpl implements IIncidenciaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IIncidenciaRepo iIncidenciaRepo;

    @Autowired
    private IHojaFirmaRepo hFirmaRepo;

    @Autowired
    private IClaveRepo repoClave;

    @Override
    public IncidenciaDTO Salvar(IncidenciaDTO p) {
        Incidencia incidencia = iIncidenciaRepo.save(mapearEntidad(p));
        return mapearDTO(incidencia);
    }

    @Override
    public IncidenciaDTO SalvarPorIdHf(IncidenciaDTO incDTO, int idHojaF) {
        HojaFirma hf = hFirmaRepo.findById(idHojaF)
                .orElseThrow(() -> new ResourceNotFoundException("Hoja de Firma ", "id", idHojaF));

        System.out.println(" clave antes");
        Clave clave = repoClave.findById(incDTO.getClave().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", incDTO.getClave().getId()));
        System.out.println(" clave despues descripcion:" + clave.getDescripcion() + ", clave:" + clave.getClave());

        Incidencia incidencia = mapearEntidad(incDTO);
        System.out.println("incidencia mapeada:" + incidencia.getCantDias() + incidencia.getFecha()
                + incidencia.getDescripcion());
        incidencia.setHojafirmaI(hf);
        System.out.println("set Hoja firma:" + incidencia.getHojafirmaI().getId());
        incidencia.setClave(clave);
        System.out.println(" set clave:" + incidencia.getClave().getId());

        //-----------
        Usuario u = hf.getUsuario();
        incidencia.setNombreyApellidos(u.getNombre()+" "+u.getApellidos());
        
        iIncidenciaRepo.save(incidencia);
        System.out.println("repo save");

        return mapearDTO(incidencia);
    }

    private IncidenciaDTO mapearDTO(Incidencia incidencia) {
        IncidenciaDTO incidenciaDTO = modelMapper.map(incidencia, IncidenciaDTO.class);
        return incidenciaDTO;
    }

    //convierte de DTO a entidad
    private Incidencia mapearEntidad(IncidenciaDTO incidenciaDTO) {
        Incidencia incidencia = modelMapper.map(incidenciaDTO, Incidencia.class);
        return incidencia;
    }

    @Override
    public IncidenciaRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, Sort.by(ordenarPor));

        Page<Incidencia> page = iIncidenciaRepo.findAll(pageable);

        List<Incidencia> us = page.getContent();
        List<IncidenciaDTO> contenido = us.stream().map(incidencia
                -> mapearDTO(incidencia)).collect(Collectors.toList());

        IncidenciaRespuesta incidenciaRespuesta = new IncidenciaRespuesta();
        incidenciaRespuesta.setContenido(contenido);
        incidenciaRespuesta.setNumeroDePagina(page.getNumber());
        incidenciaRespuesta.setMedidaDePagina(page.getSize());
        incidenciaRespuesta.setTotalElementos(page.getTotalElements());
        incidenciaRespuesta.setTotalPaginas(page.getTotalPages());
        incidenciaRespuesta.setUltima(page.isLast());

        return incidenciaRespuesta;
    }

    @Override
    public IncidenciaDTO ObtenerPorId(int id) {
      Incidencia incidencia = iIncidenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", id));
        return mapearDTO(incidencia); }

    @Override
    public IncidenciaDTO actualizarIncidencia(IncidenciaDTO incidenciaDTO, int id) {
     Incidencia incidencia = iIncidenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", id));
        Incidencia inciNew = mapearEntidad(incidenciaDTO);
        
        inciNew.setId(incidencia.getId());

        Incidencia uAct = iIncidenciaRepo.save(inciNew);

        return mapearDTO(uAct); }

    @Override
    public void Eliminar(Integer id) {
  Incidencia incidencia = iIncidenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", id));
        iIncidenciaRepo.delete(incidencia);  }

}
