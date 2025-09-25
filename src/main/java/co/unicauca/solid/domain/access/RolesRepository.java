package co.unicauca.solid.domain.access;

import Connection.GenConnection;
import co.unicauca.domain.Roles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valentina
 */
public class RolesRepository implements IRolesRepository{
    
    @Override
    public List<Roles> getAll() {
        var listRoles = new ArrayList<Roles>();

        try {
            String SQL = "SELECT * FROM roles";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                var rol = new Roles();
                rol.setId(rs.getInt("id"));
                rol.setRol(rs.getString("rol"));
                listRoles.add(rol);
            }

            GenConnection.disconnect();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return listRoles;
    }
    
    
}
