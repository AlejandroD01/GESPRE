
package tesis.proyecto.gespre.repositorio;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.Asistencia;


/**
 *
 * @author mapa
 */
@Repository
public interface IAsistenciaRepo extends JpaRepository<Asistencia, Integer> {//<tipo_de_entidad, tipo_de_id>
}
