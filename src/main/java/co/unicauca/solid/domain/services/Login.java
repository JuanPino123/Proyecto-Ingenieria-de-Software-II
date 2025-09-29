package co.unicauca.solid.domain.services;

import co.unicauca.domain.utilities.clsExceptions;
import co.unicauca.domain.utilities.Cifrador;
import Connection.GenConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *Se dedica a loggear a los usuarios
 * @author JUANDA
 */
public class Login{
    
    private static String SQL = "SELECT * FROM users WHERE email = ? AND password = ?";//SQL para la busqueda del login
    /**
     * Logea a un usuario
     * @param corr Correo
     * @param con Contraseña
     * @return true si el login es valido, false si no lo es
     */
    public static boolean LogIn(String corr, char[] con){
        try{
            
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            String CyCon = Cifrador.base64Converter(Cifrador.cifrarContrasena(con));
            pstmt.setString(1, corr);
            pstmt.setString(2, CyCon);
            ResultSet result = pstmt.executeQuery();
            if(result.first()){return true;}
            return false;
        }catch(Exception ex){
            clsExceptions.ErrorMSG(ex);
        }
        return false;
    }
}
