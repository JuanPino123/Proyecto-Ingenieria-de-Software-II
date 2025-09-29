/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.domain;

/**
 *
 * @author Royman
 */
public class Student extends Person {
    
    private Users user;
    
    public Student(String Name, String LastName, int phone) {
        super(Name, LastName, phone);
    }

    public Users getUser() {
        return user;
    }
    
    
}
