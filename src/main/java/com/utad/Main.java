package com.utad;

public class Main {

    public static void main(String[] args) {

        AlumnoDAO dao = new AlumnoDAO();
        //dao.deleteAll();
        //dao.readInsertXML();
        //dao.readXML();
        dao.insertAlumnos(dao.readXML());
    }

}