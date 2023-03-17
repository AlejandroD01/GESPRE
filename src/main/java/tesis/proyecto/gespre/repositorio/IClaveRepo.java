
package tesis.proyecto.gespre.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.proyecto.gespre.modelo.Clave;


/**
 *
 * @author mapa
 */
@Repository
public interface IClaveRepo extends JpaRepository<Clave, Integer> {//<tipo_de_entidad, tipo_de_id>
}
