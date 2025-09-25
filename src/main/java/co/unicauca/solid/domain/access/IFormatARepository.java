package co.unicauca.solid.domain.access;

import co.unicauca.domain.FormatA;


/**
 *Metodos disponibles desde el repositorio de usuarios
 * @author Valentina
 */
public interface IFormatARepository {
    boolean register(int idTeacher, FormatA formatA);
    
}
