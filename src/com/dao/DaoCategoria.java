/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

/**
 *
 * @author Dev04
 */

import com.clases.Categoria;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

   
    
public class DaoCategoria extends Conexion{
    
    public void insertarCategoria(Categoria objCat) throws Exception{
        
        try {            
            this.conectar();
            String sql ="INSERT INTO categoria(nombre)  VALUES (?)";
            PreparedStatement ps= this.getCon().prepareStatement(sql);            
            ps.setString(1, objCat.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        
    }
    
    public void modificarCategoria(Categoria objCat) throws Exception{
        try {
            this.conectar();
            String sql="UPDATE categoria SET nombre=? WHERE id_categoria=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            
            ps.setString(1, objCat.getNombre());
            ps.setInt(2, objCat.getIdCategoria());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error modificarCategoriaDAO " + e.toString());
        }finally{
            this.desconectar();
        }
    }    
    
    public void eliminarCategoria(Categoria objCat) throws Exception{
        try {
            this.conectar();
            String sql="DELETE FROM categoria WHERE id_categoria=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            
            ps.setInt(1, objCat.getIdCategoria());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
    }
    
    
    public List mostrarCategoria() throws Exception{
        
        List listaCategoria = new ArrayList();
        ResultSet rs;
        
        try {
            this.conectar();            
            String sql="SELECT * FROM categoria";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                Categoria objCat=new Categoria();
                objCat.setIdCategoria(rs.getInt("id_categoria"));
                objCat.setNombre(rs.getString("nombre"));
                listaCategoria.add(objCat);
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error DaoMostrar() " + e.toString());
        } finally{
            this.desconectar();
        }
                
        return listaCategoria;
    }
    
}

