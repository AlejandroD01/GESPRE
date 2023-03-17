/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.proyecto.gespre.clientewsdl;

import com.example.clientewsdl.generated.ASSET.Area;
import com.example.clientewsdl.generated.ASSET.AssetsWSService;
import com.example.clientewsdl.generated.ASSET.AssetsWSService_Impl;
import com.example.clientewsdl.generated.ASSET.Persona;
import java.rmi.RemoteException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.rpc.ServiceException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 *
 * @author mapa
 */
public class ClienteAssetsUCIWSDL extends WebServiceGatewaySupport {

    public ClienteAssetsUCIWSDL() {
        super();
        AceptAllSSLCertificate();
    }
    
    public Persona obtenerPersonaXCI(String ci) throws RemoteException {
        AssetsWSService service = new AssetsWSService_Impl();
        try {
            return service.getAssetsWSPort().obtenerPersonaDadoCIdentidad(ci);
        } catch (ServiceException ex) {
            Logger.getLogger(ClienteAssetsUCIWSDL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Persona[] obtenerPersonasDadoIdArea(String id){
        AssetsWSService service = new AssetsWSService_Impl();
        try {
            return service.getAssetsWSPort().obtenerPersonasDadoIdArea(id);
        } catch (ServiceException ex) {
            Logger.getLogger(ClienteAssetsUCIWSDL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteAssetsUCIWSDL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Area[] obtenerAreas(){
        AssetsWSService service = new AssetsWSService_Impl();
        try {
            return service.getAssetsWSPort().obtenerAreas();
        } catch (ServiceException ex) {
            Logger.getLogger(ClienteAssetsUCIWSDL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteAssetsUCIWSDL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static void AceptAllSSLCertificate() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

// Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
