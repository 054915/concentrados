/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Categoria;
import com.conexion.Conexion;
import com.clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEVELOPER-4
 */
public class DaoInventario extends Conexion{
    
    public void insertarInventario (Inventario objInv) throws Exception{
        try {
            this.conectar();
            String sql="INSERT INTO inventario VALUES (NULL,?,?);";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objInv.getProducto().getCod_producto());
            ps.setInt(2, objInv.getCantidadStock());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public void modificarInventario (Inventario objInv) throws Exception{
        try {
            this.conectar();
            String sql="UPDATE inventario SET id_producto=?, cantidad_stock=? WHERE id_inventario=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objInv.getProducto().getCod_producto());
            ps.setInt(2, objInv.getCantidadStock());
            ps.setInt(3, objInv.getIdInventario());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public void eliminarInventario (Inventario objInv) throws Exception{
        try {
            this.conectar();
            String sql="DELETE FROM inventario WHERE id_inventario=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objInv.getIdInventario());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public List mostrarInventario() throws Exception{
        List listaInventario = new ArrayList();
        ResultSet res;
        try {
            this.conectar();//SELECT inv.id_inventario, pro.id_producto, pro.cod_producto, pro.nombre as nombreProducto, pro.descripcion, pro.precio, pro.id_categoria, cat.nombre as nombreCategoria, pro.id_proveedor, prov.nombre AS nombreProveedor, inv.cantidad_stock FROM inventario inv, producto pro, proveedor prov, categoria cat WHERE inv.id_producto=pro.id_producto AND pro.id_categoria=cat.id_categoria AND pro.id_proveedor=prov.id_proveedor
            String sql="SELECT inv.id_inventario, pro.id_producto, pro.cod_producto, pro.nombre as nombreProducto, pro.descripcion, pro.precio, pro.id_categoria, cat.nombre as nombreCategoria, pro.id_proveedor, prov.nombre AS nombreProveedor, inv.cantidad_stock FROM inventario inv, producto pro, proveedor prov, categoria cat WHERE inv.id_producto=pro.id_producto AND pro.id_categoria=cat.id_categoria AND pro.id_proveedor=prov.id_proveedor";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            res=pst.executeQuery();
            while (res.next()) {
                Inventario objInv = new Inventario();
                Producto objPro = new Producto();
                Categoria objCat=new Categoria();
                Proveedor objProv=new Proveedor();
                objInv.setIdInventario(res.getInt("id_inventario"));
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
                objInv.setProducto(objPro);
                objInv.setCantidadStock(res.getInt("cantidad_stock"));
                listaInventario.add(objInv);
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
        return listaInventario;
    }
}
