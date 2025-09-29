package co.unicauca.domain.utilities;

/**
 *
 * @author JUANDA
 */
public class clsExceptions extends Exception{
    private String title;
    public clsExceptions(String message, String title){
        super(message);
        this.title=title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public static String ErrorMSG(Exception ex){
        return "Error: " + ex.getMessage();
    }
}
