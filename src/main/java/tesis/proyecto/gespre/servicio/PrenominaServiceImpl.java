package tesis.proyecto.gespre.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tesis.proyecto.gespre.dto.DatosUsuarioDTO;
import tesis.proyecto.gespre.dto.PrenominaDTO;
import tesis.proyecto.gespre.dto.PrenominaFinalDatos;
import tesis.proyecto.gespre.dto.PrenominaRespuesta;
import tesis.proyecto.gespre.excepciones.ResourceNotFoundException;
import tesis.proyecto.gespre.modelo.Asistencia;
import tesis.proyecto.gespre.modelo.HojaFirma;
import tesis.proyecto.gespre.modelo.Prenomina;
import tesis.proyecto.gespre.repositorio.IHojaFirmaRepo;
import tesis.proyecto.gespre.repositorio.IPrenominaRepo;

@Service
public class PrenominaServiceImpl implements IPrenominaService {

    @Autowired
    private IPrenominaRepo repo;
    @Autowired
    private IHojaFirmaRepo hojaFirmaRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrenominaDTO Salvar(PrenominaDTO p) {

        //verificar q no haya una prenomina con el mes y el annio igual
        boolean f = false;
//        Calendar fechaActual = Calendar.getInstance();
//        int mes = fechaActual.get(Calendar.MONTH) + 1;
//        int annio = fechaActual.get(Calendar.YEAR);
                String fechaDTO = p.getFecha();
                String[] arrSplit = fechaDTO.split("-");
                int mesDTO =Integer.parseInt(arrSplit[1]);
                int annioDTO =Integer.parseInt(arrSplit[0]);
                System.out.println("fecha entrada mes:" +mesDTO+ " anio:"+annioDTO );

        List<Prenomina> ps = repo.findAll();
        if (!ps.isEmpty()) {
            for (Prenomina p1 : ps) {
                String fecha = p1.getFecha();
                String[] arr2 = fecha.split("-");
                int mes =Integer.parseInt(arr2[1]);
                int annio =Integer.parseInt(arr2[0]);
                
                if (mes==mesDTO && annioDTO==annio) {
                    System.out.println("Hoja de firma ya creada");
                    f = true;
                }
            }
        }
        if (f) {
            PrenominaDTO error = new PrenominaDTO();
            error.setId(-1);

            return error;
        }

        Prenomina respuesta = repo.save(mapearEntidad(p));

        return mapearDTO(respuesta);
    }

    @Override
    public void Eliminar(Integer id) {
        Prenomina prenomina = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenomina ", "id", id));
        repo.delete(prenomina);
    }

    @Override
    public PrenominaRespuesta findAll(int numeroDePagina, int medidaDePagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, Sort.by(ordenarPor));

        Page<Prenomina> page = repo.findAll(pageable);

        List<Prenomina> us = page.getContent();
        List<PrenominaDTO> contenido = us.stream().map(usuario
                -> mapearDTO(usuario)).collect(Collectors.toList());
        PrenominaRespuesta prenominaRespuesta = new PrenominaRespuesta();

        prenominaRespuesta.setContenido(contenido);
        prenominaRespuesta.setNumeroDePagina(page.getNumber());
        prenominaRespuesta.setMedidaDePagina(page.getSize());
        prenominaRespuesta.setTotalElementos(page.getTotalElements());
        prenominaRespuesta.setTotalPaginas(page.getTotalPages());
        prenominaRespuesta.setUltima(page.isLast());

