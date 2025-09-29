package co.unicauca.domain;

/**
 *
 * @author Valentina
 */
public enum TypeRoles {
    ESTUDIANTE,
    DIRECTOR,
    COORDINADOR;

    public static TypeRoles fromString(String value) {
        for (TypeRoles r : TypeRoles.values()) {
            if (r.name().equalsIgnoreCase(value)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Rol no válido: " + value);
    }
}
