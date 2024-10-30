package com.utad.student;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alumno")
public class Alumno {


    private String nombre;

    private String apellido;

    private String curso;

    private String dni;

    public Alumno() {}

    public Alumno(String nombre, String apellido, String curso, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.dni = dni;
    }
    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @XmlElement
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    @XmlElement
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
