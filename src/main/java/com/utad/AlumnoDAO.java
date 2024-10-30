package com.utad;

import com.utad.student.Alumno;
import com.utad.student.Alumnos;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AlumnoDAO {

    private static final String XML_FILE = "src/main/java/com/utad/alumnos.xml";
    private Connection conn;

    public AlumnoDAO() {
        this.conn = DBConnection.getConnection();
    }

    public List<Alumno> readXML(){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Alumnos.class);
            Unmarshaller um = ctx.createUnmarshaller();
            File file = new File(XML_FILE);
            Alumnos alumnos = (Alumnos) um.unmarshal(file);

            for (Alumno alumno : alumnos.getAlumnos()) {
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Apellido: " + alumno.getApellido());
                System.out.println("Curso: " + alumno.getCurso());
                System.out.println("DNI: " + alumno.getDni());
                System.out.println();
            }
            return alumnos.getAlumnos();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertAlumnos(List<Alumno> alumnos) {
        int counter = 0;
        String selectQuery = "SELECT COUNT(*) FROM alumnos WHERE dni = ?";
        String insertQuery = "INSERT INTO alumnos (nombre, apellido, curso, dni) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            for (Alumno alumno : alumnos) {
                selectStmt.setString(1, alumno.getDni());
                ResultSet rs = selectStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    insertStmt.setString(1, alumno.getNombre());
                    insertStmt.setString(2, alumno.getApellido());
                    insertStmt.setString(3, alumno.getCurso());
                    insertStmt.setString(4, alumno.getDni());
                    insertStmt.executeUpdate();
                    counter++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total de alumnos insertados: " + counter);
    }

    public void deleteAll() {

        String deleteQuery = "DELETE FROM alumnos";
        String resetSequenceQuery = "ALTER SEQUENCE alumnos_id_seq RESTART WITH 1";
        try {
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.executeUpdate();

            PreparedStatement resetStmt = conn.prepareStatement(resetSequenceQuery);
            resetStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Se han eliminado todos los registros de la tabla alumnos");
    }
}

