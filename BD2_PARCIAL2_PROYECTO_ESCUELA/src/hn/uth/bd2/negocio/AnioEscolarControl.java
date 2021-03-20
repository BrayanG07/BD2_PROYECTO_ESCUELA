/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.negocio;

import hn.uth.bd2.datos.AnioEscolarDAO;
import hn.uth.bd2.objetos.AnioEscolar;
import hn.uth.bd2.objetos.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class AnioEscolarControl {

    private final AnioEscolarDAO DATOS;
    private AnioEscolar objeto;
    private DefaultTableModel modeloTabla;
    private String respuesta;

    public AnioEscolarControl() {
        this.DATOS = new AnioEscolarDAO();
        this.objeto = new AnioEscolar();
    }

    public DefaultTableModel listar() {
        List<AnioEscolar> lista = new ArrayList();
        lista.addAll(DATOS.listar());

        String[] titulos = {"Id Anio Escolar", "Año", "Fecha Inicio", "Fecha Fin"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[4];

        for (AnioEscolar item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getFecha().toString();
            registro[2] = item.getFechaInicio().toString();
            registro[3] = item.getFechaFin().toString();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertar(Date fecha, Date fechaInicio, Date fechaFin) {
        respuesta = "error";
        objeto.setFecha(fecha);
        objeto.setFechaInicio(fechaInicio);
        objeto.setFechaFin(fechaFin);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public String actualizar(int id, Date fecha, Date fechaInicio, Date fechaFin) {
        respuesta = "error";
        objeto.setId(id);
        objeto.setFecha(fecha);
        objeto.setFechaInicio(fechaInicio);
        objeto.setFechaFin(fechaFin);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }
}
