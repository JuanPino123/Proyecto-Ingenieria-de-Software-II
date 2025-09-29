/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.domain;

import java.util.HashSet;

import java.util.logging.Logger;

/**
 *
 * @author Royman
 */
public class Coordinateer extends Person {
    private static final Logger logger = Logger.getLogger(Coordinateer.class.getName());
    private Users user;

    public Users getUser() {
        return user;
    }
      

    public Coordinateer(String Name, String LastName, int phone) {
        super(Name, LastName, phone);
    }
    
    public void EvaluateFormatA(DegreeWork proyect, DegreeWorkStatus decision, String observations){
        proyect.setStatus(decision);
        if(decision == DegreeWorkStatus.OBSERVACIONES){
            proyect.setObservaciones(observations);
        }else {
            proyect.setObservaciones("");
        }
        
    }
    
    
    
}
