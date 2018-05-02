/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Categoria;
import com.clases.Producto;
import com.clases.Rol;
import com.clases.Usuario;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class DaoProductos extends Conexion{
    
    public void insertarProducto (Producto objPro) throws Exception{
        try {
            this.conectar();
            String sql="INSERT INTO producto VALUES (NULL,?,?,?,?,?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objPro.getIdProducto());
            ps.setString(2, objPro.getNombre());
            ps.setString(3, objPro.getDescripcion());
            ps.setDouble(4, objPro.getPrecio());
            ps.setInt(5, objPro.getCategoria().getIdCategoria());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public void modificarProducto (Producto objPro) throws Exception{
        try {
            this.conectar();
            String sql="UPDATE producto SET cod_producto=?, nombre=?, descripcion=?, precio=?, id_categoria=? WHERE id_producto=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objPro.getCod_producto());
            ps.setString(2, objPro.getNombre());
            ps.setString(3, objPro.getDescripcion());
            ps.setDouble(4, objPro.getPrecio());
            ps.setInt(5, objPro.getCategoria().getIdCategoria());
            ps.setInt(6, objPro.getIdProducto());
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
            this.conectar();
            String sql="SELECT * FROM producto";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            res=pst.executeQuery();
            while (res.next()) {
                Producto objPro = new Producto();
                Categoria objCat=new Categoria();
                objPro.setIdProducto(res.getInt("id_producto"));
                objPro.setCod_producto(res.getInt("cod_producto"));
                objPro.setNombre(res.getString("nombre"));
                objPro.setDescripcion(res.getString("descripcion"));
                objPro.setPrecio(res.getDouble("precio"));
                objCat.setIdCategoria(res.getInt("id_categoria"));
                objPro.setCategoria(objCat);
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
