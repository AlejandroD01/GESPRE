/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.servicio;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import services.ObtenerPersonaDadoCodigoBarrasResponse;
import tesis.proyecto.gespre.clientewsdl.ClienteDatosUCIWSDL;
import tesis.proyecto.gespre.dto.HojaFirmaDTO;
import tesis.proyecto.gespre.dto.HojaFirmaRespuesta;
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.excepciones.PrenominaAppException;
import tesis.proyecto.gespre.excepciones.ResourceNotFoundException;
import tesis.proyecto.gespre.modelo.Asistencia;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.modelo.Predeterminado;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IAsistenciaRepo;
import tesis.proyecto.gespre.repositorio.IHojaFirmaRepo;
import tesis.proyecto.gespre.repositorio.IPredeterminadoRepo;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;

/**
 *
 * @author mapa
 */
@Service
public class HojaFirmaServiceImpl implements IHojaFirmaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IHojaFirmaRepo hFirmaRepo;

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Autowired
    private IAsistenciaRepo asistRepo;

    @Autowired
    private IPredeterminadoRepo predeterminadoRepo;

    @Autowired
    private ClienteDatosUCIWSDL datosUCIWSDL;

    @Override
    public HojaFirmaDTO Salvar(HojaFirmaDTO p) {
        List<HojaFirma> hfs = hFirmaRepo.findAll()
                .stream()
                .filter(x -> x.getNo_Expediente()
                        .equals(p.getNo_Expediente()))
                .collect(Collectors.toList());
        System.out.println("  p hoja firma:"+p.getNo_Expediente()+p.getDireccion());
        HojaFirma hf= hfs.get(0);
        hf.setDireccion(p.getDireccion());
        hFirmaRepo.save(hf);

        //salvar viejo
//        HojaFirma hf = mapearEntidad(p);
//
//        System.out.println(" 1111 hoja firmadto:" + hf.getMes() + " anio " + hf.getAnnio());
//        Usuario usuario = usuarioRepo.findById(p.getUsuarioID())
//                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", p.getUsuarioID()));
//        System.out.println(" 222222  id:" + usuario.getId());
//        List<HojaFirma> hfs = hFirmaRepo.findByUsuario_Id(p.getUsuarioID());
//        System.out.println(" 33333333 isempty:" + hfs.isEmpty() + hfs.size());
//        for (HojaFirma hfi : hfs) {
//            System.out.println("55+ anio hf  " + hf.getAnnio() + " anio hfi:" + hfi.getAnnio());
//            if (hf.getAnnio() == hfi.getAnnio()) {
//                System.out.println(" 555 mes:" + hfi.getMes() + " anio : " + hfi.getAnnio());
//                if (hf.getMes() == hfi.getMes()) {
//                    System.out.println(" 555 mes:" + hfi.getMes() + " anio : " + hfi.getAnnio());
//                    HojaFirmaDTO error = new HojaFirmaDTO();
//                    error.setId(-1);
//                    return error;
//                }
//            }
//        }
//        System.out.println(" 4444444");
//        ////// todo bien
//
//        hf.setUsuario(usuario);
//        HojaFirma hfRespuesta = hFirmaRepo.save(hf);
//        for (int i = 1; i < 32; i++) {
//            Asistencia a = new Asistencia();
//            a.setDia(i);
//            a.setHojafirma(hfRespuesta);
//            asistRepo.save(a);
//        }
        return p;
    }

    @Override
    public List<HojaFirmaDTO> ListarPorUsuario(int idUsuario) {
        List<HojaFirma> hojaFirmas = hFirmaRepo.findByUsuario_Id(idUsuario);
        return hojaFirmas.stream().map(hFirma -> mapearDTO(hFirma)).collect(Collectors.toList());
    }

    @Override
    public HojaFirmaRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, Sort.by(ordenarPor));

        Page<HojaFirma> page = hFirmaRepo.findAll(pageable);

        List<HojaFirma> us = page.getContent();
        List<HojaFirmaDTO> contenido = us.stream().map(hojaFirma
                -> mapearDTO(hojaFirma)).collect(Collectors.toList());
        HojaFirmaRespuesta hojaFRespuesta = new HojaFirmaRespuesta();
        hojaFRespuesta.setContenido(contenido);
        hojaFRespuesta.setNumeroDePagina(page.getNumber());
        hojaFRespuesta.setMedidaDePagina(page.getSize());
        hojaFRespuesta.setTotalElementos(page.getTotalElements());
        hojaFRespuesta.setTotalPaginas(page.getTotalPages());
        hojaFRespuesta.setUltima(page.isLast());

        return hojaFRespuesta;
    }

    @Override
    public HojaFirmaDTO obtenerHojaFirmaPorID(int idHojaF, int idUsuario) {
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));

        HojaFirma hf = hFirmaRepo.findById(idHojaF)
                .orElseThrow(() -> new ResourceNotFoundException("Hoja de Firma ", "id", idHojaF));
        if (!hf.getUsuario().getId().equals(usuario.getId())) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "La Hoja de Firma no pertenece al usuario  ");
        }

        return mapearDTO(hf);
    }

    @Override
    public HojaFirmaDTO getHojaFirma(int idHojaF) {
        HojaFirma hf = hFirmaRepo.findById(idHojaF)
                .orElseThrow(() -> new ResourceNotFoundException("Hoja de Firma ", "id", idHojaF));
        return mapearDTO(hf);
    }

    @Override
    public HojaFirmaDTO actualizarHojaFirmaPorIDUsuario(int idHojaF, int idUsuario, HojaFirmaDTO firmaDTO) {
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
        HojaFirma hf = hFirmaRepo.findById(idHojaF)
                .orElseThrow(() -> new ResourceNotFoundException("Hoja de Firma ", "id", idHojaF));
        if (!hf.getUsuario().getId().equals(usuario.getId())) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "La Hoja de Firma no pertenece al usuario  ");
        }
        hf.setAnnio(firmaDTO.getAnnio());
        hf.setArea(firmaDTO.getArea());
