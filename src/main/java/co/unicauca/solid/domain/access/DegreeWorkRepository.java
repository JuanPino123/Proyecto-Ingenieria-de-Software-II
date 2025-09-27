/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.domain.access;

import Connection.GenConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author royman
 */
public class DegreeWorkRepository {
    
    public void updateStatus(int idDegreeWork, String status) {
    String sql = "UPDATE degreework SET status = ? WHERE id = ?";
    try {
        GenConnection.connect();
            PreparedStatement ps = GenConnection.conn.prepareStatement(sql);
        ps.setString(1, status);
        ps.setInt(2, idDegreeWork);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
}
