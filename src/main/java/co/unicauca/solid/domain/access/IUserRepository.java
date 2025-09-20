/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.domain.access;

import co.unicauca.domain.Register;

/**
 *Metodos disponibles desde el repositorio de usuarios
 * @author JUANDA
 */
public interface IUserRepository {
    boolean register(Register newRegister);
    boolean deleteUserByEmail(String email);
    boolean checkUser(String email);
}
