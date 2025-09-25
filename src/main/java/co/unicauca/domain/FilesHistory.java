package co.unicauca.domain;

import java.time.LocalDate;

/**
 *
 * @author Valentina
 */

public class FilesHistory {
    private int id;
    private String fileUrl;
    private LocalDate uploadDate;
    private int idFormatA;
    private String fileName;

    public FilesHistory() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public void setUploadDate(LocalDate Date ) {
        this.uploadDate  = Date;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public int getIdFormatA() {
        return idFormatA;
    }

    public void setIdFormatA(int idFormatA) {
        this.idFormatA = idFormatA;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}
