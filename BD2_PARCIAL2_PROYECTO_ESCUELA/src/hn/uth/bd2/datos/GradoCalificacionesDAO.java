/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.AsignaturaCalificacion;
import hn.uth.bd2.objetos.GradoCalificaiones;
import hn.uth.bd2.objetos.ProfesoresCalificacion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public List<ProfesoresCalificacion> comboProfesores(String grado, String seccion){
        List<ProfesoresCalificacion> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBO_PROFESOR_LISTAR(?,?,?)}");
            insertando.setString(1, grado);
            insertando.setString(2, seccion);
            insertando.registerOutParameter(3, OracleTypes.CURSOR);
            insertando.executeUpdate();
            
            rs = (ResultSet) insertando.getObject(3);
            while (rs.next()) {
                registros.add(new ProfesoresCalificacion(rs.getInt(1),rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            insertando =null;
            rs = null;
            CON.cerrarConexion();
        }
        return registros;
    }
    
    public List<AsignaturaCalificacion> comboAsignaturas(){
        List<AsignaturaCalificacion> listaAsignaturas = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBO_ASIGNATURA_LISTAR(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();
            
            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                listaAsignaturas.add(new AsignaturaCalificacion(rs.getInt(1),rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            insertando =null;
            rs = null;
            CON.cerrarConexion();
        }
        return listaAsignaturas;
    }
    
    
}
