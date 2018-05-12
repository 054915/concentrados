/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

/**
 *
 * @author DEVELOPER-4
 */
public class Inventario {
    private int idInventario;
    private Producto producto;
    private int cantidadStock;

    public Inventario() {
    }

    public Inventario(int idInventario, Producto producto, int cantidadStock) {
        this.idInventario = idInventario;
        this.producto = producto;
        this.cantidadStock = cantidadStock;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    @Override
    public String toString() {
        return "Inventario{" + "idInventario=" + idInventario + ", producto=" + producto + ", cantidadStock=" + cantidadStock + '}';
    }
    
}
