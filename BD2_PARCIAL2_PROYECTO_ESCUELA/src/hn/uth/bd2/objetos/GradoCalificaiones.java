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
public class GradoCalificaiones {
    private int idCalificacion;
    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;
    private int idAlumno;
    private String nombreAlumno;
    private int idProfesor;
    private String nombreProfesor;
    private String rtnAlumno;
    private int idAsignatura;
    private String nombreAsignatura;
    private double notaTotal = 0.00;

    public GradoCalificaiones() {
    }

    public GradoCalificaiones(int idCalificacion, String nombreAlumno, double nota1, double nota2, double nota3, double nota4, String nombreProfesor, String nombreAsignatura) {
        this.idCalificacion = idCalificacion;
        this.nombreAlumno = nombreAlumno;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.nombreProfesor = nombreProfesor;
        this.nombreAsignatura = nombreAsignatura;
    }

    public GradoCalificaiones(int idAlumno, String nombreAlumno, String rtnAlumno) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.rtnAlumno = rtnAlumno;
    }

    public double getNotaTotal() {
        return notaTotal;
    }

    public void setNotaTotal(double notaTotal) {
        this.notaTotal = notaTotal;
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

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getNota4() {
        return nota4;
    }

    public void setNota4(double nota4) {
        this.nota4 = nota4;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getRtnAlumno() {
        return rtnAlumno;
    }

    public void setRtnAlumno(String rtnAlumno) {
        this.rtnAlumno = rtnAlumno;
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
        return "GradoCalificaiones{" + "idCalificacion=" + idCalificacion + ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3 + ", nota4=" + nota4 + ", idAlumno=" + idAlumno + ", nombreAlumno=" + nombreAlumno + ", idProfesor=" + idProfesor + ", nombreProfesor=" + nombreProfesor + ", rtnAlumno=" + rtnAlumno + ", idAsignatura=" + idAsignatura + ", nombreAsignatura=" + nombreAsignatura + '}';
    }

}
