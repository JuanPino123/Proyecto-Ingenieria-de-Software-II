/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.UI;

import co.unicauca.domain.Program;
import co.unicauca.solid.domain.access.Factory;
import co.unicauca.solid.domain.access.IUserRepository;
import co.unicauca.domain.Register;
import co.unicauca.domain.utilities.Cifrador;
import co.unicauca.domain.utilities.clsExceptions;
import co.unicauca.solid.domain.access.IProgramRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *Clase que se encarga de la administracion de la interfaz de la aplicacion
 * @author JUANDA
 */
public class Controller {
    
    private static JFrame Frame;
    
    
    /**
     * Inicializa la aplicacion
     */
    public static void initializeApp(){
        if(Frame==null)initialize("Administrador de Proyectos de Grado");
    }
    
    //<editor-fold defaultstate="collapsed" desc="Controladores de la presentacion">
    /**
     * Abre un panel basico, usado para el menu de seleccion de login/registro
     * @param panelName Nombre del panel inicial
     */
    private static void initialize(String panelName){
        Frame = new JFrame(panelName);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(400, 300);
        Frame.add(new Selector());Frame.setVisible(true);
    }
    
    /**
     * Cambia el Panel en funcionamiento, usado para cambiar entre menús
     * @param Content El nuevo panel a usar
     */
    protected static void OpenPanel(JPanel Content){
        Frame.setSize(400, 300);
        Frame.setContentPane(Content);
        Frame.validate();
    }
    
    protected static void setSize(int width, int height){
        Frame.setSize(width, height);
        Frame.validate();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones de registro">
    /**
     * Se encarga de verificar los datos introducidos
     * @param nom Nombre
     * @param ape Apellido
     * @param email Correo
     * @param contra Contraseña
     * @param contra2 Contraseña de confirmacion
     * @return Retorna True si los datos son validos, False si no es asi
     */
    protected static boolean verificacionDatos(String nom, String ape, String email, char[] contra, char[] contra2){
        //Se verifica que el correo este correctamente formateado
        Pattern pttCorreo = Pattern.compile("@unicauca.edu.co", Pattern.CASE_INSENSITIVE);
        boolean verCorreo = pttCorreo.matcher(email).find();
        //Se verifica que la contraseña este correctamente formateada y que la de confirmacion sea igual
        Pattern pttContra = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");
        boolean verContra =(pttContra.matcher(new String(contra)).find() &&
                pttContra.matcher(new String(contra2)).find() &&
                (new String(contra).equals(new String(contra2))));
        //Verificacion final que no sean nulos y sean validos
        System.gc();
        if(nom.isEmpty() || ape.isEmpty() || !verCorreo || !verContra){
            return false;//Si algun dato obligatorio es nulo o no-valido
        }else{return true;}//Todos los datos son validos
    }
    
    /**
     * Hace el registro de un nuevo usuario
     * @param nom Nombre
     * @param ape Apellido(s)
     * @param email Correo
     * @param rol Rol
     * @param programa Programa
     * @param numCel Numero de calular
     * @param contra Contraseña
     * @param contra2 Contraseña de confirmacion
     */
    public static void registrar(String nom, String ape, String email, String rol, Program programa, Long numCel, char[] contra, char[] contra2){
        try{
            IUserRepository repository = Factory.getInstance().getUserRepository("default");
            if(!Controller.verificacionDatos(nom, ape, email, contra, contra2)){new clsExceptions("Datos no validos, verifique que los datos ingresados sean correctos", "Correo Invalido");}
            if(repository.checkUser(email)){new clsExceptions("Este usuario ya existe", "Usario existente");}
                String contraCif = Cifrador.base64Converter(Cifrador.cifrarContrasena(contra));
                Register Registro = new Register(nom, ape, email, rol, programa, numCel, contraCif);
                if(repository.register(Registro)){
                    System.out.println("Usuario registrado");
                    
                }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error inesperado:" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static List<Program> getAllProgram(){
        try {
            var repository = Factory.getInstance().getProgramRepository("default");
            return repository.getAll();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado:" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return List.of();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones de Login">
    
    //</editor-fold>
    
    /**
     * Funcion tipo wrapper para pruebas unitarias
     * @param nom
     * @param ape
     * @param email
     * @param rol
     * @param programa
     * @param numCel
     * @param contra
     * @param contra2 
     */
    @Deprecated
    public static void wrapperRegistrar(String nom, String ape, String email, String rol, Program programa, Long numCel, char[] contra, char[] contra2){
        registrar(nom, ape, email, rol, programa, numCel, contra, contra2);
    }
    
}
