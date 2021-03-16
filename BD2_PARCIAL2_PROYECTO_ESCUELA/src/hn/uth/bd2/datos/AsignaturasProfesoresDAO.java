/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.Asignaturas;
import hn.uth.bd2.objetos.AsignaturasProfesores;
import hn.uth.bd2.objetos.Grado;
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
            insertando = CON.conectar().prepareCall("{call PROFESORES_ASIG_CURSO_DB.SP_BUSQUEDA_ID_PROFESOR(?,?)}");
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
            insertando = CON.conectar().prepareCall("{call PROFESORES_ASIG_CURSO_DB.SP_ASIGNATURA(?)}");
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
    
    public List<AsignaturasProfesores> listarAsignaturasId(int idProfesor, int idCurso) {
        List<AsignaturasProfesores> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call PROFESORES_ASIG_CURSO_DB.SP_LISTAR_ASIGN_ID(?,?,?)}");
            insertando.setInt(1, idProfesor);
            insertando.setInt(2, idCurso);
            insertando.registerOutParameter(3, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(3);
            while (rs.next()) {
                registros.add(new AsignaturasProfesores(rs.getInt(1), rs.getString(2)));
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

    public boolean insertarAsignaturaProfe(int idProfesor, int idAsignatura, int idGrado) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call PARAMETROS_APP.INSERTAR_ASIGNACIONES(?,?,?,?,?)}");
            insertando.setInt(1, idProfesor);
            insertando.setInt(2, idAsignatura);
            insertando.setInt(3, idGrado);
            insertando.registerOutParameter(4, OracleTypes.INTEGER);
            insertando.registerOutParameter(5, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(4) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(5), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, insertando.getString(5), "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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
            insertando = CON.conectar().prepareCall("{call PROFESORES_ASIG_CURSO_DB.SP_ASIGNATURA_LISTA(?,?)}");
            insertando.setString(1, busqueda);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new AsignaturasProfesores(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
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

    public List<Grado> listarGrado() {
        List<Grado> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call PROFESORES_ASIG_CURSO_DB.SP_GRADOS_LISTA(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                registros.add(new Grado(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
    
    public boolean eliminarDetalle(int idProfesor, int idGrado) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call PROFESORES_ASIG_CURSO_DB.ELIMINAR_PROF_ASIG_CUR(?,?)}");
            insertando.setInt(1, idProfesor);
            insertando.setInt(2, idGrado);

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
