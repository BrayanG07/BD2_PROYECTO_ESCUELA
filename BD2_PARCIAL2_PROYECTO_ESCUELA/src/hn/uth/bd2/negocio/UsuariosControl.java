/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.UsuariosDAO;
import hn.uth.bd2.objetos.Rol;
import hn.uth.bd2.objetos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class UsuariosControl {

    private final UsuariosDAO DATOS;
    private Usuario objeto;
    private DefaultTableModel modeloTabla;
    private String respuesta;

    public UsuariosControl() {
        this.DATOS = new UsuariosDAO();
        this.objeto = new Usuario();
    }

    public DefaultTableModel listar(String busqueda) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOS.listar(busqueda));

        String[] titulos = {"Id Usuario", "Usuario", "ID rol", "Rol", "Password"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[5];

        for (Usuario item : lista) {
            registro[0] = Integer.toString(item.getIdUsuario());
            registro[1] = item.getUsuario();
            registro[2] = Integer.toString(item.getIdRol());
            registro[3] = item.getNombreRol();
            registro[4] = item.getPassword();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public DefaultComboBoxModel llenandoRoles() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Rol> listaRoles = new ArrayList();
        listaRoles = DATOS.comboRoles();

        for (Rol objetoProfesores : listaRoles) {
            items.addElement(new Rol(objetoProfesores.getId(), objetoProfesores.getNombre()));
        }
        return items;
    }

    public String insertar(String usuario, String password, int idRol) {
        respuesta = "error";
        objeto.setUsuario(usuario);
        objeto.setPassword(password);
        objeto.setIdRol(idRol);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public String actualizar(int idUsuario, String usuario, String password, int idRol) {
        respuesta = "error";
        objeto.setIdUsuario(idUsuario);
        objeto.setUsuario(usuario);
        objeto.setPassword(password);
        objeto.setIdRol(idRol);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public String login(String usuario, String clave) {
        String respuesta = "0";
        Usuario usu = this.DATOS.login(usuario, clave);
        if (usu != null) {
            Variables.usuarioId = usu.getIdUsuario();
            Variables.rolId = usu.getIdRol();
            Variables.rolNombre = usu.getNombreRol();
            Variables.usuarioLogin = usu.getUsuario();
            respuesta = "1";
        }
        return respuesta;
    }

}
