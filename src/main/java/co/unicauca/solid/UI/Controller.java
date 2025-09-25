package co.unicauca.solid.UI;

import co.unicauca.domain.FormatA;
import co.unicauca.domain.Program;
import co.unicauca.solid.domain.access.Factory;
import co.unicauca.solid.domain.access.IUserRepository;
import co.unicauca.domain.Register;
import co.unicauca.domain.Roles;
import co.unicauca.domain.utilities.Cifrador;
import co.unicauca.domain.utilities.clsExceptions;
import co.unicauca.solid.domain.access.IFormatARepository;
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
        Frame.setSize(600, 500);
        Frame.add(new Selector());Frame.setVisible(true);
        Frame.setLocationRelativeTo(null);
    }
    
    /**
     * Cambia el Panel en funcionamiento, usado para cambiar entre menús
     * @param Content El nuevo panel a usar
     */
    protected static void OpenPanel(JPanel Content){
        Frame.setSize(600, 500);
        Frame.setContentPane(Content);
        Frame.validate();
        Frame.setLocationRelativeTo(null);
    }
    
    protected static void setSize(int width, int height){
        Frame.setSize(width, height);
        Frame.setLocationRelativeTo(null);
        Frame.validate();
    }
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

    public static void registrar(String nom, String ape, String email, Roles rol, Program programa, Long numCel, char[] contra, char[] contra2){
    try{
        IUserRepository repository = Factory.getInstance().getUserRepository("default");

        if(!Controller.verificacionDatos(nom, ape, email, contra, contra2)){
            JOptionPane.showMessageDialog(null, "Datos no válidos, verifique que los datos ingresados sean correctos", "Correo inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(repository.checkUser(email)){
            JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Usuario existente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String contraCif = Cifrador.base64Converter(Cifrador.cifrarContrasena(contra));
        Register Registro = new Register(nom, ape, email, rol, programa, numCel, contraCif);

        if(repository.register(Registro)){
            JOptionPane.showMessageDialog(null, "Usuario registrado", "INFO", JOptionPane.INFORMATION_MESSAGE);
            Controller.OpenPanel(new Selector());
            System.out.println("Usuario registrado");
        }

    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     * getAll
     * @return 
     */
    public static List<Program> getAllProgram(){
        try {
            var repository = Factory.getInstance().getProgramRepository("default");
            return repository.getAll();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado:" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return List.of();
    }
    public static List<Roles> getAllRoles(){
        try {
            var repository = Factory.getInstance().getRolesRepository("default");
            return repository.getAll();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado:" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return List.of();
    }    
    /**
     * login
     * @param email
     * @param contra 
     */
    public static void login(String email, char[] contra){
        try {
            String contraCif = Cifrador.base64Converter(Cifrador.cifrarContrasena(contra));
            IUserRepository repository = Factory.getInstance().getUserRepository("default");
            if (repository.checkUser(email)) {
                var user = repository.getByEmail(email);
                var idTeacher = repository.getIdTeacherByEmail(email);
                if (user.getPassword().equals(contraCif)) {
                    var uploadFormatADirector = UploadFormatADirector.getInstance();
                    uploadFormatADirector.setIdTeacher(idTeacher);
                    Controller.OpenPanel(new Menu());
                }else{
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                String[] options = {"Registrarse", "OK"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "El usuario no existe",
                        "ERROR",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        options,
                        options[1]
                );

                if (choice == 0) {
                    Controller.OpenPanel(new RegisterForm());
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado:" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
    public static boolean saveFormatA(int idTeacher, FormatA formatA){
        IFormatARepository repository = Factory.getInstance().getFormatARepository("default");
        repository.register(idTeacher, formatA);
        return repository.register(idTeacher, formatA);
    }
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
    public static void wrapperRegistrar(String nom, String ape, String email, Roles rol, Program programa, Long numCel, char[] contra, char[] contra2){
        registrar(nom, ape, email, rol, programa, numCel, contra, contra2);
    }
    
}
