package tesis.proyecto.gespre;

import com.example.clientewsdl.generated.ASSET.Area;
import com.example.clientewsdl.generated.ASSET.Persona;
import https.autenticacion2_uci_cu.v7.ObtenerPersonaDadoUsuarioResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import services.ObtenerPersonaDadoCodigoBarrasResponse;
import tesis.proyecto.gespre.clientewsdl.ClienteAssetsUCIWSDL;
import tesis.proyecto.gespre.clientewsdl.ClienteDatosUCIWSDL;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;

@SpringBootApplication
public class GespreApplication implements CommandLineRunner {

    @Autowired
    private ClienteAssetsUCIWSDL assetsUCIWSDL;
    @Autowired
    private ClienteDatosUCIWSDL datosUCIWSDL;
    @Autowired
    private IUsuarioRepo repo;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(GespreApplication.class, args);
    }

    public void run(String... args) throws Exception {

//        System.out.println("********************SERVICIO DATOS UCI********************");
//        services.ObtenerPersonaDadoUsuarioResponse datos1 = datosUCIWSDL.obtenerPersonaDadoUsuario("alejandrodmt");
//        
//        System.out.println(datos1.getReturn().getValue().getCredencial().getValue());
//        System.out.println("as");
//        System.out.println(datos1.getReturn().getValue().getApellidos().getValue());
//
//        AutenticarResponse p = datosUCIWSDL.autenticar("alejandrodmt", "Lmfao980512.1"); 
//        System.out.println("id : "+p.getReturn().getValue().getArea().getValue().getIdArea().getValue());
//
//    ObtenerPersonasDadoIdEstructuraResponse personasEstructura=datosUCIWSDL.personasDadoArea(440);
//     System.out.println("*************************************************************************************");
//        for (PersonaUCI personass : personasEstructura.getReturn()) {
//            System.out.println("Nombre completo:" + personass.getNombreCompleto().getValue());
//        }
        //PERSONA DADO CI
//        System.out.println(assetsUCIWSDL.obtenerPersonaXCI("84071128588").getNombres());
//        
//        //AREAS
//        Area[] areas = assetsUCIWSDL.obtenerAreas();
//        for (Area area : areas) {
//            System.out.println(area.getNombreArea() + "--" + area.getIdArea());
//        }
//        PERSONAS DADO ID DE AREA
//        Persona[] personas = assetsUCIWSDL.obtenerPersonasDadoIdArea("240500");
//        for (Persona persona : personas) {
//            System.out.println(persona.getNombres());
//        }

//// probando el barcode:
//      ObtenerPersonaDadoCodigoBarrasResponse barrasResponse = datosUCIWSDL.obtenerPersonaDadoCodigoBarra("E1130324");
//        System.out.println("siuuuuuuuuuuuu: " + barrasResponse.getReturn().getValue().getNombres().getValue());
//   

// crear ell usuario Admin:
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        Usuario u = new Usuario();
//        System.out.println(passwordEncoder.encode("123456"));
//        u.setUsuarios("alejandrodmt");
//        u.setId(2);
//        u.setPass(passwordEncoder.encode("123456"));
//        u.setRol("ROLE_ADMIN");
//        repo.save(u);


//prueba slit() en string para el caargo
//                    String aux="Tegnico de Laboratorio";
//                  String[] cargos=aux.split(" ");
//                    String  cargo="";
//                    for (int i = 0; i < cargos.length; i++) {
//                        cargo += cargos[i].charAt(0);
//                    }
//                    System.out.println(" Cargo" + cargo);

//prueba para no seleccionar el usario admin
//  List<Usuario> page = repo.findAll().stream().filter(x -> !x.getNombre().equals("ADMIN")).collect(Collectors.toList());
//        for (Usuario usuario : page) {
//            System.out.println(" "+usuario.getNombre());
//        }


    }
}
