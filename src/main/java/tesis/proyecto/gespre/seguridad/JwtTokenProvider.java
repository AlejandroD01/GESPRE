package tesis.proyecto.gespre.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tesis.proyecto.gespre.excepciones.PrenominaAppException;
import tesis.proyecto.gespre.modelo.Usuario;
import tesis.proyecto.gespre.repositorio.IUsuarioRepo;

/**
 *
 * @author Ale
 */
@Component
public class JwtTokenProvider {

    @Autowired
    private IUsuarioRepo repo;
    

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMS;

    public String generarToken(Authentication authentication) {
        String username = authentication.getName();
        Optional<Usuario> user = repo.findByusuarios(username);
        System.out.println(" user:"+user.get().getId());
        Map<String, Object> claims = new LinkedHashMap<String, Object>();

        claims.put("roles", authentication.getAuthorities().stream().map(
                (autority) -> autority.getAuthority().replaceAll("ROLE_", "")
        ).toArray());

        claims.put("id", authentication.getAuthorities().stream().map(
                (autority) -> user.get().getId()).toArray());

        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMS);

        String token = Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
        } catch (MalformedJwtException ex) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "Token JWT no valida");
        } catch (ExpiredJwtException ex) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
        } catch (UnsupportedJwtException ex) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
        } catch (IllegalArgumentException ex) {
            throw new PrenominaAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
        }
    }
}
