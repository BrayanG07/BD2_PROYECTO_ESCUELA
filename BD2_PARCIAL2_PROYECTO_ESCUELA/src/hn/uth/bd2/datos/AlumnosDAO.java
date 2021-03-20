/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.Alumnos;
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
public class AlumnosDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public AlumnosDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean actualizar(Alumnos objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ALUMNOS_DB.SP_ACTUALIZAR_ALUMNOS(?,?,?,?,?,?,?)}"); //LISTO
            insertando.setInt(1, objeto.getIdAlumno());
            insertando.setString(2, objeto.getNombres());
            insertando.setString(3, objeto.getApellidos());
            insertando.setString(4, objeto.getRtn());
            insertando.setString(5, objeto.getDireccion());
            insertando.setString(6, objeto.getTelefono());
            insertando.registerOutParameter(7, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getString(7) != null) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(7), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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

    public List<Alumnos> listar(String busqueda) {
        List<Alumnos> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call ALUMNOS_DB.SP_LISTAR_ALUMNOS(?,?)}");
            insertando.setString(1, busqueda);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new Alumnos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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
