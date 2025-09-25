package co.unicauca.solid.domain.access;

import co.unicauca.domain.Register;
import Connection.GenConnection;
import co.unicauca.domain.TypeRoles;
import co.unicauca.domain.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author JUANDA
 */
public class UserRepository implements IUserRepository {

    /**
     * Registra un usuario nuevo en la DB
     *
     * @param newRegister Instancia de la clase registro con la informacion a
     * registrar
     * @return
     */
    @Override
    public boolean register(Register newRegister) {
        try {
            int idPerson = registerPerson(newRegister);
            int idUser = registerUser(idPerson, newRegister);
            registerRolesUser(idUser, newRegister.getRoles().getId());

            if (newRegister.getRoles().getRol().equals(TypeRoles.ESTUDIANTE)) {
                registerStudent(idPerson, newRegister.getPrograma().getId());
            } else if (List.of(TypeRoles.COORDINADOR, TypeRoles.DIRECTOR).contains(newRegister.getRoles().getRol())) {
                registerTeacher(idPerson, newRegister.getPrograma().getId());
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return false;
    }

    private int registerPerson(Register newRegister) throws SQLException {
        try {
            String SQL = "INSERT INTO PERSON ( name, lastName, phone ) "
                    + "VALUES ( ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newRegister.getNombre());
            pstmt.setString(2, newRegister.getApellido());
            if (newRegister.getNumTel() != null) {
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
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private int registerUser(int idPerson, Register newRegister) throws SQLException {
        try {
            String SQL = "INSERT INTO users ( email, password, idPerson ) "
                    + "VALUES ( ?, ?, ? )";
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
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private boolean registerRolesUser(int idUser, int idRol) throws SQLException {
        try {
            String SQL = "INSERT INTO users_roles ( idUser, idRol ) "
                    + "VALUES ( ?, ?)";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, idRol);

            pstmt.execute();

            GenConnection.disconnect();
            return true;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private boolean registerStudent(int idPerson, int idProgram) throws SQLException {
        try {
            String SQL = "INSERT INTO student ( idPerson, idProgram ) "
                    + "VALUES ( ?, ?)";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setInt(1, idPerson);
            pstmt.setInt(2, idProgram);
            pstmt.execute();

            GenConnection.disconnect();
            return true;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private boolean registerTeacher(int idPerson, int idProgram) throws SQLException {
        try {
            String SQL = "INSERT INTO teacher ( idPerson, idProgram ) "
                    + "VALUES ( ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setInt(1, idPerson);
            pstmt.setInt(2, idProgram);
            pstmt.execute();

            GenConnection.disconnect();
            return true;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Revisa si ya existe un usuario con el correo introducido
     *
     * @param email Correo
     * @return False si no existe, True si ya existe
     */
    @Override
    public boolean checkUser(String email) {
        String SQL = "SELECT COUNT(*) FROM users WHERE email = ?";

        try {
            GenConnection.connect();
            try (PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL)) {
                pstmt.setString(1, email);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            GenConnection.disconnect(); // garantizado
        }
        return false;
    }

    /**
     * Borra un usuario dado un correo, usado para pruebas principalmente
     *
     * @param email Correo del usuario a probar
     * @return
     */
    @Override
    public boolean deleteUserByEmail(String email) {
        try {
            String SQL = "DELETE FROM users WHERE email = ?";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setString(1, email);
            int affecterRows = pstmt.executeUpdate();
            return affecterRows > 0;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public Users getByEmail(String email) {
        Users user = null;
        try {
            String SQL = "SELECT * FROM users WHERE email = ?";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdPerson(rs.getInt("idPerson"));
            }

            rs.close();
            pstmt.close();
            GenConnection.conn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return user;
    }
    
    /**
     * Obtiene el idTeacher dado un correo | Usado para el director
     * @param email
     * @return 
     */
    public int getIdTeacherByEmail(String email) {
        int idTeacher = -1;

        try {
            String SQL = "SELECT t.id AS idTeacher " +
                 "FROM users u " +
                 "INNER JOIN person p ON u.idPerson = p.id " +
                 "INNER JOIN teacher t ON p.id = t.idPerson " +
                 "WHERE u.email = ?";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idTeacher = rs.getInt("idTeacher");
            }

            rs.close();
            pstmt.close();
            GenConnection.conn.close();

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return idTeacher;
    }


}
