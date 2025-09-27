package co.unicauca.domain;
import java.time.LocalDateTime;

/**
 *
 * @author royman
 */
public class Evaluation {
    private int id;
    private String qualification; 
    private String observations;
    private LocalDateTime createAt;
    private int idDegreeWork;
    private int idCoordinateer; 

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public int getIdDegreeWork() {
        return idDegreeWork;
    }

    public void setIdDegreeWork(int idDegreeWork) {
        this.idDegreeWork = idDegreeWork;
    }

    public int getIdCoordinateer() {
        return idCoordinateer;
    }

    public void setIdCoordinateer(int idCoordinateer) {
        this.idCoordinateer = idCoordinateer;
    }
    
    


   
    
    
}
