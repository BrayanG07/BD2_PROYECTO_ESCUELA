/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.MatriculaDAO;
import hn.uth.bd2.objetos.AnioEscolar;
import hn.uth.bd2.objetos.Grado;
import hn.uth.bd2.objetos.MatriculaAlumno;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class MatriculaControl {
    private final MatriculaDAO DATOS;
    private MatriculaAlumno objeto;
    private AnioEscolar objAnio;
    private DefaultTableModel modeloTabla;
    private String respuesta;

    public MatriculaControl() {
        this.DATOS = new MatriculaDAO();
        this.objeto = new MatriculaAlumno();
        this.objAnio = new AnioEscolar();
    }
    
    public DefaultComboBoxModel llenandoComboAnio() {
        DefaultComboBoxModel items2 = new DefaultComboBoxModel();
        List<AnioEscolar> lista = new ArrayList();
        lista = DATOS.listarAnio();

        for (AnioEscolar objeto : lista) {
            items2.addElement(new AnioEscolar(objeto.getId(), objeto.getAnio()));
        }
        return items2;
    }
    
    public String insertar(String nombre, String apellido, String rtn, String direccion, String telefono, Date fecha, int idAnioEscolar, int idCurso) {
        respuesta = "error";
        objeto.setNombres(nombre);
        objeto.setApellidos(apellido);
        objeto.setRtn(rtn);
        objeto.setDireccion(direccion);
        objeto.setTelefono(telefono);
        objeto.setFecha(fecha);
        objeto.setIdAnioEscolar(idAnioEscolar);
        objeto.setIdNivelEducativo(idCurso);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }
    
    public DefaultTableModel listar(String busqueda) {
        List<MatriculaAlumno> lista = new ArrayList();
        lista.addAll(DATOS.listarMatricula(busqueda));

        String[] titulos = {"Id Matricula", "ID Alumno", "Alumno", "RTN", "ID Grado", "Grado", "ID Anio Escolar", "Año Escolar"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        for (MatriculaAlumno item : lista) {
            registro[0] = Integer.toString(item.getIdMatricula());
            registro[1] = Integer.toString(item.getIdAlumno());
            registro[2] = item.getNombres()+" "+item.getApellidos();
            registro[3] = item.getRtn();
            registro[4] = Integer.toString(item.getIdNivelEducativo());
            registro[5] = item.getGrado();
            registro[6] = Integer.toString(item.getIdAnioEscolar());
            registro[7] = item.getAnioEscolar();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
}
