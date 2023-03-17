/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tesis.proyecto.gespre.dto.PredeterminadoDTO;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.modelo.Predeterminado;
import tesis.proyecto.gespre.repositorio.IHojaFirmaRepo;
import tesis.proyecto.gespre.repositorio.IPredeterminadoRepo;

@Service
public class ServiceImplPredeterminado implements IPredeterminadoService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IPredeterminadoRepo repo;
    @Autowired
    private IHojaFirmaRepo hFirmaRepo;

   // el predeterminado tiene q volver hacia la pagina anterio o sino da problema en el fronte
    @Override
    public PredeterminadoDTO Salvar(PredeterminadoDTO predeterminado) {
        System.out.println(""+predeterminado.getId());
        Predeterminado p = mapearEntidad(predeterminado);
        System.out.println(" "+predeterminado.getId());
        Predeterminado newP = repo.save(p);
        PredeterminadoDTO newDTO = mapearDTO(newP);

        return newDTO;
    }

    @Override
    public PredeterminadoDTO getPredeterminado() {
        Predeterminado predeterminado = new Predeterminado();

        List<Predeterminado> listP = repo.findAll();
        if (listP.isEmpty()) {
            predeterminado.setId(-1);
        } else {
            predeterminado = listP.get(0);
        }
       return mapearDTO(predeterminado);
    }

    //convierte entidad a DTO
    private PredeterminadoDTO mapearDTO(Predeterminado predeterminado) {
        PredeterminadoDTO predeterminadoDTO = modelMapper.map(predeterminado, PredeterminadoDTO.class);
        return predeterminadoDTO;
    }

    //convierte de DTO a entidad
    private Predeterminado mapearEntidad(PredeterminadoDTO predeterminadoDTO) {
        Predeterminado predeterminado = modelMapper.map(predeterminadoDTO, Predeterminado.class);
        return predeterminado;
    }

}
