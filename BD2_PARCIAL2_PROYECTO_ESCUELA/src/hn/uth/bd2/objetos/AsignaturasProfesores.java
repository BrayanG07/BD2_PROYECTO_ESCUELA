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
public class AsignaturasProfesores {
    private int idProfesor;
    private String nombreProfesor;
    private int idAsignatura;
    private String nombreAsignatura;
    private int idGrado;
    private String nombreGrado;
    private String seccion;

    public AsignaturasProfesores(int idProfesor, String nombreProfesor, int idAsignatura, String nombreAsignatura, int idGrado, String nombreGrado, String seccion) {
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.idGrado = idGrado;
        this.nombreGrado = nombreGrado;
        this.seccion = seccion;
    }

    public AsignaturasProfesores() {
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
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

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
}
