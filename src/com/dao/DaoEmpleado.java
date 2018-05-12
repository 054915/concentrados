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
 * @author Daniel
 */
public class DaoEmpleado extends Conexion{
    
    
    
    public void insertarEmpleado(Empleado objEmp) throws Exception{
        
        try {
            this.conectar();
            String sql="insert into empleado(cod_empleado,nombre,apellido,direccion,telefono,id_usuario) values(?,?,?,?,?,?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, objEmp.getCodigoEmpleado());
            ps.setString(2, objEmp.getNombreEmpleado());
            ps.setString(3, objEmp.getApellidoEmpleado());
            ps.setString(4, objEmp.getDireccionEmpleado());
            ps.setString(5, objEmp.getTelefonoEmpleado());
            ps.setInt(6, objEmp.getUsuario().getId_user());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error dao insertar "+e.toString());
        } finally{
            this.desconectar();
        }        
    }
    
    public void modificarEmpleado(Empleado objEmp) throws Exception{        
        try {
            this.conectar();
            String sql ="update empleado set cod_empleado=?,nombre=?,apellido=?,direccion=?,telefono=?,id_usuario=? where id_empleado=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            
            ps.setInt(1, objEmp.getCodigoEmpleado());
            ps.setString(2, objEmp.getNombreEmpleado());
            ps.setString(3, objEmp.getApellidoEmpleado());
            ps.setString(4, objEmp.getDireccionEmpleado());
            ps.setString(5, objEmp.getTelefonoEmpleado());
            ps.setInt(6, objEmp.getUsuario().getId_user());
            ps.setInt(7, objEmp.getIdEmpleado());
            ps.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error dao modificarEmpleado "+e.toString());
        } finally {
            this.desconectar();
        }        
    }
    
    public void eliminarEmpleado(Empleado objEmp) throws Exception{        
        try {
            this.conectar();
            String sql ="delete from empleado where id_empleado=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);                        
            ps.setInt(1, objEmp.getIdEmpleado());
            ps.executeUpdate();            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error dao eliminarEmpleado "+e.toString());
        } finally {
            this.desconectar();
        }        
    }
    
    public List mostrarEmpleados() throws Exception{
        
        List listEmp = new ArrayList();
        ResultSet rs;
        try {
            this.conectar();
            String sql ="select emp.id_empleado, emp.cod_empleado, emp.nombre, emp.apellido, emp.direccion, emp.telefono, emp.id_usuario, usu.usuario from empleado emp, usuario usu WHERE emp.id_usuario=usu.id_usuario";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                Empleado e = new Empleado();
                Usuario u = new Usuario();
                
                e.setIdEmpleado(rs.getInt("id_empleado"));
                e.setCodigoEmpleado(rs.getInt("cod_empleado"));
                e.setNombreEmpleado(rs.getString("nombre"));
                e.setApellidoEmpleado(rs.getString("apellido"));
                e.setDireccionEmpleado(rs.getString("direccion"));
                e.setTelefonoEmpleado(rs.getString("telefono"));
                u.setId_user(rs.getInt("id_usuario"));
                u.setUser(rs.getString("usuario"));
                e.setUsuario(u);
                listEmp.add(e);                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error dao mostrarEmpleados "+e.toString());
        } finally {
            this.desconectar();
        }  
        return listEmp;
    }
    
    
}
