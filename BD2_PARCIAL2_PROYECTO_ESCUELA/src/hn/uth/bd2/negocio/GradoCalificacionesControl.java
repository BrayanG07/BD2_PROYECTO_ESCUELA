/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.GradoCalificacionesDAO;
import hn.uth.bd2.objetos.GradoCalificaiones;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class GradoCalificacionesControl {
    private final GradoCalificacionesDAO DATOS;
    private GradoCalificaiones objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public GradoCalificacionesControl() {
        this.DATOS = new GradoCalificacionesDAO();
    }
    
    public DefaultTableModel listarAlumnosGrado(String grado, String seccion) {
        List<GradoCalificaiones> lista = new ArrayList();
        lista.addAll(DATOS.listar(grado, seccion));

        String[] titulos = {"Id", "Nombre Alumno", "RTN"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[3];

        for (GradoCalificaiones item : lista) {
            registro[0] = Integer.toString(item.getIdAlumno());
            registro[1] = item.getNombreAlumno();
            registro[2] = item.getRtnAlumno();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
}
