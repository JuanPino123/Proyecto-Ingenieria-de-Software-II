package co.unicauca.solid.domain.access;

import co.unicauca.domain.Register;
import co.unicauca.domain.Users;

/**
 *Metodos disponibles desde el repositorio de usuarios
 * @author JUANDA
 */
public interface IUserRepository {
    boolean register(Register newRegister);
    boolean deleteUserByEmail(String email);
    boolean checkUser(String email);
    Users getByEmail(String email);
    public int getIdTeacherByEmail(String email);
}
