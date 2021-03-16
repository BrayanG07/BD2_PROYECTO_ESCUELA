/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.AnioEscolar;
import hn.uth.bd2.objetos.MatriculaAlumno;
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
public class MatriculaDAO {
    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public MatriculaDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<AnioEscolar> listarAnio() {
        List<AnioEscolar> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call MATRICULA_ALUMNO.SP_COMBO_LISTAR_ANIO(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                registros.add(new AnioEscolar(rs.getInt(1), rs.getString(2)));
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
    
    public boolean insertar(MatriculaAlumno objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call PARAMETROS_APP.SP_INSERTAR_MATRICULA_ALUMNO(?,?,?,?,?,?,?,?,?,?)}");
            insertando.setString(1, objeto.getNombres());
            insertando.setString(2, objeto.getApellidos());
            insertando.setString(3, objeto.getRtn());
            insertando.setString(4, objeto.getDireccion());
            insertando.setString(5, objeto.getTelefono());
            insertando.setDate(6, objeto.getFecha());
            insertando.setInt(7, objeto.getIdAnioEscolar());
            insertando.setInt(8, objeto.getIdNivelEducativo());
            insertando.registerOutParameter(9, OracleTypes.INTEGER);
            insertando.registerOutParameter(10, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(9) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(10), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Matricula registrada correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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
    
    public List<MatriculaAlumno> listarMatricula(String busqueda) {
        List<MatriculaAlumno> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call MATRICULA_ALUMNO.SP_LISTAR_MATRICULA(?,?)}");
            insertando.setString(1, busqueda);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new MatriculaAlumno(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11),rs.getString(12), rs.getDate(13)));
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
    
    public boolean actualizar(MatriculaAlumno objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MATRICULA_ALUMNO.SP_ACTUALIZAR_MATRICULA(?,?,?,?,?,?,?,?,?,?,?,?)}"); //LISTO
            insertando.setInt(1, objeto.getIdMatricula());
            insertando.setInt(2, objeto.getIdAlumno());
            insertando.setString(3, objeto.getNombres());
            insertando.setString(4, objeto.getApellidos());
            insertando.setString(5, objeto.getDireccion());
            insertando.setString(6, objeto.getRtn());
            insertando.setString(7, objeto.getTelefono());
            insertando.setInt(8, objeto.getIdAnioEscolar());
            insertando.setInt(9, objeto.getIdNivelEducativo());
            insertando.setDate(10, objeto.getFecha());
            insertando.registerOutParameter(11, oracle.jdbc.internal.OracleTypes.INTEGER);
            insertando.registerOutParameter(12, oracle.jdbc.internal.OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(11) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(12), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
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
}
