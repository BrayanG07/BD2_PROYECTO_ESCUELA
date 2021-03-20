/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.objetos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author wandy zapata
 */
public class Conexion3 {
   private static final String usuario = "PROYECTO_ESCUELA";
    private static final String clave = "brayan";
    public static Connection ObtenerConexion(){
        try {
            String URL = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection cn = DriverManager.getConnection(URL, usuario, clave);
            return cn;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
