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

    /**
     * @Override public Users getByEmail(String email) { Users user = null; try
     * { String SQL = "SELECT * FROM users WHERE email = ?";
     * GenConnection.connect(); PreparedStatement pstmt =
     * GenConnection.conn.prepareStatement(SQL); pstmt.setString(1, email);
     *
     * ResultSet rs = pstmt.executeQuery(); if (rs.next()) { user = new Users();
     * user.setId(rs.getInt("id")); user.setEmail(rs.getString("email"));
     * user.setPassword(rs.getString("password"));
     * user.setIdPerson(rs.getInt("idPerson")); }
     *
     * rs.close(); pstmt.close(); GenConnection.conn.close(); } catch (Exception
     * ex) { System.out.println("Error: " + ex.getMessage()); } return user; }
     */
    @Override
    public Users getByEmail(String email) {
        Users user = null;
        try {
            System.out.println("🔍 Buscando usuario con email: " + email);

            // PRIMERO: Buscar usuario básico
            String userSQL = "SELECT id, email, password, idPerson FROM users WHERE email = ?";
            GenConnection.connect();

            PreparedStatement userStmt = GenConnection.conn.prepareStatement(userSQL);
            userStmt.setString(1, email);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                System.out.println("✅ Usuario encontrado en BD");
                user = new Users();
                user.setId(userRs.getInt("id"));
                user.setEmail(userRs.getString("email"));
                user.setPassword(userRs.getString("password"));
                user.setIdPerson(userRs.getInt("idPerson"));
                System.out.println("📝 Datos usuario - ID: " + user.getId() + ", Email: " + user.getEmail());

                // SEGUNDO: Buscar el rol - USANDO LA COLUMNA CORRECTA 'rol'
                String roleSQL = "SELECT r.id, r.rol FROM roles r "
                        + // ← COLUMNA CORRECTA: 'rol'
                        "INNER JOIN users_roles ur ON r.id = ur.idRol "
                        + "WHERE ur.idUser = ?";

                PreparedStatement roleStmt = GenConnection.conn.prepareStatement(roleSQL);
                roleStmt.setInt(1, user.getId());
                ResultSet roleRs = roleStmt.executeQuery();

                if (roleRs.next()) {
                    System.out.println("✅ Rol encontrado");
                    co.unicauca.domain.Roles role = new co.unicauca.domain.Roles();
                    role.setId(roleRs.getInt("id"));
                    role.setRol(roleRs.getString("rol"));  // ← COLUMNA CORRECTA: 'rol'
                    user.setRole(role);
                    System.out.println("🎭 Rol asignado: " + role.getRol());
                } else {
                    System.out.println("⚠️ Usuario NO tiene rol asignado en users_roles");

                    // Debug: verificar qué hay en users_roles
                    String debugSQL = "SELECT * FROM users_roles WHERE idUser = ?";
                    PreparedStatement debugStmt = GenConnection.conn.prepareStatement(debugSQL);
                    debugStmt.setInt(1, user.getId());
                    ResultSet debugRs = debugStmt.executeQuery();

                    if (!debugRs.next()) {
                        System.out.println("❌ No hay registro en users_roles para este usuario");
                    } else {
                        System.out.println("✅ Hay registro en users_roles: idRol = " + debugRs.getInt("idRol"));
                    }
                    debugRs.close();
                    debugStmt.close();
                }

                roleRs.close();
                roleStmt.close();
            } else {
                System.out.println("❌ Usuario NO encontrado en la tabla 'users'");
            }

            userRs.close();
            userStmt.close();
            GenConnection.conn.close();

        } catch (Exception ex) {
            System.out.println("❌ Error en getByEmail: " + ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println("📤 Retornando usuario: " + (user != null ? "NO null" : "NULL"));
        return user;
    }

    /**
     * Obtiene el idTeacher dado un correo | Usado para el director
     *
     * @param email
     * @return
     */
    public int getIdTeacherByEmail(String email) {
        int idTeacher = -1;

        try {
            String SQL = "SELECT t.id AS idTeacher "
                    + "FROM users u "
                    + "INNER JOIN person p ON u.idPerson = p.id "
                    + "INNER JOIN teacher t ON p.id = t.idPerson "
                    + "WHERE u.email = ?";
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
