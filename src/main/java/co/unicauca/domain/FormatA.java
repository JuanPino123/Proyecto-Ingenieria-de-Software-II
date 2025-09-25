package co.unicauca.domain;

import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author Valentina
 */
public class FormatA {
    private int id;
    private String title;
    private String modality;
    private LocalDate createAt;
    private String director;
    private String codirector;
    private String generalObjective;
    private String specificObjectives;
    private FilesHistory formatA;
    private FilesHistory acceptanceLetter;
    private int idTeacher;
    

    // Constructor vacío (obligatorio para frameworks como JDBC, Hibernate, etc.)
    public FormatA() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }
    

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCodirector() {
        return codirector;
    }

    public void setCodirector(String codirector) {
        this.codirector = codirector;
    }

    public String getGeneralObjective() {
        return generalObjective;
    }

    public void setGeneralObjective(String generalObjective) {
        this.generalObjective = generalObjective;
    }

    public String getSpecificObjectives() {
        return specificObjectives;
    }

    public void setSpecificObjectives(String specificObjectives) {
        this.specificObjectives = specificObjectives;
    }

    public FilesHistory getFormatA() {
        return formatA;
    }

    public void setFormatA(FilesHistory formatA) {
        this.formatA = formatA;
    }

    public FilesHistory getAcceptanceLetter() {
        return acceptanceLetter;
    }

    public void setAcceptanceLetter(FilesHistory acceptanceLetter) {
        this.acceptanceLetter = acceptanceLetter;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }
    
    
    
}
