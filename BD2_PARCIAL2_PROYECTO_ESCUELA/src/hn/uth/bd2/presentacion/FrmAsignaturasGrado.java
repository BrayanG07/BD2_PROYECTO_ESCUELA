/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.bd2.presentacion;

import hn.uth.bd2.datos.AsignaturasProfesoresDAO;
import hn.uth.bd2.negocio.AsigProfesoresControl;
import hn.uth.bd2.negocio.AsignaturasGradoControl;
import hn.uth.bd2.objetos.Asignaturas;
import hn.uth.bd2.objetos.AsignaturasProfesores;
import hn.uth.bd2.objetos.Grado;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Buddys
 */
public class FrmAsignaturasGrado extends javax.swing.JInternalFrame {

    private final AsigProfesoresControl CONTROL_PROFE;
    private final AsignaturasGradoControl CONTROL;
    private Asignaturas auxAsignatura;
    private String accion;
    private int seleccionQuitarTabla;
    private int seleccionAsignatura;
    private int idGradoTabla;

    /**
     * Creates new form FrmAsignaturasProfesores
     */
    public FrmAsignaturasGrado() {
        initComponents();
        this.CONTROL_PROFE = new AsigProfesoresControl();
        this.CONTROL = new AsignaturasGradoControl();
        this.accion = "guardar";
        this.listarTodo("");
        this.cargarGrado();
        tablaAsignaturas.setModel(this.CONTROL_PROFE.listarAsignaturas()); //BIEN
        tabGeneral.setEnabledAt(1, false);
        DefaultTableModel model;
        String columnas[] = {"ID", "NOMBRE"};
        model = new DefaultTableModel(null, columnas);
        tablaAsignando.setModel(model);
        auxAsignatura = null;
        seleccionAsignatura = -1;
        idGradoTabla = 0;
    }

    private void listarTodo(String busqueda) {
        tablaPrincipal.setModel(this.CONTROL.listarTodo(busqueda));
        TableRowSorter control = new TableRowSorter(tablaPrincipal.getModel());
        tablaPrincipal.setRowSorter(control);
        this.ocultarColumnas();
    }

    private void ocultarColumnas() {
        tablaPrincipal.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPrincipal.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPrincipal.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPrincipal.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tablaPrincipal.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaPrincipal.getColumnModel().getColumn(3).setMinWidth(0);
        tablaPrincipal.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tablaPrincipal.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
    }

    private void listarAsignaturasId(int idProfesor, int idCurso) {
        tablaAsignando.setModel(this.CONTROL_PROFE.listarAsignacionesId(idProfesor, idCurso));
    }

    private void cargarGrado() { //BIEN
        DefaultComboBoxModel items = this.CONTROL_PROFE.llenandoCombo();
        cboGrado.setModel(items);
    }

    public static void pasarAsignatura(String id, String nombre, JTable tabla) {
        DefaultTableModel model;
        model = (DefaultTableModel) tabla.getModel();
        if (id != null && nombre != null) {
            String[] filas = new String[2];
            filas[0] = id;
            filas[1] = nombre;
            model.addRow(filas);
        }

        tabla.setModel(model);
    }

    private void limpiar() {
        DefaultTableModel model;
        String columnas[] = {"ID", "NOMBRE"};
        model = new DefaultTableModel(null, columnas);
        tablaAsignando.setModel(model);
        this.accion = "guardar";
    }

    private void editar(int idProfesor) {
        String capturador = this.CONTROL_PROFE.eliminarDetalle(idProfesor, idGradoTabla);
        String detalle = "";
        if (capturador.equals("OK")) {
            Grado item2 = (Grado) cboGrado.getSelectedItem();
            for (int i = 0; i < tablaAsignando.getRowCount(); i++) {
                detalle = this.CONTROL_PROFE.insertarAsignaturasProf(idProfesor, Integer.parseInt(tablaAsignando.getValueAt(i, 0) + ""), item2.getId());
            }
            if (detalle.equals("OK")) {
                this.mensajeOk("Registros actualizados correctamente");
                this.listarTodo("");
                this.limpiar();
                tablaAsignaturas.setModel(this.CONTROL_PROFE.listarAsignaturas());
                tabGeneral.setEnabledAt(1, false);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setSelectedIndex(0);
            }
        }
    }

