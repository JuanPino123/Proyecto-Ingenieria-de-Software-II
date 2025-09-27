/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.domain;

/**
 *
 * @author Royman
 */
public class Teacher extends Person{
    private Users user;

    public Users getUser() {
        return user;
    }   
    
    
    public Teacher(String Name, String LastName, int phone) {
        super(Name, LastName, phone);
    }
    
}
