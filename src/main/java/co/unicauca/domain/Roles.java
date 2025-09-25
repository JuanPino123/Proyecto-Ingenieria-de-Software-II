package co.unicauca.domain;

/**
 *
 * @author Valentina
 */
public class Roles {
    private int id;
    private TypeRoles rol;

    public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeRoles getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = TypeRoles.fromString(rol);
    }

    @Override
    public String toString() {
        return rol.name();
    }
    
}
