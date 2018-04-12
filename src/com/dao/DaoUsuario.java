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
import javax.swing.JOptionPane;


/**
 *
 * @author DEVELOPER-4
 */
public class DaoUsuario extends Conexion{
    
    public void insertarUsuario(Usuario objUs) throws Exception{
        try {
            this.conectar();
            String sql="insert into usuario(usuario,contrasenia,id_rol) values(?,?,?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            
            ps.setString(1, objUs.getUser());
            ps.setString(2, objUs.getPass());
            ps.setInt(3, objUs.getRol());
            ps.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public void modificarUsario(Usuario objUs) throws Exception{
        try {
            this.conectar();
            String sql="update usuario set usuario=?,contrasenia=?,id_rol=? where id_usuario=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            
            ps.setString(1, objUs.getUser());
            ps.setString(2, objUs.getPass());
            ps.setInt(3, objUs.getRol());
            ps.setInt(4, objUs.getId_user());
            ps.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
    }
    
    public void eliminarUsuario(Usuario objUs){
        
        try {
            this.conectar();
            String sql="delete from usuario where id_usuario=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
           // ps.setInt();
        } catch (Exception e) {
            
        }
    }
    
    public boolean login(Usuario objUsu) throws Exception{
        boolean ingreso=false;
        ResultSet res;
        try {
            this.conectar();
            String sql="SELECT * FROM usuario WHERE usuario=? AND contrasenia=?";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            pst.setString(1, objUsu.getUser());
            pst.setString(2, objUsu.getPass());
            res=pst.executeQuery();
            if (res.next()) {   
               ingreso=true;
            }
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
        return ingreso;
    }
    
    public List LoginMostrar(Usuario objUsu) throws Exception{
        List listaUsuarios = new ArrayList();
        ResultSet res;
        try {
            this.conectar();
            String sql="SELECT * FROM usuario WHERE usuario=? AND contrasenia=?";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            pst.setString(1, objUsu.getUser());
            pst.setString(2, objUsu.getPass());
            res=pst.executeQuery();
            if (res.next()) {   
               Usuario obj=new Usuario();
               obj.setId_user(res.getInt("id_usuario"));
               obj.setUser(res.getString("usuario"));
               obj.setPass(res.getString("contrasenia"));
               obj.setRol(res.getInt("id_rol"));
               listaUsuarios.add(obj);
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
        return listaUsuarios;
    }
    /*public static void main(String[] args) throws Exception{
        Usuario obj1 = new Usuario();
        DaoUsuario obj2 = new DaoUsuario();
        obj1.setUser("Josue");
        obj1.setPass("123456");
        boolean resul= obj2.login(obj1);
        JOptionPane.showMessageDialog(null, resul);
    }*/
}
