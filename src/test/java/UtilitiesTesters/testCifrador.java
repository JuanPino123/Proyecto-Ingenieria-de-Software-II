/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UtilitiesTesters;

import co.unicauca.domain.utilities.Cifrador;//Clase a testear
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *Clase para probar el cifrador
 * @author JUANDA
 */
public class testCifrador {
    
    private final String contraaCifrar = "Hola";
    
    /**
     * Prueba si el cifrado da diferentes resultados
     * @throws Exception 
     */
    @Test
    void testCifrarYDescifarContrasena() throws Exception{
        
        String contraDes;
        //Ciframos la contraseña
        byte[] contraCif = Cifrador.cifrarContrasena(contraaCifrar.toCharArray());
        //Desciframos la contraseña
        contraDes = Cifrador.descifrarContrasena(contraCif);
        //Probamos que la contraseña si se cifro
        Assertions.assertNotEquals(contraaCifrar.getBytes("UTF-8"),contraCif);
        //Probamos que las 2 son iguales y si descifro correctamente
        Assertions.assertEquals(contraaCifrar, contraDes);
        System.out.println("Contrasena original: " + contraaCifrar +"\nContrasena descifrada: "+ contraDes);
    }
}
