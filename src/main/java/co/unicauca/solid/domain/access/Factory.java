package co.unicauca.solid.domain.access;

/**
 *
 * @author JUANDA
 */
public class Factory {
    
    private static Factory instance;
    
    private Factory(){
    }
    
    public static Factory getInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }
    
    public IUserRepository getUserRepository(String type){
        IUserRepository result = null;
        switch(type){
            case "default":result = new UserRepository();
        }
        return result;
    }
    
    public IProgramRepository getProgramRepository(String type){
        IProgramRepository result = null;
        switch(type){
            case "default":result = new ProgramRepository();
        }
        return result;
    }
    
    public IRolesRepository getRolesRepository(String type){
        IRolesRepository result = null;
        switch(type){
            case "default":result = new RolesRepository();
        }
        return result;
    }
    
        public IFormatARepository getFormatARepository(String type){
        FormatARepository result = null;
        switch(type){
            case "default":result = new FormatARepository();
        }
        return result;
    }
}
