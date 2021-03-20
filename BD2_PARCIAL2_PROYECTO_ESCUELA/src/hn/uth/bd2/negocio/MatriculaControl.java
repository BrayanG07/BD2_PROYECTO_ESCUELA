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

        String[] titulos = {"Id Matricula", "ID Alumno", "Nombres", "Apellidos", "RTN", "ID Grado", "Grado", "Seccion", "ID Anio Escolar", "Año Escolar", "Telefono", "Direccion", "Fecha"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[13];

        for (MatriculaAlumno item : lista) {
            registro[0] = Integer.toString(item.getIdMatricula());
            registro[1] = Integer.toString(item.getIdAlumno());
            registro[2] = item.getNombres();
            registro[3] = item.getApellidos();
            registro[4] = item.getRtn();
            registro[5] = Integer.toString(item.getIdNivelEducativo());
            registro[6] = item.getGrado();
            registro[7] = item.getSeccion();
            registro[8] = Integer.toString(item.getIdAnioEscolar());
            registro[9] = item.getAnioEscolar();
            registro[10] = item.getTelefono();
            registro[11] = item.getDireccion();
            registro[12] = String.valueOf(item.getFecha());
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String actualizar(int idMatricula, int idAlumno, String nombre, String apellido, String direccion, String rtn, String telefono, int idAnioEscolar, int idCurso, Date fecha) {
        respuesta = "error";
        objeto.setIdMatricula(idMatricula);
        objeto.setIdAlumno(idAlumno);
        objeto.setNombres(nombre);
        objeto.setApellidos(apellido);
        objeto.setDireccion(direccion);
        objeto.setRtn(rtn);
        objeto.setTelefono(telefono);
        objeto.setIdAnioEscolar(idAnioEscolar);
        objeto.setIdNivelEducativo(idCurso);
        objeto.setFecha(fecha);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }
}
