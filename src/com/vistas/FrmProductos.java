/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import com.clases.*;
import com.dao.DaoCategoria;
import com.dao.DaoProductos;
import com.dao.DaoProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.FuncionesValidacion;

/**
 *
 * @author Daniel
 */
public class FrmProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmProductos
     */
    /*---- Instancias Globales --------*/
    Producto objPro = new Producto();
    DaoProductos objDaoPro = new DaoProductos();
    DaoCategoria objDaoCat = new DaoCategoria();
    DaoProveedor objDaoProv = new DaoProveedor();
    Categoria objCatTemp = new Categoria();
    Proveedor objProTemp = new Proveedor();
    List<Categoria> listComboCate;
    List<Proveedor> listComboProv;
    int txtIdProductos=0;
    private FuncionesValidacion validar = new FuncionesValidacion();
    
    public FrmProductos() {
        initComponents();
        tablaDep();
        DefaultComboBoxModel model = new DefaultComboBoxModel(); 
        DefaultComboBoxModel modelProveedor = new DefaultComboBoxModel();
        listComboCate = new ArrayList<>();
        listComboProv = new ArrayList<>();
        
        /*-------- Mostrar Categorias en comboBox ----------*/
        try {
            for (Object object : objDaoCat.mostrarCategoria()) {
                Categoria cate = (Categoria) object;
                listComboCate.add(cate);
                model.addElement(cate.getIdCategoria()+", "+cate.getNombre());
            }
            this.cbbCategoria.setModel(model);
        } catch (Exception e) {
           //JOptionPane.showInputDialog(ERROR);
        }
       
        /*-------- Mostrar Proveedores en comboBox ----------*/
        try {
            for (Object object : objDaoProv.mostrarProveedor()) {
                Proveedor prov = (Proveedor) object;
                listComboProv.add(prov);
                modelProveedor.addElement(prov.getIdProveedor()+", "+prov.getNombre());
            }
            this.cbbProveedor.setModel(modelProveedor);
        } catch (Exception e) {
           JOptionPane.showInputDialog(ERROR,"Error al mostrar");
        }
    }
    
    
    public void tablaDep(){
        String [] columnas = {"Id Producto","Codigo","Nombre","Descripcion","Precio","Categoria","Proveedor"};
        Object[] obj = new Object[7];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls=objDaoPro.mostrarProducto();
            for (int i = 0; i < ls.size(); i++) {
                objPro = (Producto)ls.get(i);
                obj[0]=objPro.getIdProducto();
                obj[1]=objPro.getCod_producto();
                obj[2]=objPro.getNombre();
                obj[3]=objPro.getDescripcion();
                obj[4]=objPro.getPrecio();
                obj[5]=objPro.getCategoria().getIdCategoria()+", "+objPro.getCategoria().getNombre();
                obj[6]=objPro.getProveedor().getIdProveedor()+", "+objPro.getProveedor().getNombre();
                tabla.addRow(obj);
            }
            this.tblProductos.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos " + e.toString());
        }
    }
    
    public void tocarTabla(){
        int fila;
        /*procedimiento*/
        fila = this.tblProductos.getSelectedRow();
        this.txtIdProductos=Integer.parseInt(String.valueOf(this.tblProductos.getValueAt(fila, 0)));
        System.out.println(txtIdProductos);
        this.txtCodProducto.setText(String.valueOf(this.tblProductos.getValueAt(fila, 1)));
        this.txtNombre.setText(String.valueOf(this.tblProductos.getValueAt(fila, 2)));
        this.txtDescripcion.setText(String.valueOf(this.tblProductos.getValueAt(fila, 3)));
        this.txtPrecio.setText(String.valueOf(this.tblProductos.getValueAt(fila, 4)));
        this.cbbCategoria.setSelectedItem(this.tblProductos.getValueAt(fila, 5));
        this.cbbProveedor.setSelectedItem(this.tblProductos.getValueAt(fila, 6));
    }
    
    public void recuperarDatos(){
        objPro.setIdProducto(this.txtIdProductos);
        objPro.setCod_producto(Integer.parseInt(this.txtCodProducto.getText()));
        objPro.setNombre(this.txtNombre.getText());
        objPro.setDescripcion(this.txtDescripcion.getText());
        objPro.setPrecio(Double.parseDouble(this.txtPrecio.getText()));
        
        String cate=(String)this.cbbCategoria.getSelectedItem();
        String[] categ = cate.split(",");
        
        objCatTemp.setIdCategoria(Integer.parseInt(categ[0]));
        System.out.println(categ[0]);
        objPro.setCategoria(objCatTemp);
        
        String prove=(String)this.cbbProveedor.getSelectedItem();
        String[] proved = prove.split(",");
        System.out.println(proved[0]);
        objProTemp.setIdProveedor(Integer.parseInt(proved[0]));
        objPro.setProveedor(objProTemp);
    }
    
    public void insertar() throws Exception{
        recuperarDatos();
        objDaoPro.insertarProducto(objPro);
    }
    
    public void modificar() throws Exception{
        recuperarDatos();
        int mensa = JOptionPane.showConfirmDialog(null, "Desea modificar");
        if (mensa==0) {
            objDaoPro.modificarProducto(objPro);
            limpiar();
        }else{
            limpiar();
        }
    }
    
    public void eliminar() throws Exception{
        recuperarDatos();
        int mensa = JOptionPane.showConfirmDialog(null, "Desea eliminar");
        if (mensa==0) {
            objDaoPro.eliminarProducto(objPro);
            limpiar();
        }else{
            limpiar();
        }
    }
    
    public void limpiar(){
        this.txtIdProductos=0;
        this.txtCodProducto.setText("");
        this.txtNombre.setText("");
        this.txtDescripcion.setText("");
        this.txtPrecio.setText("");
        this.cbbCategoria.setSelectedIndex(0);
        this.cbbProveedor.setSelectedIndex(0);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtPrecio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        cbbCategoria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbbProveedor = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FORMULARIO DE PRODUCTOS");

        jLabel1.setText("Codigo Producto:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripcion:");

        jLabel4.setText("Precio:");

        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos);

        jLabel5.setText("Categoria");

        jLabel6.setText("Proveedor");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombre)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(147, 147, 147))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        tocarTabla();
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            insertar();
            tablaDep();
            limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error metodo boton");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            modificar();
            tablaDep();
            limpiar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            eliminar();
            tablaDep();
            limpiar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCodProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyTyped
        validar.numbersOnly(evt);
    }//GEN-LAST:event_txtCodProductoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        validar.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        validar.numbersOnly(evt);
    }//GEN-LAST:event_txtPrecioKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbbCategoria;
    private javax.swing.JComboBox<String> cbbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
