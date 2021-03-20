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
public class AsignaturaGrado {
    private int idNivelEducativo;
    private int idAsignatura;
    private String grado;
    private String seccion;
    private String Asignatura;

    public AsignaturaGrado(int idNivelEducativo, int idAsignatura, String grado, String seccion, String Asignatura) {
        this.idNivelEducativo = idNivelEducativo;
        this.idAsignatura = idAsignatura;
        this.grado = grado;
        this.seccion = seccion;
        this.Asignatura = Asignatura;
    }

    public AsignaturaGrado(int idAsignatura, String Asignatura) {
        this.idAsignatura = idAsignatura;
        this.Asignatura = Asignatura;
    }
    

    public AsignaturaGrado() {
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getIdNivelEducativo() {
        return idNivelEducativo;
    }

    public void setIdNivelEducativo(int idNivelEducativo) {
        this.idNivelEducativo = idNivelEducativo;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getAsignatura() {
        return Asignatura;
    }

    public void setAsignatura(String Asignatura) {
        this.Asignatura = Asignatura;
    }
}
