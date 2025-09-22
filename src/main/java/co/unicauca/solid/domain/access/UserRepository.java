/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.domain.access;

import co.unicauca.domain.Register;
import Connection.GenConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author JUANDA
 */
public class UserRepository implements IUserRepository{
    
    /**
     * Registra un usuario nuevo en la DB
     * @param newRegister Instancia de la clase registro con la informacion a registrar
     * @return 
     */
    @Override
    public boolean register(Register newRegister){
        try{
            registerUser(registerPerson(newRegister), newRegister);
            return true;
        }catch(SQLException ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return false;
    }
    
    private int registerPerson(Register newRegister) throws SQLException{
        try{
            String SQL = "INSERT INTO users ( name, lastName, phone, programa, email, password, rol ) "+
                    "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newRegister.getNombre());
            pstmt.setString(2, newRegister.getApellido());
            if(newRegister.getNumTel() != null){pstmt.setLong(3, newRegister.getNumTel());
            }else{pstmt.setNull(6, Types.BIGINT);}
            //pstmt.setString(4, newRegister.getPrograma());
            pstmt.setString(5, newRegister.getCorreo());
            pstmt.setString(6, newRegister.getContra());
            pstmt.setString(7, newRegister.getRol());
            pstmt.executeUpdate();
            
             ResultSet rs = pstmt.getGeneratedKeys();
            int generatedId = -1;
            if (rs.next()) {
                generatedId = rs.getInt(1); // Primera columna contiene el id
            }
            
            GenConnection.disconnect();
            return generatedId;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    private int registerUser(int idPerson, Register newRegister) throws SQLException{
        try{
            String SQL = "INSERT INTO users ( nombres, apellidos, celular, programa, email, password, rol ) "+
                    "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newRegister.getNombre());
            pstmt.setString(2, newRegister.getApellido());
            if(newRegister.getNumTel() != null){pstmt.setLong(3, newRegister.getNumTel());
            }else{pstmt.setNull(6, Types.BIGINT);}
            //pstmt.setString(4, newRegister.getPrograma());
            pstmt.setString(5, newRegister.getCorreo());
            pstmt.setString(6, newRegister.getContra());
            pstmt.setString(7, newRegister.getRol());
            pstmt.executeUpdate();
            
             ResultSet rs = pstmt.getGeneratedKeys();
            int generatedId = -1;
            if (rs.next()) {
                generatedId = rs.getInt(1); // Primera columna contiene el id
            }
            
            GenConnection.disconnect();
            return generatedId;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    
    /**
     * Revisa si ya existe un usuario con el correo introducido
     * @param email Correo
     * @return False si no existe, True si ya existe
     */
    @Override
    public boolean checkUser(String email){
        try{
            String SQL = "SELECT * FROM users WHERE ? = email";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setString(1, email);
            return pstmt.execute();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
    
    /**
     * Borra un usuario dado un correo, usado para pruebas principalmente
     * @param email Correo del usuario a probar
     * @return 
     */
    @Override
    public boolean deleteUserByEmail(String email){
        try{
            String SQL = "DELETE FROM users WHERE email = ?";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setString(1, email);
            int affecterRows = pstmt.executeUpdate();
            return affecterRows > 0;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
}
