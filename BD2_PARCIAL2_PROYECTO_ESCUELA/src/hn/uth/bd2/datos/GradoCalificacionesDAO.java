/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.GradoCalificaiones;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Buddys
 */
public class GradoCalificacionesDAO {
    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public GradoCalificacionesDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<GradoCalificaiones> listar(String nombreGrado, String seccion) {
        List<GradoCalificaiones> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTA_ALUMNOSC(?,?,?)}");
            insertando.setString(1, nombreGrado);
            insertando.setString(2, seccion);
            insertando.registerOutParameter(3, OracleTypes.CURSOR);
            insertando.executeUpdate();
            
            rs = (ResultSet) insertando.getObject(3);
            while (rs.next()) {
                lista.add(new GradoCalificaiones(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }
}
