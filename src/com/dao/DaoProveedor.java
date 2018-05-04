/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.*;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEVELOPER-4
 */
public class DaoProveedor extends Conexion {
    public void insertarProveedor(Proveedor objPro) throws Exception{
        try {
            this.conectar();
            String sql="INSERT INTO proveedor VALUES (NULL,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, objPro.getNombre());
            ps.setString(2, objPro.getDireccion());
            ps.setString(3, objPro.getTelefono());
            ps.setString(4, objPro.getNit());
            ps.setString(5, objPro.getNfc());
            ps.setString(6, objPro.getGiro());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    public void modificarProveedor (Proveedor objPro) throws Exception{
        try {
            this.conectar();
            String sql="UPDATE proveedor SET nombre=?, direccion=?, telefono=?, nit=?, ncf=?, giro=? WHERE id_proveedor=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, objPro.getNombre());
            ps.setString(2, objPro.getDireccion());
            ps.setString(3, objPro.getTelefono());
            ps.setString(4, objPro.getNit());
            ps.setString(5, objPro.getNfc());
            ps.setString(6, objPro.getGiro());
            ps.setInt(7, objPro.getIdProveedor());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    public void eliminarProveedor (Proveedor objPro) throws Exception{
        try {
            this.conectar();
            String sql="DELETE FROM proveedor WHERE id_proveedor=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objPro.getIdProveedor());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    public List mostrarProveedor() throws Exception{
        List listaProveedor = new ArrayList();
        ResultSet res;
        try {
            this.conectar();
            String sql="SELECT * FROM proveedor";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            res=pst.executeQuery();
            while (res.next()) {
                Proveedor objPro = new Proveedor();
                objPro.setIdProveedor(res.getInt("id_proveedor"));
                objPro.setNombre(res.getString("nombre"));
                objPro.setDireccion(res.getString("direccion"));
                objPro.setTelefono(res.getString("telefono"));
                objPro.setNit(res.getString("nit"));
                objPro.setNfc(res.getString("ncf"));
                objPro.setGiro(res.getString("giro"));
                listaProveedor.add(objPro);
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
        return listaProveedor;
    }
}
