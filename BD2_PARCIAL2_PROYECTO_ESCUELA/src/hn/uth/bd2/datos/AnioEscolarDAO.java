/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.AnioEscolar;
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
public class AnioEscolarDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public AnioEscolarDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(AnioEscolar objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call PARAMETROS_APP.SP_INSERTAR_ANIO_ESCOLAR(?,?,?,?,?)}"); //LISTO
            insertando.setDate(1, objeto.getFecha());
            insertando.setDate(2, objeto.getFechaInicio());
            insertando.setDate(3, objeto.getFechaFin());
            insertando.registerOutParameter(4, OracleTypes.INTEGER);
            insertando.registerOutParameter(5, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(4) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(5), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
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

    public boolean actualizar(AnioEscolar objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ANIO_ESCOLARR.SP_ACTUALIZAR_ANIO_ESCOLAR(?,?,?,?,?,?)}"); //LISTO
            insertando.setInt(1, objeto.getId());
            insertando.setDate(2, objeto.getFecha());
            insertando.setDate(3, objeto.getFechaInicio());
            insertando.setDate(4, objeto.getFechaFin());
            insertando.registerOutParameter(5, OracleTypes.INTEGER);
            insertando.registerOutParameter(6, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(5) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(6), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Anio Escolar actualizado correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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

    public List<AnioEscolar> listar() {
        List<AnioEscolar> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call ANIO_ESCOLARR.SP_LISTAR_ANIO_ESCOLAR(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                registros.add(new AnioEscolar(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4)));
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
