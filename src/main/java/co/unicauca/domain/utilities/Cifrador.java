package co.unicauca.domain.utilities;

import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 *Utilidad encargada de las contraseñas, sea cifrado o descifrado
 * @author JUANDA
 */
public class Cifrador {
    
    //Transformes duh-duh, robots in disguise
    private final static String Transformador = "AES";
    
    /**
     * Genera la clave de cifrado basado en una frase basica
     * @param contra Contraseña
     * @return La clave Hasheada
     */
    private static SecretKey generarClave() throws Exception{
        //Convertimos el char[] a un byte
        byte[] fraseCifrado = "I CANT DO ANYTHING, IM IN A FUCKING WHEELCHAIR".getBytes("UTF-8");
        //Se saca el SHA-256
        MessageDigest hashedContra = MessageDigest.getInstance("SHA-256");
        fraseCifrado = hashedContra.digest(fraseCifrado);
        //Retorna la clave secreta
        return new SecretKeySpec(fraseCifrado, Transformador);
    }
    
    //<editor-fold desc="Cifradores y Descifradores">
    //<editor-fold defaultstate="Collapsed" desc="Cifrador y Descifrador AES">
    /**
     * 
     * @param contra
     * @return
     * @throws Exception 
     */
    private static byte[] cifrarAES(char[] contra)throws Exception{
        Cipher cifrado = Cipher.getInstance(Transformador);
        cifrado.init(Cipher.ENCRYPT_MODE, generarClave());
        return cifrado.doFinal(new String(contra).getBytes("UTF-8"));
    }
    
    /**
     * Descifra las contraseñas cifradas que se le dan
     * @param contra Contraseña a descifrar
     * @return Contraseña no cifrada (Aun hasheada pero meh, messi)
     */
    private static String descifrarAES(byte[] contra) throws Exception{
        Cipher cifrado = Cipher.getInstance(Transformador);
        cifrado.init(Cipher.DECRYPT_MODE, generarClave());
        return new String(cifrado.doFinal(contra), "UTF-8");
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="Collapsed" desc="Cifrador y Descifrador Contraseña">
    /**
     * Cifra el char[] que se le pasa, retornando un byte[]
     * @param contra Contraseña a cifrar
     * @return contraseña cifrada
     * @throws java.lang.Exception Da excepcion creo
     */
    public static byte[] cifrarContrasena(char[] contra) throws Exception{
        return cifrarAES(contra);
    }
    
    /**
     * Descifra la contrasena cifrada que se le pasa
     * @param contraCif
     * @return La contrasena descifrada, siendo esta la cadena original
     * @throws Exception Da excepcion, supongo
     */
    public static String descifrarContrasena(byte[] contraCif) throws Exception{
        return descifrarAES(contraCif);
    }
    //</editor-fold>
    //</editor-fold>
    
    //<editor-fold defaultstate="Collapsed" desc="Conversores base 64">
    /**
     * Convierte la clave cifrada en una cadena que se puede pasar a la DB
     * @param contraCif Contrasena cifrada
     * @return Una cadena que se puede usar en la DB
     */
    public static String base64Converter(byte[] contraCif){
       return Base64.getEncoder().encodeToString(contraCif);
    }
    
    /**
     * Convierte el codigo sacado de la DB en uno usable para el descifrador
     * @param cod Codigo en base 64
     * @return Byte que se ingreso a la DB originalmente
     */
    public static byte[] base64Converter(String cod){
        return Base64.getDecoder().decode(cod);
    }
    //</editor-fold>
}
