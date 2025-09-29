package co.unicauca.domain;

/**
 * Clase que representa a un usuario
 * @author Valentina
 */
public class Users {
    private int id;
    private String email;
    private String password;
    private int idPerson;
    private Roles role; 

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public TypeRoles getRole() {
        return role.getRol();
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
