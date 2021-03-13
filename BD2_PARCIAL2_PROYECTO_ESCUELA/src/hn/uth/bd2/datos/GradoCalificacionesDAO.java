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
            java.util.Date d = new java.util.Date();
            java.sql.Date date2 = new java.sql.Date(d.getTime());
            insertando = CON.conectar().prepareCall("{call LISTA_ALUMNOSC(?,?,?,?)}");
            insertando.setString(1, nombreGrado);
            insertando.setString(2, seccion);
            insertando.setDate(3, date2);
            insertando.registerOutParameter(4, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(4);
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

    public List<GradoCalificaiones> listarCalificados(String nombreGrado, String seccion) {
        List<GradoCalificaiones> listaCalif = new ArrayList();
        try {
            java.util.Date d = new java.util.Date();
            java.sql.Date date2 = new java.sql.Date(d.getTime());
            insertando = CON.conectar().prepareCall("{call CURSOR_CALIFICACIONES(?,?,?,?)}");
            insertando.setString(1, nombreGrado);
            insertando.setString(2, seccion);
            insertando.setDate(3, date2);
            insertando.registerOutParameter(4, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(4);
            while (rs.next()) {
                listaCalif.add(new GradoCalificaiones(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), rs.getString(11)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error capa DAO: " + e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return listaCalif;
    }

    public List<ProfesoresCalificacion> comboProfesores(String grado, String seccion) {
        List<ProfesoresCalificacion> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBO_PROFESOR_LISTAR(?,?,?)}");
            insertando.setString(1, grado);
            insertando.setString(2, seccion);
            insertando.registerOutParameter(3, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(3);
            while (rs.next()) {
                registros.add(new ProfesoresCalificacion(rs.getInt(1), rs.getString(2)));
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
        return registros;
    }

    public List<AsignaturaCalificacion> comboAsignaturas() {
        List<AsignaturaCalificacion> listaAsignaturas = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBO_ASIGNATURA_LISTAR(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                listaAsignaturas.add(new AsignaturaCalificacion(rs.getInt(1), rs.getString(2)));
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
        return listaAsignaturas;
    }

    public boolean insertar(GradoCalificaiones objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_CALIFICACION(?,?,?,?,?,?,?,?,?)}");
            insertando.setDouble(1, objeto.getNota1());
            insertando.setDouble(2, objeto.getNota2());
            insertando.setDouble(3, objeto.getNota3());
            insertando.setDouble(4, objeto.getNota4());
            insertando.setInt(5, objeto.getIdAlumno());
            insertando.setInt(6, objeto.getIdAsignatura());
            insertando.setInt(7, objeto.getIdProfesor());
            insertando.registerOutParameter(8, OracleTypes.INTEGER);
            insertando.registerOutParameter(9, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(8) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(9), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Alumno calificado correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
            }

            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean actualizar(GradoCalificaiones objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_CALIFICACION(?,?,?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdCalificacion());
            insertando.setDouble(2, objeto.getNota1());
            insertando.setDouble(3, objeto.getNota2());
            insertando.setDouble(4, objeto.getNota3());
            insertando.setDouble(5, objeto.getNota4());
            insertando.setInt(6, objeto.getIdAlumno());
            insertando.setInt(7, objeto.getIdAsignatura());
            insertando.setInt(8, objeto.getIdProfesor());
            insertando.registerOutParameter(9, OracleTypes.INTEGER);
            insertando.registerOutParameter(10, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(9) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(10), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Calificacion actualizada correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
            }

            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

}
