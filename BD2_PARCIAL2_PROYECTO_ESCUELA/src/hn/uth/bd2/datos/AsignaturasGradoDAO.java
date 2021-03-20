/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.AsignaturaGrado;
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
public class AsignaturasGradoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public AsignaturasGradoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertarAsignaturaGrado(int idGrado, int idAsignatura) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call PARAMETROS_APP.SP_INSERTAR_CLASES_GRADO(?,?,?)}");
            insertando.setInt(1, idGrado);
            insertando.setInt(2, idAsignatura);
            insertando.registerOutParameter(3, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getString(3) != null) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(3), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
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

    public List<AsignaturaGrado> listarAsignaturasGrado(String busqueda) {
        List<AsignaturaGrado> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call GRADO_ASIGNATURAS_DB.SP_GRADO_ASIGN_LISTA(?,?)}");
            insertando.setString(1, busqueda);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new AsignaturaGrado(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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

    public List<AsignaturaGrado> listarAsignaturasTabla(int idGrado) {
        List<AsignaturaGrado> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call GRADO_ASIGNATURAS_DB.SP_LISTAR_ASIGN_GRADO_TBL(?,?)}");
            insertando.setInt(1, idGrado);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new AsignaturaGrado(rs.getInt(1), rs.getString(2)));
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
    
    public boolean actualizar(AsignaturaGrado objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call GRADO_ASIGNATURAS_DB.SP_ACTUALIZAR_NVL_EDUC_ASIG(?,?,?,?)}"); //LISTO
            insertando.setInt(1, objeto.getIdNivelEducativo());
            insertando.setInt(2, objeto.getIdAsignatura());
            insertando.registerOutParameter(3, oracle.jdbc.internal.OracleTypes.INTEGER);
            insertando.registerOutParameter(4, oracle.jdbc.internal.OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(3) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(4), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Datos de matricula actualizados correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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
    
    public boolean eliminarDetalle(int idGrado) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call GRADO_ASIGNATURAS_DB.ELIMINAR_GRADO_ASIGN(?)}");
            insertando.setInt(1, idGrado);

            if (insertando.executeUpdate() > 0) {
                respuesta = true;
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
