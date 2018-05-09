/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Categoria;
import com.clases.Producto;
import com.clases.Proveedor;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class DaoProductos extends Conexion{
    
    public void insertarProducto (Producto objPro) throws Exception{
        try {
            this.conectar();
            String sql="INSERT INTO producto VALUES (NULL,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objPro.getCod_producto());
            ps.setString(2, objPro.getNombre());
            ps.setString(3, objPro.getDescripcion());
            ps.setDouble(4, objPro.getPrecio());
            ps.setInt(5, objPro.getCategoria().getIdCategoria());
            ps.setInt(6, objPro.getProveedor().getIdProveedor());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error dao");
        }
        finally{
            this.desconectar();
        }
    }
    
    public void modificarProducto (Producto objPro) throws Exception{
        try {
            this.conectar();
            String sql="UPDATE producto SET cod_producto=?, nombre=?, descripcion=?, precio=?, id_categoria=?, id_proveedor=? WHERE id_producto=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objPro.getCod_producto());
            ps.setString(2, objPro.getNombre());
            ps.setString(3, objPro.getDescripcion());
            ps.setDouble(4, objPro.getPrecio());
            ps.setInt(5, objPro.getCategoria().getIdCategoria());
            ps.setInt(6, objPro.getProveedor().getIdProveedor());
            ps.setInt(7, objPro.getIdProducto());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public void eliminarProducto (Producto objPro) throws Exception{
        try {
            this.conectar();
            String sql="DELETE FROM producto WHERE id_producto=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objPro.getIdProducto());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public List mostrarProducto() throws Exception{
        List listaProductos = new ArrayList();
        ResultSet res;
        try {
            this.conectar();//SELECT inv.id_inventario, pro.id_producto, pro.cod_producto, pro.nombre as nombreProducto, pro.descripcion, pro.precio, pro.id_categoria, cat.nombre as nombreCategoria, pro.id_proveedor, prov.nombre AS nombreProveedor, inv.cantidad_stock FROM inventario inv, producto pro, proveedor prov, categoria cat WHERE inv.id_producto=pro.id_producto AND pro.id_categoria=cat.id_categoria AND pro.id_proveedor=prov.id_proveedor
            String sql="SELECT pro.id_producto, pro.cod_producto, pro.nombre as nombreProducto, pro.descripcion, pro.precio, pro.id_categoria, cat.nombre as nombreCategoria, pro.id_proveedor, prov.nombre AS nombreProveedor FROM producto pro, proveedor prov, categoria cat WHERE pro.id_categoria=cat.id_categoria AND pro.id_proveedor=prov.id_proveedor";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            res=pst.executeQuery();
            while (res.next()) {
                Producto objPro = new Producto();
                Categoria objCat=new Categoria();
                Proveedor objProv=new Proveedor();
                objPro.setIdProducto(res.getInt("id_producto"));
                objPro.setCod_producto(res.getInt("cod_producto"));
                objPro.setNombre(res.getString("nombreProducto"));
                objPro.setDescripcion(res.getString("descripcion"));
                objPro.setPrecio(res.getDouble("precio"));
                objCat.setIdCategoria(res.getInt("id_categoria"));
                objCat.setNombre(res.getString("nombreCategoria"));
                objPro.setCategoria(objCat);
                objProv.setIdProveedor(res.getInt("id_proveedor"));
                objProv.setNombre(res.getString("nombreProveedor"));
                objPro.setProveedor(objProv);
                listaProductos.add(objPro);
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
        return listaProductos;
    }
}
