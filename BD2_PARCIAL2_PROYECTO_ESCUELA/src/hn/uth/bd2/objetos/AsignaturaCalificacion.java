/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.objetos;

import java.util.Objects;

/**
 *
 * @author Buddys
 */
public class AsignaturaCalificacion {
    private int id;
    private String nombreAsignatura;

    public AsignaturaCalificacion(int id, String nombreAsignatura) {
        this.id = id;
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    @Override
    public String toString() {
        return nombreAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nombreAsignatura);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AsignaturaCalificacion other = (AsignaturaCalificacion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombreAsignatura, other.nombreAsignatura)) {
            return false;
        }
        return true;
    }

    
}
