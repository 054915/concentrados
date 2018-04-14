/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Producto;
import com.conexion.Conexion;

/**
 *
 * @author Daniel
 */
public class DaoProductos extends Conexion{
    
    public void insertarProducto (Producto objPro){
        
        try {
            this.conectar();
            String sql="insert into producto()";
            
        } catch (Exception e) {
        }
        
        
    }
    
    
}
