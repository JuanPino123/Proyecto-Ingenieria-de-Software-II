package co.unicauca.domain;


import co.unicauca.domain.utilities.Cifrador;
import java.sql.PreparedStatement;
import java.util.Arrays;
/**
 *
 * @author JUANDA
 */
public class Register implements AutoCloseable{
    
    private String nombre, apellido, correo, rol;//Valores usados por y para el registro
    private Program programa;
    private Long numTel;
    private String contra;//Contraseña ya cifrada
    
    /**
     * Constructor parametrizado, crea nueva instancia de Register
     * @param nom Nombre
     * @param ape Apellido
     * @param corr Correo
     * @param rol Rol
     * @param prog Programa
     * @param num Numero de telefono(puede ser nulo)
     * @param contra Contraseña sin cifrar
     */
    public Register(String nom, String ape, String corr, String rol, Program prog, Long num, String contra) throws Exception {
        super();
        this.nombre = nom;
        this.apellido = ape;
        this.correo = corr;
        this.rol = rol;
        this.programa = prog;
        this.numTel = num;
        this.contra = contra;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public Program getPrograma() {
        return programa;
    }

    public Long getNumTel() {
        if(numTel != null)return numTel;
        return null;
    }

    public String getContra() {
        return contra;
    }
    //</editor-fold>
    
    @Override
    public void close(){
        System.out.println("Registro completo");
    }
}