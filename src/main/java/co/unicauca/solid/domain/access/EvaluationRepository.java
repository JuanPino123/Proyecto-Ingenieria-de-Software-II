/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.domain.access;
import java.sql.*;
import co.unicauca.domain.Evaluation;
import Connection.GenConnection;
import java.sql.PreparedStatement;

/**
 *
 * @author royman
 */
public class EvaluationRepository {
    public void insert(Evaluation evaluation) {
        String sql = "INSERT INTO evaluations (qualification, observations, createAt, iddegreework, idTeacher) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try {
            GenConnection.connect();
            PreparedStatement ps = GenConnection.conn.prepareStatement(sql);
            ps.setString(1, evaluation.getQualification());
            ps.setString(2, evaluation.getObservations());
            ps.setTimestamp(3, Timestamp.valueOf(evaluation.getCreateAt()));
            ps.setInt(4, evaluation.getIdDegreeWork());
            ps.setInt(5, evaluation.getIdCoordinateer());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
