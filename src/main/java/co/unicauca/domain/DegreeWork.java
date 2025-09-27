/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.domain;


import java.util.List;

/**
 *
 * @author Royman
 */
public class DegreeWork {
    
    private int id;
    private String titulo;
    private Teacher teacher;
    private List<Student> students;
    private DegreeWorkStatus status;
    private Student student;
    private String observaciones;
    

    public DegreeWork(int id, String titulo, Teacher teacher, List<Student> students) {
        this.id = id;
        this.titulo = titulo;
        this.teacher = teacher;
        this.students = students;
        this.status = DegreeWorkStatus.PENDIENTE;
        this.observaciones = "";
    }

    public DegreeWorkStatus getStatus() {
        return status;
    }

    public void setStatus(DegreeWorkStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Teacher getTeacher() {
        return teacher;
    }   

    public List<Student> getStudents() {
        return students;
    }


       
    public String getEmailStudents (){
        StringBuilder sb = new StringBuilder();
        for (Student e : students) {
            sb.append(e.getUser().getEmail()).append(" ");
        }
        return sb.toString().trim();
    }

  
    
    
    

}
