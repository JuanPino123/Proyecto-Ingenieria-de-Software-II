package co.unicauca.solid.domain.access;

import co.unicauca.domain.Roles;
import java.util.List;


/**
 *Metodos disponibles desde el repositorio de usuarios
 * @author Valentina
 */
public interface IRolesRepository {
    List<Roles> getAll();
}
