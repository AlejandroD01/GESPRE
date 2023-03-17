
package tesis.proyecto.gespre.clientewsdl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


///Esstaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa es la q voy a utilizar
@Configuration
public class ClienteUCIWSDLConfig {

    private Jaxb2Marshaller jaxb2Marshaller(String url) {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath(url);

        return jaxb2Marshaller;
    }
    
    @Bean
    public ClienteDatosUCIWSDL clienteDatosUCIWSDL() {
        Jaxb2Marshaller marshaller = jaxb2Marshaller("services");
        ClienteDatosUCIWSDL client = new ClienteDatosUCIWSDL();
        client.setDefaultUri("http://10.0.0.195:9763/services/PasarelaWS.PasarelaWSHttpSoap11Endpoint/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
     @Bean
    public ClienteAssetsUCIWSDL clienteAssetsUCIWSDL() {

        return new ClienteAssetsUCIWSDL();
    }
    
}
