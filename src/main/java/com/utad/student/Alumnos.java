package com.utad.student;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "alumnos")
public class Alumnos {

    private List<Alumno> alumnos;

    public Alumnos() {}

    public Alumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    @XmlElement(name = "alumno")
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
