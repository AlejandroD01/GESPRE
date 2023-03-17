
package tesis.proyecto.gespre.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.Incidencia;


/**
 *
 * @author mapa
 */
@Repository
public interface IIncidenciaRepo extends JpaRepository<Incidencia, Integer> {//<tipo_de_entidad, tipo_de_id>
}
