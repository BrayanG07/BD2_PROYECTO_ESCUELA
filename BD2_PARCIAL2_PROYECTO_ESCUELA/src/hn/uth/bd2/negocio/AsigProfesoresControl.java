/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.AsignaturasProfesoresDAO;
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
public class AsigProfesoresControl {

    private final AsignaturasProfesoresDAO DATOS;
    private Profesores objeto;
    private Asignaturas objAsignaturas;
    private DefaultTableModel modeloTabla;
    private String respuesta;

    public AsigProfesoresControl() {
        this.DATOS = new AsignaturasProfesoresDAO();
        this.objeto = new Profesores();
        this.objAsignaturas = new Asignaturas();
    }

    public String buscar(int id) {
        String nombre = "";
        List<Profesores> lista = new ArrayList();
        lista.addAll(DATOS.busquedaProfesor(id));

        for (Profesores item : lista) {
            nombre = item.getNombreProfesor();
        }
        return nombre;
    }

    public DefaultTableModel listarAsignaturas() {
        List<Asignaturas> lista = new ArrayList();
        lista.addAll(DATOS.listarAsignaturas());

        String[] titulos = {"Id", "Asignatura"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[2];

        for (Asignaturas item : lista) {
            registro[0] = Integer.toString(item.getIdAsignatura());
            registro[1] = item.getNombreAsignatura();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarAsignaciones(String busqueda) {
        List<AsignaturasProfesores> lista = new ArrayList();
        lista.addAll(DATOS.listarAsignaturasAsignadas(busqueda));
        
        String[] titulos = {"ID Profesor", "Profesor", "ID Asignatura", "Asignatura", "ID Grado", "Nombre", "Seccion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[7];
        for (AsignaturasProfesores item : lista) {
            registro[0] = Integer.toString(item.getIdProfesor());
            registro[1] = item.getNombreProfesor();
            registro[2] = Integer.toString(item.getIdAsignatura());
            registro[3] = item.getNombreAsignatura();
            registro[4] = Integer.toString(item.getIdGrado());
            registro[5] = item.getNombreGrado();
            registro[6] = item.getSeccion();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertarAsignaturasProf(int idProfesor, int idAsignatura, int idGrado) {
        if (DATOS.insertarAsignaturaProfe(idProfesor, idAsignatura, idGrado)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public DefaultComboBoxModel llenandoCombo() {
        DefaultComboBoxModel items2 = new DefaultComboBoxModel();
        List<Grado> lista = new ArrayList();
        lista = DATOS.listarGrado();

        for (Grado objeto : lista) {
            items2.addElement(new Grado(objeto.getId(), objeto.getNombre(), objeto.getSeccion()));
        }
        return items2;
    }
}
