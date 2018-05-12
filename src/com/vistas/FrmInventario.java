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
    int idInventario=0;
    int idProducto=0;
    
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
    
    public void tocarTablaInventario(){
        int filas = this.jTable1.getSelectedRow();
        
        idInventario=Integer.parseInt(String.valueOf(this.jTable1.getValueAt(filas, 0)));
        idProducto=Integer.parseInt(String.valueOf(this.jTable1.getValueAt(filas, 1)));
        this.txtProducto.setText(String.valueOf(this.jTable1.getValueAt(filas, 3)));
        this.txtStock.setText(String.valueOf(this.jTable1.getValueAt(filas, 7)));
    }
    
    public void recuperarDatos(){
        inv.setIdInventario(idInventario);
        prod.setIdProducto(idProducto);
        inv.setProducto(prod);
        inv.setCantidadStock(Integer.parseInt(this.txtStock.getText()));
        //System.out.println(inv.toString());
        //System.out.println(prod.toString());
    }
    
    public void insertar(){
        recuperarDatos();
        try {
            daoInv.insertarInventario(inv);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al metodo insertar "+e.toString());
        }
    }
    
    public void modificar(){
        int mensa = JOptionPane.showConfirmDialog(null, "Desea modificar");
        if (mensa==0) {
            recuperarDatos();
            try {
                daoInv.modificarInventario(inv);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al metodo modificar "+ e.toString());
            }
        }else{
            limpiarDatos();
        }
    }
    
    public void eliminar(){
        int mensa = JOptionPane.showConfirmDialog(null, "Desea eliminar");
        if (mensa==0) {
            recuperarDatos();
            try {
                daoInv.eliminarInventario(inv);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al metodo eliminar "+ e.toString());
            }
        }else{
            limpiarDatos();
        }
    }
    
    public void tocarTablaProducto(){
        int filas = this.tableProducts.getSelectedRow();
        
        idProducto=Integer.parseInt(String.valueOf(this.tableProducts.getValueAt(filas, 0)));
        this.txtProducto.setText(String.valueOf(this.tableProducts.getValueAt(filas, 1)));
    }
    
    public void limpiarDatos(){
        idInventario=0;
        idProducto=0;
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
        txtProducto = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel1.setText("Selecciona el producto:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel2.setText("Stock:");

        txtStock.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        btnInsertar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
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

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnCancelar.setText("Limpiar");
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

        tableProducts.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
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
        tableProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProducts);

        resp.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        resp.setText("Producto:");

        txtProducto.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtProducto.setEnabled(false);

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

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(resp, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addComponent(txtStock, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(112, 112, 112))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(130, 130, 130))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resp)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addGap(25, 25, 25)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnInsertar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tocarTablaInventario();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiarDatos();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarDatos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tableProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductsMouseClicked
        tocarTablaProducto();
    }//GEN-LAST:event_tableProductsMouseClicked

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        insertar();
        llenarTablaInventario();
        limpiarDatos();
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        llenarTablaInventario();
        limpiarDatos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        llenarTablaInventario();
        limpiarDatos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        recuperarDatos();
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel resp;
    private javax.swing.JTable tableProducts;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
