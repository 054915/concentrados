/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

/**
 *
 * @author Daniel
 */
public class Empleado {
    
    private int idEmpleado;
    private int codigoEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String direccionEmpleado;
    private String telefonoEmpleado;
    private Usuario usuario;

    public Empleado() {
    }
    public Empleado(int idEmpleado, int codigoEmpleado, String nombreEmpleado, String apellidoEmpleado, String direccionEmpleado, String telefonoEmpleado, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.codigoEmpleado = codigoEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.usuario = usuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }
    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }
    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }
    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }
    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }
    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }
    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
