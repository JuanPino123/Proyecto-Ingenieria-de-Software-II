/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccessTesters;

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
public class testUserRepository {
    
    private final IUserRepository testRepository = Factory.getInstance().getRepository("default");
    private final String testCorreo = "test@unicauca.edu.co";//Uselo para cada vez que se hace una prueba
    private final String testCorreoNN = "test2@unicauca.edu.co";//Uselo para cada vez que se hace una prueba
    
    @AfterEach @BeforeEach
    void setup(){
        if(testRepository.deleteUserByEmail(testCorreo)){
            System.out.println("Ususario de prueba eliminado correctamente");
        }else{System.out.println("Usuario de prueba no existe");}
    }
    
    @Test
    public void testRepository() throws Exception{
        Register testRegister = new Register(//Register de prueba
                "Jhon", "Doe",
                testCorreo,
                "Estudiante", "Ingenieria de Sistemas",
                3014155062L,
                "Pru3b@123");
        Assertions.assertTrue(testRepository.register(testRegister));
        Assertions.assertTrue(testRepository.checkUser(testRegister.getCorreo()));
    }
    
    @Test
    public void testRepositoryNoNumber() throws Exception{
        testRepository.deleteUserByEmail(testCorreoNN);
        Register testRegisterNN = new Register(//Register de prueba sin numero
                "Jane", "Doe",
                testCorreo,
                "Estudiante", "Ingenieria de Sistemas",
                null,
                "Pru3b@123");
        Assertions.assertTrue(testRepository.register(testRegisterNN));
        Assertions.assertTrue(testRepository.checkUser(testRegisterNN.getCorreo()));
    }
    
}
