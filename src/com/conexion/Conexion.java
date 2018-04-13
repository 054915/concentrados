package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author Administrador
 */
public class Conexion {
    private Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void conectar() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/concentrados?user=root&password=");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al conectar");
        }
    }
    
    public void desconectar() throws Exception{
        try {
            if (con.isClosed()==false) {
                con.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
