/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServicesTesters;

import co.unicauca.solid.domain.services.Login;
import co.unicauca.domain.utilities.Cifrador;
import co.unicauca.solid.domain.access.IUserRepository;
import co.unicauca.solid.domain.access.Factory;
import co.unicauca.domain.Register;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author JUANDA
 */
public class testLogin {
    
    private final IUserRepository testRepository = Factory.getInstance().getRepository("default");
    private final String testCorreo = "test@unicauca.edu.co";//Uselo para cada vez que se hace una prueba
    private final char[] testContra = "Pru3b@123".toCharArray();
    
    @BeforeEach
    void setup() throws Exception{
        if(testRepository.deleteUserByEmail(testCorreo)){
            System.out.println("Ususario de prueba eliminado correctamente");
        }else{
            System.out.println("Usuario de prueba no existe");
        }
        Register testRegister = new Register(
                "Jhon", "Doe",
                testCorreo,
                "Estudiante", "Ingenieria de Sistemas",
                3014155062L,
                Cifrador.base64Converter(Cifrador.cifrarContrasena(testContra)));
            if(testRepository.register(testRegister))System.out.println("Usuario de prueba creado correctamente");
    }
    
    @Test
    public void testLogin(){
        Assertions.assertTrue(Login.LogIn(testCorreo, testContra));
    }
}
