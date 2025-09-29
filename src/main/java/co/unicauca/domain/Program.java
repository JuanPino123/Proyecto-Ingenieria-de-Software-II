package co.unicauca.domain;

/**
 *
 * @author Valentina
 */
public class Program {
    private int id;
    private String name;
    private int idFaculty;

    public Program() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
}
