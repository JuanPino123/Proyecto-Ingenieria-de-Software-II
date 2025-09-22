package co.unicauca.solid.domain.access;

import co.unicauca.domain.Register;
import Connection.GenConnection;
import co.unicauca.domain.TypeRoles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
            int idPerson = registerPerson(newRegister);
            int idUser = registerUser(idPerson, newRegister);
            registerRolesUser(idUser, newRegister.getRoles().getId());
            
            if (newRegister.getRoles().getRol().equals(TypeRoles.ESTUDIANTE)) {
                registerStudent(idPerson, newRegister.getPrograma().getId());
            }else if(List.of(TypeRoles.COORDINADOR, TypeRoles.DIRECTOR).contains(newRegister.getRoles().getRol())){
                registerTeacher(idPerson);
            }
            
            return true;
        }catch(SQLException ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return false;
    }
    
    private int registerPerson(Register newRegister) throws SQLException{
        try{
            String SQL = "INSERT INTO PERSON ( name, lastName, phone ) "+
                    "VALUES ( ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newRegister.getNombre());
            pstmt.setString(2, newRegister.getApellido());
            if(newRegister.getNumTel() != null){
                pstmt.setLong(3, newRegister.getNumTel());
            }
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
            String SQL = "INSERT INTO users ( email, password, idPerson ) "+
                    "VALUES ( ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newRegister.getCorreo());
            pstmt.setString(2, newRegister.getContra());
            pstmt.setInt(3, idPerson);
           
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            int generatedId = -1;
            if (rs.next()) {
                generatedId = rs.getInt(1); 
            }
            
            GenConnection.disconnect();
            return generatedId;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    private boolean registerRolesUser(int idUser, int idRol) throws SQLException{
        try{
            String SQL = "INSERT INTO users_roles ( idUser, idRol ) "+
                    "VALUES ( ?, ?)";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, idRol);
           
            pstmt.execute();
            
            GenConnection.disconnect();
            return true;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    private boolean registerStudent(int idPerson, int idProgram) throws SQLException{
        try{
            String SQL = "INSERT INTO student ( idPerson, idProgram ) "+
                    "VALUES ( ?, ?)";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setInt(1, idPerson);
            pstmt.setInt(2, idProgram);
            pstmt.execute();
            
            GenConnection.disconnect();
            return true;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    private boolean registerTeacher(int idPerson) throws SQLException{
        try{
            String SQL = "INSERT INTO teacher ( idPerson ) "+
                    "VALUES ( ?)";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setInt(1, idPerson);
            pstmt.execute();
            
            GenConnection.disconnect();
            return true;
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
