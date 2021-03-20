/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.AsignaturasGradoDAO;
import hn.uth.bd2.objetos.AsignaturaGrado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class AsignaturasGradoControl {

    private final AsignaturasGradoDAO DATOS;
    private AsignaturaGrado objeto;
    private DefaultTableModel modeloTabla;
    private String respuesta;

    public AsignaturasGradoControl() {
        this.DATOS = new AsignaturasGradoDAO();
        this.objeto = new AsignaturaGrado();
    }

    public DefaultTableModel listarTodo(String busqueda) {
        List<AsignaturaGrado> lista = new ArrayList();
        lista.addAll(DATOS.listarAsignaturasGrado(busqueda));

        String[] titulos = {"ID Grado", "Grado", "Seccion", "ID Asignatura", "Asignatura"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[5];
        for (AsignaturaGrado item : lista) {
            registro[0] = Integer.toString(item.getIdNivelEducativo());
            registro[1] = item.getGrado();
            registro[2] = item.getSeccion();
            registro[3] = Integer.toString(item.getIdAsignatura());
            registro[4] = item.getAsignatura();

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarAsignacionesTabla(int idGrado) {
        List<AsignaturaGrado> lista = new ArrayList();
        lista.addAll(DATOS.listarAsignaturasTabla(idGrado));

        String[] titulos = {"ID", "Asignatura"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[2];
        for (AsignaturaGrado item : lista) {
            registro[0] = Integer.toString(item.getIdAsignatura());
            registro[1] = item.getAsignatura();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertarAsignaturasGrado(int idGrado, int idAsignatura) {
        if (DATOS.insertarAsignaturaGrado(idGrado, idAsignatura)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String eliminarDetalle(int idNivelEducativo) {
        if (DATOS.eliminarDetalle(idNivelEducativo)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String actualizar(int idGrado, int idAsignatura) {
        respuesta = "error";
        objeto.setIdNivelEducativo(idGrado);
        objeto.setIdAsignatura(idAsignatura);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

}
