package co.unicauca.solid.domain.access;

import Connection.GenConnection;
import co.unicauca.domain.Program;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valentina
 */
public class ProgramRepository implements IProgramRepository{
    
    @Override
    public List<Program> getAll() {
        List<Program> programs = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM program";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Program program = new Program();
                program.setId(rs.getInt("id"));
                program.setName(rs.getString("name"));
                program.setIdFaculty(rs.getInt("idFaculty"));
                programs.add(program);
            }

            GenConnection.disconnect();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return programs;
    }
    
    
}