//        hf.setNo_Expediente(firmaDTO.getNo_Expediente());
// estoy quitando el no Expediente
        hf.setDireccion(firmaDTO.getDireccion());
        hf.setMes(firmaDTO.getMes());
        hf.setNombreYapellidos(firmaDTO.getNombreYapellidos());
        hf.setOrganismo(firmaDTO.getOrganismo());

        HojaFirma hFActualizada = hFirmaRepo.save(hf);

        return mapearDTO(hFActualizada);
    }

    @Override
    public void eliminarHojaFirma(int idHojaF) {
        HojaFirma hf = hFirmaRepo.findById(idHojaF)
                .orElseThrow(() -> new ResourceNotFoundException("Hoja de Firma ", "id", idHojaF));
        hFirmaRepo.delete(hf);
    }

    @Override
    public HojaFirmaDTO actualizarHojaFirma(int idHojaF, HojaFirmaDTO firmaDTO) {

        HojaFirma hf = hFirmaRepo.findById(idHojaF)
                .orElseThrow(() -> new ResourceNotFoundException("Hoja de Firma ", "id", idHojaF));

        hf.setAnnio(firmaDTO.getAnnio());
        hf.setArea(firmaDTO.getArea());
        hf.setDireccion(firmaDTO.getDireccion());
//        hf.setNo_Expediente(firmaDTO.getNo_Expediente());
        // estoy quitando el no Expediente
        hf.setMes(firmaDTO.getMes());
        hf.setNombreYapellidos(firmaDTO.getNombreYapellidos());
        hf.setOrganismo(firmaDTO.getOrganismo());

        HojaFirma hFActualizada = hFirmaRepo.save(hf);

        return mapearDTO(hFActualizada);
    }

    ///nota """ IMPORTANTE """ si agrego una hoja de firma de un mes mayor al que estoy no va a funcionar
    /// cuando ingresas el codigo del solapin
    @Override
    public HojaFirmaDTO getHojaFirmaByUser(UsuarioDTO usuario) {
        HojaFirma firma = new HojaFirma();

        //Error de no encontrar usuario
        Usuario u = usuarioRepo.findBysolapin(usuario.getSolapin()).orElse(null);

        if (u == null) {
            firma.setId(-2);
            return mapearDTO(firma);
        }

        //error de no encontrar hoja de firma a ese usuario
        List<HojaFirma> hojaFirmas = hFirmaRepo.findByUsuario_Id(u.getId());
        if (hojaFirmas.isEmpty()) {
            firma.setId(-3);
            return mapearDTO(firma);
        } else {
            Calendar fechaActual = Calendar.getInstance();
            int mes = fechaActual.get(Calendar.MONTH) + 1;
            int annio = fechaActual.get(Calendar.YEAR);
            firma = hojaFirmas.get(hojaFirmas.size() - 1);
            System.out.println("annio:" + annio + " firma.getAnnio()" + firma.getAnnio() + " firma.getMes()" + firma.getMes() + " mes" + mes);

            if (firma.getAnnio() != annio || firma.getMes() != mes) {
                System.out.println("annio:" + annio + " firma.getAnnio()" + firma.getAnnio() + " firma.getMes()" + firma.getMes() + " mes" + mes);
                HojaFirma firma2 = new HojaFirma();
                firma2.setId(-3);
                return mapearDTO(firma2);
            }
        }

        return mapearDTO(firma);
    }

    private HojaFirmaDTO mapearDTO(HojaFirma hojaFirma) {
        HojaFirmaDTO firmaDTO = modelMapper.map(hojaFirma, HojaFirmaDTO.class);

        return firmaDTO;
    }

    //convierte de DTO a entidad
    private HojaFirma mapearEntidad(HojaFirmaDTO firmaDTO) {
        HojaFirma hojaFirma = modelMapper.map(firmaDTO, HojaFirma.class);
        return hojaFirma;
    }

    //generar hoja de firma funciona para todas las personas(usuarios ) 
    //q no poseen usuarios(ej:alejandrodmt, para este no se genera)
    //esto es para quitar el usuario maestro, el admin
    @Override
    public Integer generarHojaFirmas() {

        //verificando el predeterminado q este
        List<Predeterminado> p = predeterminadoRepo.findAll();
        Predeterminado predeterminado = p.get(0);
        System.out.println("datos:" + predeterminado.getArea() + predeterminado.getOrganismo() + predeterminado.getEntidad());
        if (p.isEmpty()) {
            return -1;
        }
        // listando todos los usurios y obteniento la fecha actual  
        List<Usuario> usuarios = usuarioRepo.findAll();
        System.out.println("bien hasta aki");
        Calendar fechaActual = Calendar.getInstance();
        int mes = fechaActual.get(Calendar.MONTH) + 1;
        int annio = fechaActual.get(Calendar.YEAR);
        System.out.println("mes:" + mes + "  anio:" + annio);

        // contador para notificar si se genero alguna hoja de firma
        int cont = 0;
        //for para revisar q no aparescan hojas de firmas repetidas
        for (Usuario usuario : usuarios) {
            // verificando q no se coja el usuario maestro a travez de su usuario, ya que todos los demas no van a tener
            if (usuario.getUsuarios() == null) {
                boolean error = true;
                List<HojaFirma> hfs = hFirmaRepo.findByUsuario_Id(usuario.getId());
                for (HojaFirma hfi : hfs) {
                    if (annio == hfi.getAnnio()) {
                        if (mes == hfi.getMes()) {
                            error = false;
                        }
                    }
                }

                if (error) {
                    cont++;
                    System.out.println("si");
                    HojaFirma hf = new HojaFirma();

                    hf.setUsuario(usuario);
                    hf.setAnnio(annio);
                    hf.setMes(mes);
                    hf.setArea(predeterminado.getArea());
                    hf.setOrganismo(predeterminado.getOrganismo());
                    hf.setNo_Expediente(usuario.getIdExpediente());
                    hf.setDireccion(".");
                    hf.setNombreYapellidos(usuario.getNombre() + " " + usuario.getApellidos());

                    //en caso de que me vuelva a funcionar los servicios UCI ya no sera Jefe de Departamento
                    //Sera ""{{JD}} por lo que modifique los Aprobado por
                    for (Usuario usuario1 : usuarios) {
                        if (usuario1.getCargo().equals("JD")) {
                            hf.setAprobadoPor(usuario1.getNombre() + " " + usuario1.getApellidos());
                        }
                    }
                    HojaFirma hfRespuesta = hFirmaRepo.save(hf);

                    //agregandole asistencias
                    for (int i = 1; i < 32; i++) {
                        Asistencia a = new Asistencia();
                        a.setDia(i);
                        a.setHojafirma(hfRespuesta);
                        asistRepo.save(a);
                    }
                } else {
                    System.out.println("no");

                }

            }
        }

        return cont;
    }

    ///nota """ IMPORTANTE """ si agrego una hoja de firma de un mes mayor al que estoy no va a funcionar
    /// cuando ingresas el codigo del solapin
    @Override
    public HojaFirmaDTO getHojaFirmaByBarCode(UsuarioDTO usuario) {
        System.out.println("solapin usuario dentro de metodo:" + usuario.getSolapin());
        HojaFirma firma = new HojaFirma();
        try {
            ObtenerPersonaDadoCodigoBarrasResponse barrasResponse = datosUCIWSDL.obtenerPersonaDadoCodigoBarra(usuario.getSolapin());
            if (barrasResponse.getReturn().getValue().getIdExpediente().getValue().equals("") || barrasResponse.getReturn() == null) {
                firma.setId(-2);
                System.out.println(" la barra respose da nulo, este es el error, servidor caido o no encontro el servicio ese codigo barra");
                return mapearDTO(firma);
            }

            System.out.println("barrasResponse id Expediente:" + barrasResponse.getReturn().getValue().getIdExpediente().getValue());
            Usuario u = usuarioRepo.findByidExpediente(barrasResponse.getReturn().getValue().getIdExpediente().getValue());
            System.out.println("usuario encontrado:" + u.getNombre());

            if (u == null) {
                firma.setId(-2);
                return mapearDTO(firma);
            }

            //error de no encontrar hoja de firma a ese usuario
            List<HojaFirma> hojaFirmas = hFirmaRepo.findByUsuario_Id(u.getId());
            if (hojaFirmas.isEmpty()) {
                firma.setId(-3);
                return mapearDTO(firma);
            } else {
                Calendar fechaActual = Calendar.getInstance();
                int mes = fechaActual.get(Calendar.MONTH) + 1;
                int annio = fechaActual.get(Calendar.YEAR);
                firma = hojaFirmas.get(hojaFirmas.size() - 1);
                System.out.println("annio:" + annio + " firma.getAnnio()" + firma.getAnnio() + " firma.getMes()" + firma.getMes() + " mes" + mes);

                if (firma.getAnnio() != annio || firma.getMes() != mes) {
                    System.out.println("annio:" + annio + " firma.getAnnio()" + firma.getAnnio() + " firma.getMes()" + firma.getMes() + " mes" + mes);
                    HojaFirma firma2 = new HojaFirma();
                    firma2.setId(-3);
                    return mapearDTO(firma2);
                }
            }
        } catch (Exception e) {
            System.out.println("servicios uci cahidos");
            firma.setId(-1);
        }

        return mapearDTO(firma);
    }

}
