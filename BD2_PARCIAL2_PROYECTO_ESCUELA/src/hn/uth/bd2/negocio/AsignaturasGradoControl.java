/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.AsignaturasGradoDAO;
import hn.uth.bd2.objetos.AsignaturaGrado;
import hn.uth.bd2.objetos.Asignaturas;
import hn.uth.bd2.objetos.AsignaturasProfesores;
import hn.uth.bd2.objetos.Grado;
import hn.uth.bd2.objetos.Profesores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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

    public String insertarAsignaturasGrado(int idGrado, int idAsignatura ) {
        if (DATOS.insertarAsignaturaGrado(idGrado, idAsignatura)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String eliminarDetalle(int idProfesor, int idGrado) {
        if (DATOS.eliminarDetalle(idProfesor, idGrado)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

}
