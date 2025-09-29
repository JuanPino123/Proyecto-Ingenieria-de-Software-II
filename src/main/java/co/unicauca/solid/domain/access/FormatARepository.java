package co.unicauca.solid.domain.access;

import Connection.GenConnection;
import co.unicauca.domain.DegreeWorkStatus;
import co.unicauca.domain.FilesHistory;
import co.unicauca.domain.FormatA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valentina
 */
public class FormatARepository implements IFormatARepository {

    private final List<FormatA> formats = new ArrayList<>();

    /**
     * Guarda el archivo 'Formato A' y la 'Carta de aceptacion' (si la hay)
     *
     * @param idTeacher
     * @param formatA
     * @return
     */
    @Override
    public boolean register(int idTeacher, FormatA formatA) {
        try {
            int idFormatA = registerFormatA(idTeacher, formatA);
            registerFile(idFormatA, "FORMATO A", formatA.getFormatA());
            if (formatA.getAcceptanceLetter() != null) {
                registerFile(idFormatA, "CARTA ACEPTACION", formatA.getAcceptanceLetter());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<FormatA> getAll() {
        List<FormatA> formatos = new ArrayList<>();
        String sql = "SELECT id, title, modality, createAt, director, codirector, generalObjective, specificObjectives, idTeacher FROM FormatA";

        try (Connection conn = GenConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                FormatA f = new FormatA();
                f.setId(rs.getInt("id"));
                f.setTitle(rs.getString("title"));
                f.setModality(rs.getString("modality"));

                // 🔹 Convertir String → LocalDate
                String dateStr = rs.getString("createAt");
                if (dateStr != null) {
                    f.setCreateAt(LocalDate.parse(dateStr));
                }

                f.setDirector(rs.getString("director"));
                f.setCodirector(rs.getString("codirector"));
                f.setGeneralObjective(rs.getString("generalObjective"));
                f.setSpecificObjectives(rs.getString("specificObjectives"));
                f.setIdTeacher(rs.getInt("idTeacher"));

                formatos.add(f);
            }

        } catch (Exception e) {
            System.out.println("Error cargando formatos: " + e.getMessage());
        }
        return formatos;
    }

    /**
     * Registra un nuevo Formato A en la base de datos y devuelve el id
     * autogenerado de ese registro.
     *
     * @param idTeacher
     * @param formatA
     * @return
     * @throws SQLException
     */
    private int registerFormatA(int idTeacher, FormatA formatA) throws SQLException {
        try {
            if (formatA.getStatus() == null) {
            formatA.setStatus(DegreeWorkStatus.PENDIENTE);
        }
            String SQL = "INSERT INTO FormatA ( title, modality, createAt, director, codirector, generalObjective, specificObjectives, idTeacher, status ) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, formatA.getTitle());
            pstmt.setString(2, formatA.getModality());
            pstmt.setString(3, formatA.getCreateAt().format(DateTimeFormatter.ISO_DATE));
            pstmt.setString(4, formatA.getDirector());
            pstmt.setString(5, formatA.getCodirector());
            pstmt.setString(6, formatA.getGeneralObjective());
            pstmt.setString(7, formatA.getSpecificObjectives());
            pstmt.setInt(8, idTeacher);
            pstmt.setString(9, formatA.getStatus().name());  

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

    /**
     * Guarda los datos del archivo (Sirve para el versionamiento)
     *
     * @param idFormatA
     * @param type
     * @param filesHistory
     * @return
     * @throws SQLException
     */
    private boolean registerFile(int idFormatA, String type, FilesHistory filesHistory) throws SQLException {
        try {
            String SQL = "INSERT INTO FILES_HISTORY ( fileUrl, uploadDate, idFormatA, typeFile ) "
                    + "VALUES ( ?, ?, ?, ? )";
            GenConnection.connect();
            PreparedStatement pstmt = GenConnection.conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            LocalDate date = LocalDate.now();
            pstmt.setString(1, filesHistory.getFileUrl());
            pstmt.setDate(2, java.sql.Date.valueOf(date));
            pstmt.setInt(3, idFormatA);
            pstmt.setString(4, type);

            pstmt.execute();

            GenConnection.disconnect();
            return true;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void updateEvaluation(int formatAId, DegreeWorkStatus status, String observaciones) throws SQLException {
        String sql = "UPDATE FormatA SET status = ?, observaciones = ? WHERE id = ?";

        try (Connection conn = GenConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status.name());      // Guarda "APROBADO" o "RECHAZADO"
            pstmt.setString(2, observaciones);      // Observaciones escritas
            pstmt.setInt(3, formatAId);             // ID del registro a actualizar

            pstmt.executeUpdate();
        }
    }

}