        return prenominaRespuesta;
    }

    @Override
    public PrenominaFinalDatos GetPrenominaFinalDatos(Integer id) {
        PrenominaFinalDatos prenominaFinalDatos = new PrenominaFinalDatos();

        // La prenomina 
        Prenomina p = repo.findById(id).orElse(null);

        // Las hojas de Firmas // aki solo seleccionar la del mes de la prenomina
         String fecha = p.getFecha();
                String[] arr2 = fecha.split("-");
                int mes =Integer.parseInt(arr2[1]);
                int annio =Integer.parseInt(arr2[0]);
        List<HojaFirma> ListHojaFirma = hojaFirmaRepo.findByMes(mes);
        

        
        //////claves para la prenomina//
        int[] claves = new int[100];
        int icont = 0;
        //tomando todas las claves
        for (HojaFirma hf : ListHojaFirma) {
            for (int i = 0; i < hf.getIncidencia().size(); i++) {
                claves[icont] = hf.getIncidencia().get(i).getClave().getClave();
                icont++;
            }
        }
        //acotando los ceros
        int[] contf = new int[icont];
        for (int i = 0; i < contf.length; i++) {
            contf[i] = claves[i];
        }
        //ordenando 
        Arrays.sort(contf);
        //quitando los repetidos
        List<Integer> temp = new ArrayList<>();
        int[] clavesfinal;
        for (int i = 0; i < contf.length; i++) {
            if (i == 0) {
                temp.add(contf[0]);
            } else if (contf[i] != contf[i - 1]) {
                temp.add(contf[i]);
            }
        }
        clavesfinal = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            clavesfinal[i] = temp.get(i);
        }
        for (int i : clavesfinal) {
            System.out.print(i + " ");
        }
        //////////// din de tomar las claves para la prenomina      

        //////// crear la llista de los DatosUsuarioDTO
        List<DatosUsuarioDTO> datosUsuarioDTOs = new ArrayList<>();
        for (HojaFirma hf : ListHojaFirma) {
            DatosUsuarioDTO d = new DatosUsuarioDTO();

            d.setNo_exp(hf.getNo_Expediente());
            d.setId(hf.getUsuario().getId());
            d.setNombreApellidos(hf.getUsuario().getNombre() + " " + hf.getUsuario().getApellidos());
            d.setCD(" ");
            d.setCC(" ");
            d.setCargos(hf.getUsuario().getCargo());
            ////////////////////////////////clavesssssssssssssss de los datos de usuarios
            int[] cla = new int[clavesfinal.length];
            for (int i = 0; i < clavesfinal.length; i++) {
                for (int j = 0; j < hf.getIncidencia().size(); j++) {
                    if (clavesfinal[i] == hf.getIncidencia().get(j).getClave().getClave()) {
                        cla[i] = cla[i] + hf.getIncidencia().get(j).getCantDias();
                        System.out.println(" para i:" + i + " cla[i]:" + cla[i]);
                    }
                }
            }
            d.setClaves(cla);
            ////////////////////////////////clavesssssssssssssss
            d.setExtra(" ");
            d.setTurno(" ");
            d.setDiasF(" ");
            d.setObservaciones("" + hf.getDireccion());

            // agregando la nocturnidad
            float cantDiasTrabajado = 0;
            for (Asistencia asistenciaNoct : hf.getAsistencias()) {
                if (asistenciaNoct.getFirma() != null) {
                    cantDiasTrabajado++;
                }
            }
            d.setNoct1((float) (cantDiasTrabajado * 4 ));
            d.setNoct2((float) (cantDiasTrabajado * 8));
            //agregando los DatosUsuariosDTO a la lista:
            datosUsuarioDTOs.add(d);
        }

        //Agregandole todo   a la prenominaFinalDatos
        prenominaFinalDatos.setClavesPrenomina(clavesfinal);
        prenominaFinalDatos.setDatosUsuarioDTOs(datosUsuarioDTOs);
        prenominaFinalDatos.setPrenominaDTO(mapearDTO(p));

        return prenominaFinalDatos;
    }

    private PrenominaDTO mapearDTO(Prenomina prenomina) {
        PrenominaDTO prenominaDTO = modelMapper.map(prenomina, PrenominaDTO.class);

        return prenominaDTO;
    }

    //convierte de DTO a entidad
    private Prenomina mapearEntidad(PrenominaDTO prenominaDTO) {
        Prenomina prenomina = modelMapper.map(prenominaDTO, Prenomina.class);
        return prenomina;
    }

    @Override
    public PrenominaDTO actualizarPrenomina(PrenominaDTO prenominaDTO, int id) {
        Prenomina prenomina = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenomina", "id", id));
        Prenomina preNew = mapearEntidad(prenominaDTO);
        preNew.setId(prenomina.getId());

        Prenomina uAct = repo.save(preNew);

        return mapearDTO(uAct);
    }

}
