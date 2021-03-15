/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.datos;

import hn.uth.bd2.database.Conexion;
import hn.uth.bd2.objetos.Rol;
import hn.uth.bd2.objetos.Usuario;
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
public class UsuariosDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public UsuariosDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Usuario objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call SP_INSERTAR_USUARIO(?,?,?,?,?)}");
            insertando.setString(1, objeto.getUsuario());
            insertando.setString(2, objeto.getPassword());
            insertando.setInt(3, objeto.getIdRol());
            insertando.registerOutParameter(4, OracleTypes.INTEGER);
            insertando.registerOutParameter(5, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(4) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(5), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
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

    public boolean actualizar(Usuario objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_USUARIO(?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdUsuario());
            insertando.setString(2, objeto.getUsuario());
            insertando.setString(3, objeto.getPassword());
            insertando.setInt(4, objeto.getIdRol());
            insertando.registerOutParameter(5, OracleTypes.INTEGER);
            insertando.registerOutParameter(6, OracleTypes.VARCHAR);

            insertando.execute();
            respuesta = true;

            if (insertando.getInt(5) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, insertando.getString(6), "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
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

    public List<Rol> comboRoles() {
        List<Rol> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call SP_LISTAR_ROLES(?)}");
            insertando.registerOutParameter(1, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(1);
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2)));
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

    public List<Usuario> listar(String busqueda) {
        List<Usuario> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call SP_USUARIO_LISTA(?,?)}");
            insertando.setString(1, busqueda);
            insertando.registerOutParameter(2, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(2);
            while (rs.next()) {
                registros.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
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

    public Usuario login(String usuario, String clave) {
        Usuario usu = null;
        try {
            insertando = CON.conectar().prepareCall("{call SP_LOGIN(?,?,?)}");
            insertando.setString(1, usuario);
            insertando.setString(2, clave);
            insertando.registerOutParameter(3, OracleTypes.CURSOR);
            insertando.executeUpdate();

            rs = (ResultSet) insertando.getObject(3);
            while (rs.next()) {
                usu = new Usuario(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error capa DAO"+e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return usu;
    }
}
