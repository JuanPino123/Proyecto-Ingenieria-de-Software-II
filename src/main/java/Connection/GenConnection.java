package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *Clase encargada de la coneccion general a la DB
 * @author JUANDA
 */
public class GenConnection {
    public static Connection conn;
    
    /**
     * Conecta a la base de datos, por favor modifique la URL de ser necesario
     */
    public static void connect(){
        String url="jdbc:sqlite:C:/Users/JUANDA/Documents/Practica#2/unicauca_tg.db";
        //Conexion a la base de datos
        try{
            conn = DriverManager.getConnection(url);
        }catch(Exception ex){
           System.out.println("Error: " + ex.getMessage()); 
        }
    }
    
    /**
     * Desconecta de la base de datos
     */
    public static void disconnect(){
        try{
            if(conn!=null)conn.close();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
