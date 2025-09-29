package co.unicauca.solid.domain.access;

import co.unicauca.domain.Program;
import java.util.List;


/**
 *Metodos disponibles desde el repositorio de usuarios
 * @author Valentina
 */
public interface IProgramRepository {
    List<Program> getAll();
}
