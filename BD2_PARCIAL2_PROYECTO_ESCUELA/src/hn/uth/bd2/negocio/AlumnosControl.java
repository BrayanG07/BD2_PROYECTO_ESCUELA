/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.AlumnosDAO;
import hn.uth.bd2.objetos.Alumnos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class AlumnosControl {

    private final AlumnosDAO DATOS;
    private Alumnos objeto;
    private DefaultTableModel modeloTabla;
    private String respuesta;

    public AlumnosControl() {
        this.DATOS = new AlumnosDAO();
        this.objeto = new Alumnos();
    }

    public DefaultTableModel listar(String busqueda) {
        List<Alumnos> lista = new ArrayList();
        lista.addAll(DATOS.listar(busqueda));

        String[] titulos = {"Id Alumno", "Nombres", "Apellidos", "RTN", "Direccion", "Telefono"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        for (Alumnos item : lista) {
            registro[0] = Integer.toString(item.getIdAlumno());
            registro[1] = item.getNombres();
            registro[2] = item.getApellidos();
            registro[3] = item.getRtn();
            registro[4] = item.getDireccion();
            registro[5] = item.getTelefono();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String actualizar(int idAlumno, String nombre, String apellido, String rtn, String direccion, String telefono) {
        respuesta = "error";
        objeto.setIdAlumno(idAlumno);
        objeto.setNombres(nombre);
        objeto.setApellidos(apellido);
        objeto.setRtn(rtn);
        objeto.setDireccion(direccion);
        objeto.setTelefono(telefono);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

}
