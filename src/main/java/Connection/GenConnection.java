package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de la coneccion general a la DB
 *
 * @author JUANDA
 */
public class GenConnection {

    public static Connection conn;

    /**
     * Conecta a la base de datos, por favor modifique la URL de ser necesario
     */
    public static void connect() {
        String url = "jdbc:sqlite:C:\\Users\\jusev\\Desktop\\ProyectoSoftwareII\\Proyecto-Ingenieria-de-Software-II\\mydb.db";
        //Conexion a la base de datos
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Desconecta de la base de datos
     */
    public static void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\jusev\\Desktop\\ProyectoSoftwareII\\Proyecto-Ingenieria-de-Software-II\\mydb.db";
        return DriverManager.getConnection(url);
    }

}
