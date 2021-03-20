/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.objetos;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Buddys
 */
public class AnioEscolar {
    private int id;
    private String anio;
    private Date fecha; 
    private Date fechaInicio; 
    private Date fechaFin; 

    public AnioEscolar() {
    }

    public AnioEscolar(int id, Date fecha, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.fecha = fecha;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public AnioEscolar(int id, String anio) {
        this.id = id;
        this.anio = anio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.anio);
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
        final AnioEscolar other = (AnioEscolar) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return anio;
    }
}
