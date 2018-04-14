
package com.dao;

import com.clases.Rol;
import com.clases.Usuario;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author DEVELOPER-4
 */
public class DaoRol extends Conexion{
    public List mostrarRol() throws Exception{
        List listaRol = new ArrayList();
        ResultSet res;
        try {
            this.conectar();
            String sql="select * from rol";
            PreparedStatement pst = this.getCon().prepareCall(sql);
            res=pst.executeQuery();
            while (res.next()) {
                Rol objRol = new Rol();
                objRol.setIdRol(res.getInt("id_rol"));
                objRol.setNombre(res.getString("nombre"));
                listaRol.add(objRol);
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            this.desconectar();
        }
        return listaRol;
    }
    
    /*public static void main(String[] args) throws Exception{
        DaoRol obDao = new DaoRol();
        List listaRol = obDao.mostrarRol();
        for (int i=0;i<listaRol.size();i++) {
            JOptionPane.showMessageDialog(null, i);
        }
    }*/
}
