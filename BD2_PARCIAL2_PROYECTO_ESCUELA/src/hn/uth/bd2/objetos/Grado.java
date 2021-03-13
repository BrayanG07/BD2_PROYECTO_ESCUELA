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
public class Grado {
   private int id;
   private String nombre;
   private String seccion;

    public Grado(int id, String nombre, String seccion) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public Grado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return nombre +" "+seccion ;
    }
}
