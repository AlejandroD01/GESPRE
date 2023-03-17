package tesis.proyecto.gespre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tesis.proyecto.gespre.dto.ClaveDTO;
import tesis.proyecto.gespre.modelo.Clave;
import tesis.proyecto.gespre.servicio.IClaveService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import tesis.proyecto.gespre.dto.IncidenciaDTO;
import tesis.proyecto.gespre.dto.PrenominaDTO;
import tesis.proyecto.gespre.dto.UsuarioDTO;
import tesis.proyecto.gespre.modelo.Incidencia;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IIncidenciaRepo;
import tesis.proyecto.gespre.repositorio.IPrenominaRepo;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;
import tesis.proyecto.gespre.servicio.IIncidenciaService;
import tesis.proyecto.gespre.servicio.IPrenominaService;
import tesis.proyecto.gespre.servicio.IUsuarioService;

@SpringBootTest
class GespreApplicationTests {

    @Autowired
    IClaveService claveService;
    @Autowired
    IUsuarioService iUsuarioService;
    @Autowired
    IIncidenciaService incidenciaService;
    @Autowired
    private IUsuarioRepo Usuariorepo;
    @Autowired
    private IIncidenciaRepo iIncidenciaRepo;
    @Autowired
    IPrenominaService prenominaService;
    @Autowired
    private IPrenominaRepo prenominaRepo;

    @Test
    void salvarEliminarClaveTests() {
        ClaveDTO c = new ClaveDTO();
        c.setClave(33);
        c.setDescripcion("adsfasdf");
        ClaveDTO crespuesta = claveService.Salvar(c);
        assertThat(crespuesta).isNotNull();
        claveService.Eliminar(crespuesta.getId());
        assertThrows(Exception.class, () -> {
            claveService.ObtenerPorId(crespuesta.getId());
        });
    }

    @Test
    void ClaveTestActualizar() {
        ClaveDTO c = new ClaveDTO();
        c.setDescripcion("clave prueba");
        ClaveDTO i1 = claveService.Salvar(c);
        i1.setDescripcion("actualizar");
        claveService.Salvar(i1);
        System.out.println("i1: " + i1.getDescripcion() + " i2:" + c.getDescripcion());
        assertThat(i1.getDescripcion().equals(c.getDescripcion())).isFalse();
        claveService.Eliminar(i1.getId());
    }

    @Test
    void EliminarUsuarioTest() {
        Usuario u = new Usuario();
        u.setNombre("usuario Test");
        Usuario uRespuesta = Usuariorepo.save(u);
        assertThat(uRespuesta).isNotNull();
        iUsuarioService.Eliminar(uRespuesta.getId());
        assertThrows(Exception.class, () -> {
            iUsuarioService.ObtenerPorId(uRespuesta.getId());
        });
    }

    @Test
    void IncidenciaTestSalvarEliminar() {
        IncidenciaDTO i = new IncidenciaDTO();
        i.setDescripcion("incidencia prueba");
        IncidenciaDTO iRespuesta = incidenciaService.Salvar(i);
        assertThat(iRespuesta).isNotNull();
        incidenciaService.Eliminar(iRespuesta.getId());
        assertThrows(Exception.class, () -> {
            iUsuarioService.ObtenerPorId(iRespuesta.getId());
        });
    }

    @Test
    void IncidenciaTestActualizar() {
        IncidenciaDTO i = new IncidenciaDTO();
        i.setDescripcion("incidencia prueba");
        IncidenciaDTO i1 = incidenciaService.Salvar(i);
        i1.setDescripcion("actualizar");
        incidenciaService.Salvar(i1);
        System.out.println("i1: " + i1.getDescripcion() + " i2:" + i.getDescripcion());
        assertThat(i1.getDescripcion().equals(i.getDescripcion())).isFalse();
        incidenciaService.Eliminar(i1.getId());
    }

    @Test
    void IncidenciaTestListar() {
        int cant1 = iIncidenciaRepo.findAll().size();

        IncidenciaDTO i = new IncidenciaDTO();
        i.setDescripcion("incidencia prueba");
        IncidenciaDTO i1 = incidenciaService.Salvar(i);

        int cant2 = iIncidenciaRepo.findAll().size();

        assertTrue(cant2 > cant1);
        incidenciaService.Eliminar(i1.getId());
    }

    @Test
    void PrenominaTestSalvarEliminar() {
        PrenominaDTO p = new PrenominaDTO();
        p.setFecha("2022-10-16");
        PrenominaDTO pRespuesta = prenominaService.Salvar(p);
        assertThat(pRespuesta).isNotNull();
        prenominaService.Eliminar(pRespuesta.getId());
        assertThrows(Exception.class, () -> {
            prenominaService.Eliminar(pRespuesta.getId());
        });
    }

    @Test
    void PrenominaTestActualizar() {
        PrenominaDTO i = new PrenominaDTO();
        i.setFecha("2022-9-16");
        i.setRevisado_por("Prenomina-prueba");
        PrenominaDTO i1 = prenominaService.Salvar(i);
        i1.setRevisado_por("actualizar");
        prenominaService.Salvar(i1);
        assertThat(i1.getRevisado_por().equals(i.getRevisado_por())).isFalse();
        prenominaService.Eliminar(i1.getId());
    }

    @Test
    void PrenominaTestListar() {
        int cant1 = prenominaRepo.findAll().size();

       PrenominaDTO p = new PrenominaDTO();
        p.setFecha("2022-8-16");
        PrenominaDTO pRespuesta = prenominaService.Salvar(p);

        int cant2 = prenominaRepo.findAll().size();
        assertTrue(cant2 > cant1);
        prenominaService.Eliminar(pRespuesta.getId());
    }
}
