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
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listarAlumnosCalificados(String grado, String seccion) {
        List<GradoCalificaiones> listaCalif = new ArrayList();
        listaCalif.addAll(DATOS.listarCalificados(grado, seccion));
        double notaTotal = 0.00;
        String resultado = "";    
        String[] titulos = {"Id", "Nombre Alumno", "I-Parcial", "II-Parcial", "III-Parcial","IV-Parcial","Profesor","Asignatura","Total","Resultado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[10];

        for (GradoCalificaiones calif: listaCalif) {
            registro[0] = Integer.toString(calif.getIdCalificacion());
            registro[1] = calif.getNombreAlumno();
            registro[2] = Double.toString(calif.getNota1());
            registro[3] = Double.toString(calif.getNota2());
            registro[4] = Double.toString(calif.getNota3());
            registro[5] = Double.toString(calif.getNota4());
            notaTotal =+ ((calif.getNota1() + calif.getNota2() + calif.getNota3() + calif.getNota4())/4);
            if (notaTotal >= 70) {
                resultado = "Aprobado";
            } else {
                resultado = "Reprobado";
            }
            registro[6] = calif.getNombreProfesor();
            registro[7] = calif.getNombreAsignatura();
            registro[8] = Double.toString(notaTotal);
            registro[9] = resultado;
            this.modeloTabla.addRow(registro);
        }
        System.out.println("Error en capa control");
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
        gc.setNota2(nota2);
        gc.setNota3(nota3);
        gc.setNota4(nota4);
        gc.setIdAlumno(idAlumno);
        gc.setIdAsignatura(idAsignatura);
        gc.setIdProfesor(idProfesor);
        if (DATOS.insertar(gc)) {
            return "OK";
        }
        return respuesta;
    }
}
