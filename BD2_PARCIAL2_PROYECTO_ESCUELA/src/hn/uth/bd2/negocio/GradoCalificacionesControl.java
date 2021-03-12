/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.GradoCalificacionesDAO;
import hn.uth.bd2.objetos.AsignaturaCalificacion;
import hn.uth.bd2.objetos.GradoCalificaiones;
import hn.uth.bd2.objetos.ProfesoresCalificacion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
    
    public DefaultComboBoxModel llenandoProfesores(String grado, String seccion) {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<ProfesoresCalificacion> listaProfesores = new ArrayList();
        listaProfesores = DATOS.comboProfesores(grado, seccion);

        for (ProfesoresCalificacion objetoProfesores : listaProfesores) {
            items.addElement(new ProfesoresCalificacion(objetoProfesores.getId(), objetoProfesores.getNombreProfesores()));
        }
        return items;
    }
    
    public DefaultComboBoxModel llenandoAsignaturas() {
        DefaultComboBoxModel items2 = new DefaultComboBoxModel();
        List<AsignaturaCalificacion> lista = new ArrayList();
        lista = DATOS.comboAsignaturas();

        for (AsignaturaCalificacion objeto : lista) {
            items2.addElement(new AsignaturaCalificacion(objeto.getId(), objeto.getNombreAsignatura()));
        }
        return items2;
    }
    
    public String insertar(double nota1, double nota2, double nota3, double nota4, int idAlumno, int idAsignatura, int idProfesor) {
        respuesta = "error";
        GradoCalificaiones gc = new GradoCalificaiones();
        gc.setNota1(nota1);
        System.out.println("Despues set nota1");
        gc.setNota2(nota2);
        gc.setNota3(nota3);
        gc.setNota4(nota4);
        gc.setIdAlumno(idAlumno);
        System.out.println("Despues de los id");
        gc.setIdAsignatura(idAsignatura);
        gc.setIdProfesor(idProfesor);
        System.out.println("Antes de la validacion");
        if (DATOS.insertar(gc)) {
            System.out.println("Dentro de OK");
            return "OK";
        }
        System.out.println("LLego al final");
        return respuesta;
    }
}
