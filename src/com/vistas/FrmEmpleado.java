/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import com.clases.*;
import com.dao.*;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.FuncionesValidacion;

/**
 *
 * @author Daniel
 */
public class FrmEmpleado extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmEmpleado
     */
    FuncionesValidacion validar = new FuncionesValidacion();
    
    public FrmEmpleado() {
        initComponents();
        tablaEmpleado();
        
        DaoUsuario daoU = new DaoUsuario();
        List<Usuario> listUsuarios;
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        listUsuarios = new ArrayList<>();
        try {
            for (Object object : daoU.mostrarUsuario()) {
                Usuario user = (Usuario) object;
                listUsuarios.add(user);
                model.addElement(user.getId_user()+"- "+user.getUser());
                
            }
            this.idUsuario.setModel(model);
        } catch (Exception e) {
           //JOptionPane.showInputDialog(ERROR);
        }
        
    }

    Empleado emp = new Empleado();
    Usuario u = new Usuario();
    DaoEmpleado daoEmp = new DaoEmpleado();
    int id_empleado=0;
    
    public void tablaEmpleado(){       
        String[] columnas={"Id Empleado","Codigo Empleado","Nombre", "Apellido", "Direccion","Telefono", "Id usuario"};
        Object[]obj=new Object[7];
        //asignar encabezados
        DefaultTableModel objTabla = new DefaultTableModel(null, columnas);
        List lista;
        
        try {
            lista = daoEmp.mostrarEmpleados();
            for (int i = 0; i < lista.size(); i++) {
                emp = (Empleado) lista.get(i);
                obj[0] = emp.getIdEmpleado();
                obj[1] = emp.getCodigoEmpleado();
                obj[2] = emp.getNombreEmpleado();
                obj[3] = emp.getApellidoEmpleado();
                obj[4] = emp.getDireccionEmpleado();
                obj[5] = emp.getTelefonoEmpleado();
                obj[6] = emp.getUsuario().getId_user()+"- "+emp.getUsuario().getUser();     
                objTabla.addRow(obj);
            }
            this.jTable1.setModel(objTabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error tablaEmpleado() " + e.toString());
        }        
    }
    
    public void llenarTablaEmpleado(){
        int filas = this.jTable1.getSelectedRow();
        
        this.id_empleado = Integer.parseInt(String.valueOf(this.jTable1.getValueAt(filas, 0)));
        this.txtCodigoEmpleado.setText(String.valueOf(this.jTable1.getValueAt(filas, 1)));
        this.txtNombre.setText(String.valueOf(this.jTable1.getValueAt(filas, 2)));
        this.txtApellido.setText(String.valueOf(this.jTable1.getValueAt(filas, 3)));
        this.txtDirec.setText(String.valueOf(this.jTable1.getValueAt(filas, 4)));
        this.txtTel.setText(String.valueOf(this.jTable1.getValueAt(filas, 5)));
        this.idUsuario.setSelectedItem(this.jTable1.getValueAt(filas, 6));
        
    }
    
    public void limpiarDatoEmp(){
        id_empleado=0;
        txtCodigoEmpleado.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDirec.setText("");
        txtTel.setText(""); 
        idUsuario.setSelectedIndex(0);
    }
    
    
    
    public void insertar(){
        Usuario p = new Usuario();
        
        emp.setCodigoEmpleado(Integer.parseInt(this.txtCodigoEmpleado.getText()));
        emp.setNombreEmpleado(this.txtNombre.getText());
        emp.setApellidoEmpleado(this.txtApellido.getText());
        emp.setDireccionEmpleado(this.txtDirec.getText());
        emp.setTelefonoEmpleado(this.txtTel.getText());
        
        String usua=(String)this.idUsuario.getSelectedItem();
        String[] usuar = usua.split("-");
        p.setId_user(Integer.parseInt(usuar[0]));
        
        emp.setUsuario(p);
        try {
            daoEmp.insertarEmpleado(emp);
            tablaEmpleado();
            limpiarDatoEmp();
        } catch (Exception ex) {
            Logger.getLogger(FrmEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modificar() throws Exception{      
                
        emp.setIdEmpleado(id_empleado);
        emp.setCodigoEmpleado(Integer.parseInt(this.txtCodigoEmpleado.getText()));
        emp.setNombreEmpleado(this.txtNombre.getText());
        emp.setApellidoEmpleado(this.txtApellido.getText());
        emp.setDireccionEmpleado(this.txtDirec.getText());
        emp.setTelefonoEmpleado(this.txtTel.getText());
        String id_user = (String) this.idUsuario.getSelectedItem();
        String[] id_users = id_user.split("-");
        u.setId_user(Integer.parseInt(id_users[0]));
        emp.setUsuario(u);
        
        int confirmacionModificar = JOptionPane.showConfirmDialog(this, "Desea modificar la Empleado","Modificar Empleado", JOptionPane.YES_NO_OPTION);
        if (confirmacionModificar == 0) {
            daoEmp.modificarEmpleado(emp);
            tablaEmpleado();
            limpiarDatoEmp();
            JOptionPane.showMessageDialog(rootPane, "Modificado con éxito","  Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    public void eliminar() throws Exception{
        
        emp.setIdEmpleado(id_empleado);
        
        int confirmacionModificar = JOptionPane.showConfirmDialog(this, "Desea eliminar la Empleado","Modificar Empleado", JOptionPane.YES_NO_OPTION);
        if (confirmacionModificar == 0) {
            daoEmp.eliminarEmpleado(emp);
            tablaEmpleado();
            limpiarDatoEmp();
            JOptionPane.showMessageDialog(rootPane, "Eliminado con éxito","  Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoEmpleado = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDirec = new javax.swing.JTextArea();
        idUsuario = new javax.swing.JComboBox<>();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtTel = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FORMULARIO EMPLEADOS");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel2.setText("Apellido:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel3.setText("Codigo Empleado:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel4.setText("Direccion:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel5.setText("Telefono:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel6.setText("Id usuario:");

        txtCodigoEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtCodigoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoEmpleadoKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        txtDirec.setColumns(20);
        txtDirec.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtDirec.setRows(5);
        jScrollPane1.setViewportView(txtDirec);

        idUsuario.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        btnInsertar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnCancelar.setText("Limpiar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        try {
            txtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInsertar)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnModificar)
                        .addGap(41, 41, 41)
                        .addComponent(btnEliminar)
                        .addGap(47, 47, 47)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtCodigoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(idUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodigoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        llenarTablaEmpleado();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        insertar();
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            modificar();
        } catch (Exception ex) {
            Logger.getLogger(FrmEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            eliminar();
        } catch (Exception ex) {
            Logger.getLogger(FrmEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarDatoEmp();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCodigoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoEmpleadoKeyTyped
        validar.numbersOnly(evt);
    }//GEN-LAST:event_txtCodigoEmpleadoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        validar.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        validar.wordsOnly(evt);
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
        validar.numbersOnly(evt);
    }//GEN-LAST:event_txtTelKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> idUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCodigoEmpleado;
    private javax.swing.JTextArea txtDirec;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
