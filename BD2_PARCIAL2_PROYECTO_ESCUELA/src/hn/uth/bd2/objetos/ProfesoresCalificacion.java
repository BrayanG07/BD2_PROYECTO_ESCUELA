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
public class ProfesoresCalificacion {
    private int id;
    private String nombreProfesores;

    public ProfesoresCalificacion(int id, String nombreProfesores) {
        this.id = id;
        this.nombreProfesores = nombreProfesores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProfesores() {
        return nombreProfesores;
    }

    public void setNombreProfesores(String nombreProfesores) {
        this.nombreProfesores = nombreProfesores;
    }

    @Override
    public String toString() {
        return nombreProfesores;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nombreProfesores);
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
        final ProfesoresCalificacion other = (ProfesoresCalificacion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombreProfesores, other.nombreProfesores)) {
            return false;
        }
        return true;
    }

    
}
