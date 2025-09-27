/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.domain;

/**
 *
 * @author Royman
 */
public abstract class Person {
    protected String Name;
    protected String LastName;
    protected int phone;
    protected Users user;

    public Person(String Name, String LastName, int phone) {
        this.Name = Name;
        this.LastName = LastName;
        this.phone = phone;
    }
    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Users getUser() {
        return user;
    }

  
    
    
}
