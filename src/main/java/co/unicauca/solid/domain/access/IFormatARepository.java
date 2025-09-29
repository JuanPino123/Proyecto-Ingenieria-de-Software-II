package co.unicauca.solid.domain.access;

import co.unicauca.domain.FormatA;
import java.util.List;


/**
 *Metodos disponibles desde el repositorio de usuarios
 * @author Valentina
 */
public interface IFormatARepository {
    boolean register(int idTeacher, FormatA formatA);
    List<FormatA> getAll();

}
