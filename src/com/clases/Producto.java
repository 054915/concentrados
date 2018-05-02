package com.clases;

/**
 *
 * @author Daniel
 */
public class Producto {
    
    private int idProducto;
    private int cod_producto;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(int idProducto, int cod_producto, String nombre, String descripcion, double precio, Categoria categoria) {
        this.idProducto = idProducto;
        this.cod_producto = cod_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }      
    
}