    private void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema Escolar", JOptionPane.ERROR_MESSAGE);
    }

    private void mensajeOk(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        tablaListado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPrincipal = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cboGrado = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAsignaturas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAsignando = new javax.swing.JTable();
        btnFlecha = new javax.swing.JButton();
        btnRemueve = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Asignando Clases al grado");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jLabel2.setText("Grador:");

        btnNuevo.setText("Nuevo");
        btnNuevo.setPreferredSize(new java.awt.Dimension(80, 28));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(80, 28));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(80, 28));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tablaListado.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado"));

        tablaPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPrincipal);

        javax.swing.GroupLayout tablaListadoLayout = new javax.swing.GroupLayout(tablaListado);
        tablaListado.setLayout(tablaListadoLayout);
        tablaListadoLayout.setHorizontalGroup(
            tablaListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablaListadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        tablaListadoLayout.setVerticalGroup(
            tablaListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablaListadoLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tablaListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tablaListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabGeneral.addTab("Listado", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        jLabel4.setText("Grado:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(32, 32, 32)
                .addComponent(cboGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignaciones"));

        tablaAsignaturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaAsignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsignaturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAsignaturas);

        tablaAsignando.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaAsignando.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsignandoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAsignando);

        btnFlecha.setText("<---");
        btnFlecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlechaActionPerformed(evt);
            }
        });

        btnRemueve.setText("X");
        btnRemueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemueveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRemueve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFlecha))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnFlecha)
                        .addGap(67, 67, 67)
                        .addComponent(btnRemueve)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(90, 29));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setPreferredSize(new java.awt.Dimension(90, 29));

        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(90, 29));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 623, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Mantenimiento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setSelectedIndex(1);
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnFlechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlechaActionPerformed
        // TODO add your handling code here:
        if (auxAsignatura != null) {
            boolean existe = false;
            for (int i = 0; i < tablaAsignando.getRowCount(); i++) {
                if (tablaAsignando.getValueAt(i, 0).equals(auxAsignatura.getIdAsignatura() + "")) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                pasarAsignatura(auxAsignatura.getIdAsignatura() + "", auxAsignatura.getNombreAsignatura(), tablaAsignando);
                auxAsignatura = null;
                tablaAsignaturas.clearSelection();
            } else {
                JOptionPane.showMessageDialog(rootPane, "La asignatura ya fue agregada", null, JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione una asignatura", null, JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFlechaActionPerformed

    private void tablaAsignandoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignandoMouseClicked
        // TODO add your handling code here:
        seleccionQuitarTabla = tablaAsignando.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tablaAsignandoMouseClicked

    private void tablaAsignaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignaturasMouseClicked
        // TODO add your handling code here:
        int seleccion = tablaAsignaturas.rowAtPoint(evt.getPoint());

        if (seleccion >= 0) {
            int id = Integer.parseInt(tablaAsignaturas.getValueAt(seleccion, 0) + "");
            String nom = tablaAsignaturas.getValueAt(seleccion, 1) + "";

            auxAsignatura = new Asignaturas(id, nom);
        }
    }//GEN-LAST:event_tablaAsignaturasMouseClicked

    private void btnRemueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemueveActionPerformed
        // TODO add your handling code here:
        if (seleccionQuitarTabla >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) tablaAsignando.getModel();
            modelo.removeRow(seleccionQuitarTabla);

            seleccionQuitarTabla = -1;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione una asignatura para quitarla", null, JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemueveActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:

        String respuesta = "";
        if (this.accion.equals("editar")) {
            //this.editar(idProfesor);
        } else {
            Grado item = (Grado) cboGrado.getSelectedItem();
            for (int i = 0; i < tablaAsignando.getRowCount(); i++) {
                respuesta = this.CONTROL.insertarAsignaturasGrado(item.getId(), Integer.parseInt(tablaAsignando.getValueAt(i, 0) + ""));
            }
            if (respuesta.equals("OK")) {
                this.listarTodo("");
                this.limpiar();
                this.mensajeOk("Las asignaciones se registraron correctamente");
                tablaAsignaturas.setModel(this.CONTROL_PROFE.listarAsignaturas());
                tabGeneral.setEnabledAt(1, false);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (tablaPrincipal.getSelectedRowCount() == 1) {
            int idProfesor = Integer.parseInt(String.valueOf(tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 0)));
            String nombreProfesor = String.valueOf(tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 1));
            idGradoTabla = Integer.parseInt(String.valueOf(tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 4)));
            String nombreGrado = String.valueOf(tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 5));
            String seccion = String.valueOf(tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 6));

//            txtIdProfesor.setText(Integer.toString(idProfesor));
            Grado g = new Grado(idGradoTabla, nombreGrado, seccion);
            cboGrado.setSelectedItem(g);
            this.listarAsignaturasId(idProfesor, idGradoTabla);
        } else {
            this.mensajeError("Debes seleccionar el registro del profesor que deseas modificar");
        }

        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setSelectedIndex(1);
        this.accion = "editar";
        btnGuardar.setText("Editar");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.listarTodo(txtBuscar.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setSelectedIndex(0);
        this.limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFlecha;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRemueve;
    private javax.swing.JComboBox<String> cboGrado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaAsignando;
    private javax.swing.JTable tablaAsignaturas;
    private javax.swing.JPanel tablaListado;
    private javax.swing.JTable tablaPrincipal;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
