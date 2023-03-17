
package tesis.proyecto.gespre.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.Predeterminado;

/**
 *
 * @author mapa
 */
@Repository
public interface IPredeterminadoRepo extends JpaRepository<Predeterminado, Integer> {//<tipo_de_entidad, tipo_de_id>

}
