/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import com.clases.*;
import com.dao.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Dev04
 */
public class FrmInventario extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmInventario
     */
    Producto prod = new Producto();
    DaoInventario daoInv = new DaoInventario();
    DaoProductos daoProd = new DaoProductos();
    Inventario inv = new Inventario();
    
    
    public FrmInventario() {
        initComponents();
        llenarTablaInventario();
        llenarTablaProductos();                
    }
    
    public void llenarTablaProductos(){
        
        String[] colums = {"Id producto", "Nombre"};
        Object[] obj = new Object[2];
        DefaultTableModel objTabla = new DefaultTableModel(null, colums);
        List ls;
        
        try {
            ls = daoProd.mostrarProducto();                
                for(int i = 0; i < ls.size(); i++){
                   prod = (Producto) ls.get(i);
                   obj[0] = prod.getIdProducto();
                   obj[1] = prod.getNombre();
                   objTabla.addRow(obj);
                }
            this.tableProducts.setModel(objTabla);
        } catch (Exception e) {
        }
        
    }
    
    public void llenarTablaInventario(){        
        String[] columnas={"Id Inventario","ID Producto","Cod. Producto", "Nombre Prod.", "Precio", "Categoria","Proveedor", "Stock"};
        Object[]obj=new Object[8];
        DefaultTableModel objTabla = new DefaultTableModel(null, columnas);
        List lista;
        
        try {
            lista = daoInv.mostrarInventario();            
            for (int i = 0; i < lista.size(); i++) {
                inv = (Inventario) lista.get(i);
                obj[0] = inv.getIdInventario();
                obj[1] = inv.getProducto().getIdProducto();
                obj[2] = inv.getProducto().getCod_producto();
                obj[3] = inv.getProducto().getNombre();                
                obj[4] = inv.getProducto().getPrecio();
                obj[5] = inv.getProducto().getCategoria().getNombre();
                obj[6] = inv.getProducto().getProveedor().getNombre();
                obj[7] = inv.getCantidadStock();
                objTabla.addRow(obj);
            }
            this.jTable1.setModel(objTabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error tablaInventario() " + e.toString());
        }        
    }
    
    public void insertarDatosTabla(){
        
        int filas = this.jTable1.getSelectedRow();
        this.txtStock.setText(String.valueOf(this.jTable1.getValueAt(filas, 7)));
        int filas2 = this.tableProducts.getSelectedRow();
        String p = String.valueOf(this.jTable1.getValueAt(filas, 2));
        this.resp.setText(p);
        
    }
    public void limpiarDatos(){        
        this.txtStock.setText("");
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        resp = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Selecciona el producto:");

        jLabel2.setText("Stock:");

        btnInsertar.setText("Insertar");

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

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableProducts);

        resp.setText("res:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(txtStock)
                        .addGap(147, 147, 147)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInsertar)
                    .addComponent(btnCancelar))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resp)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnInsertar)
                        .addGap(37, 37, 37)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(resp)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        insertarDatosTabla();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiarDatos();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarDatos();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel resp;
    private javax.swing.JTable tableProducts;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
