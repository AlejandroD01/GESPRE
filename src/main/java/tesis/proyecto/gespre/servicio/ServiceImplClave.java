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
import tesis.proyecto.gespre.dto.ClaveDTO;
import tesis.proyecto.gespre.dto.ClaveRespuesta;
import tesis.proyecto.gespre.excepciones.ResourceNotFoundException;
import tesis.proyecto.gespre.modelo.Clave;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.modelo.Incidencia;
import tesis.proyecto.gespre.repositorio.IClaveRepo;
import tesis.proyecto.gespre.repositorio.IHojaFirmaRepo;
import tesis.proyecto.gespre.repositorio.IIncidenciaRepo;



@Service
public class ServiceImplClave implements IClaveService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IClaveRepo repo;
    
    @Autowired
    private IIncidenciaRepo incidenciaRepo;
 
 


    @Override
    public ClaveDTO Salvar(ClaveDTO claveDTO) {

        Clave clave = mapearEntidad(claveDTO);
        Clave newClave = repo.save(clave);
        ClaveDTO newDTO = mapearDTO(newClave);

        return newDTO;
    }

    @Override
    public ClaveRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, Sort.by(ordenarPor));

        Page<Clave> page = repo.findAll(pageable);

        List<Clave> us = page.getContent();
        List<ClaveDTO> contenido = us.stream().map(clave
                -> mapearDTO(clave)).collect(Collectors.toList());
        
        ClaveRespuesta claveRespuesta = new ClaveRespuesta();
        claveRespuesta.setContenido(contenido);
        claveRespuesta.setNumeroDePagina(page.getNumber());
        claveRespuesta.setMedidaDePagina(page.getSize());
        claveRespuesta.setTotalElementos(page.getTotalElements());
        claveRespuesta.setTotalPaginas(page.getTotalPages());
        claveRespuesta.setUltima(page.isLast());

        return claveRespuesta;
    }

    @Override
    public ClaveDTO ObtenerPorId(int id) {
        Clave clave = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", id));
        return mapearDTO(clave);
    }

    @Override
    public ClaveDTO actualizarClave(ClaveDTO claveDTO, int id) {
        Clave clave = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", id));
        
        Clave claveNew = mapearEntidad(claveDTO);
        claveNew.setId(clave.getId());

        Clave cAct = repo.save(claveNew);

        return mapearDTO(cAct);
    }

    @Override
    public void Eliminar(Integer id) {
        Clave clave = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clave", "id", id));
        repo.delete(clave);
    }

    //convierte entidad a DTO
    private ClaveDTO mapearDTO(Clave clave) {
        ClaveDTO claveDTO = modelMapper.map(clave, ClaveDTO.class);
        return claveDTO;
    }

    //convierte de DTO a entidad
    private Clave mapearEntidad(ClaveDTO claveDTO) {
        Clave clave = modelMapper.map(claveDTO, Clave.class);
        return clave;
    }


}
