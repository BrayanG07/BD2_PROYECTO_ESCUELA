/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.Asignaturas;
import hn.uth.bd2.objetos.AsignaturasProfesores;
import hn.uth.bd2.objetos.Profesores;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Buddys
 */
public class AsignaturasProfesoresDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public AsignaturasProfesoresDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Profesores> busquedaProfesor(int idProfesor) {
        List<Profesores> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call SP_BUSQUEDA_ID_PROFESOR(?,?)}");
            insertando.setInt(1, idProfesor);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new Profesores(rs.getInt(1), rs.getString(2)));
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

    public List<Asignaturas> listarAsignaturas() {
        List<Asignaturas> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call SP_ASIGNATURA(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                registros.add(new Asignaturas(rs.getInt(1), rs.getString(2)));
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

    public boolean insertarAsignaturaProfe(int idProfesor, int idAsignatura) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call SP_INSERTAR_ASIG_PROFESORES(?,?,?,?)}");
            insertando.setInt(1, idProfesor);
            insertando.setInt(2, idAsignatura);
            insertando.registerOutParameter(3, OracleTypes.INTEGER);
            insertando.registerOutParameter(4, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(3) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(4), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Asignaciones registradas correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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
    
    public List<AsignaturasProfesores> listarAsignaturasAsignadas(String busqueda) {
        List<AsignaturasProfesores> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call SP_ASIGNATURA_LISTA(?,?)}");
            insertando.setString(1, "%" + busqueda + "%");
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new AsignaturasProfesores(rs.getInt(1), rs.getString(2), rs.getInt(1), rs.getString(2)));
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
}
