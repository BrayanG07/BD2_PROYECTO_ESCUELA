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
public class Asignaturas {

    private int idAsignatura;
    private String nombreAsignatura;

    public Asignaturas() {
    }

    public Asignaturas(int idAsignatura, String nombreAsignatura) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
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

    @Override
    public String toString() {
        return "Asignaturas{" + "idAsignatura=" + idAsignatura + ", nombreAsignatura=" + nombreAsignatura + '}';
    }

}
