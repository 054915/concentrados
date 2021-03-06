/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import com.clases.Categoria;
import com.dao.DaoCategoria;
import static java.awt.Frame.ICONIFIED;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.FuncionesValidacion;

/**
 *
 * @author Dev04
 */
public class FrmCategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmCategorias
     */
    public FrmCategorias() {
        initComponents();
        tablaCategoria();

    }

    FuncionesValidacion validar= new FuncionesValidacion();
    Categoria objCat= new Categoria();
    DaoCategoria objDaoCat = new DaoCategoria();
    
    
    public void tablaCategoria(){
        
        //nombres de encabezados
        String[] columnas={"Id Categoria", "Nombre"};
        //array de objeto 2 de encabezados
        Object[]obj=new Object[2];
        //asignar encabezados
        DefaultTableModel objTabla = new DefaultTableModel(null, columnas);
        List lista;
        
        try {
            lista = objDaoCat.mostrarCategoria();
            
            for (int i = 0; i < lista.size(); i++) {
                objCat = (Categoria) lista.get(i);
                obj[0] = objCat.getIdCategoria();
                obj[1] = objCat.getNombre();
                objTabla.addRow(obj);
            }
            this.jTable1.setModel(objTabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error tablaCategoria() " + e.toString());
        }
        
    }    
    public void llenarTabla(){
        int filas = this.jTable1.getSelectedRow();
        this.txtNombre.setText(String.valueOf(this.jTable1.getValueAt(filas, 1)));        
    }
    public void limpiar(){
        txtNombre.setText("");
    }
    
    public void insertarCategoria() throws Exception{
        objCat.setNombre(this.txtNombre.getText());        
        objDaoCat.insertarCategoria(objCat);  
        tablaCategoria();
        limpiar(); 
    }
    
    public void modificarCategoria() throws Exception{
        int filas = this.jTable1.getSelectedRow();
        objCat.setIdCategoria((int) this.jTable1.getValueAt(filas, 0));
        objCat.setNombre(this.txtNombre.getText());
        
        int confirmacionModificar = JOptionPane.showConfirmDialog(this, "Desea modificar la categoria","Modificar categoria", JOptionPane.YES_NO_OPTION);
        if (confirmacionModificar == 0) {
            objDaoCat.modificarCategoria(objCat);
            JOptionPane.showMessageDialog(rootPane, "Modificado con exito","  Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
        }
        tablaCategoria();
        limpiar();        
    }
    public void eliminarCategoria() throws Exception{
        int filas = this.jTable1.getSelectedRow();
        objCat.setIdCategoria((int) this.jTable1.getValueAt(filas, 0));
        objCat.setNombre(this.txtNombre.getText());
        
        int confirmacionModificar = JOptionPane.showConfirmDialog(this, "Desea Eliminar la categoria","Eliminar categoria", JOptionPane.YES_NO_OPTION);
        if (confirmacionModificar == 0) {
            objDaoCat.eliminarCategoria(objCat);
            JOptionPane.showMessageDialog(rootPane, "Eliminado con exito","  Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
        }
        tablaCategoria();
        limpiar();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FORMULARIO CATEGORIAS");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel1.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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

        jScrollPane1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

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
        jScrollPane1.setViewportView(jTable1);

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        llenarTabla();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked

    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        try {
            modificarCategoria();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error modificar Categoria boton " + ex.toString());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        
        try {
            eliminarCategoria();
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error eliminarCategoria " + ex.toString());
        }
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            insertarCategoria();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error insertarCategoria " + ex.toString());
        }
        limpiar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        validar.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
