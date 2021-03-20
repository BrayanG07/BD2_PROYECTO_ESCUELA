/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.objetos;

/**
 *
 * @author Buddys
 */
public class Profesores {

    private int idProfesor;
    private String nombreProfesor;

    public Profesores() {
    }

    public Profesores(int idProfesor, String nombreProfesor) {
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    @Override
    public String toString() {
        return "AsignaturasProfesores{" + "idProfesor=" + idProfesor + ", nombreProfesor=" + nombreProfesor + '}';
    }
}
