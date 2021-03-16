/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.objetos;

import java.sql.Date;

/**
 *
 * @author Buddys
 */
public class MatriculaAlumno {
    private int idMatricula;
    private Date fecha;
    private int idAnioEscolar;
    private String anioEscolar;
    private int idNivelEducativo;
    private String grado;
    private int idAlumno;
    private String nombres;
    private String apellidos;
    private String rtn;
    private String direccion;
    private String telefono;

    public MatriculaAlumno() {
    }

    public MatriculaAlumno(int idMatricula, int idAlumno, String nombres, String apellidos, String rtn,int idNivelEducativo, String grado, int idAnioEscolar, String anioEscolar) {
        this.idMatricula = idMatricula;
        this.idAnioEscolar = idAnioEscolar;
        this.anioEscolar = anioEscolar;
        this.idNivelEducativo = idNivelEducativo;
        this.grado = grado;
        this.idAlumno = idAlumno;
        this.nombres = nombres;
        this.rtn = rtn;
        this.apellidos = apellidos;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdAnioEscolar() {
        return idAnioEscolar;
    }

    public void setIdAnioEscolar(int idAnioEscolar) {
        this.idAnioEscolar = idAnioEscolar;
    }

    public String getAnioEscolar() {
        return anioEscolar;
    }

    public void setAnioEscolar(String anioEscolar) {
        this.anioEscolar = anioEscolar;
    }

    public int getIdNivelEducativo() {
        return idNivelEducativo;
    }

    public void setIdNivelEducativo(int idNivelEducativo) {
        this.idNivelEducativo = idNivelEducativo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
