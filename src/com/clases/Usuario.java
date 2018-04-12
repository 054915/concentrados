
package com.clases;

public class Usuario {
    private int id_user;
    private String user;
    private String pass;
    private int rol;
    private Rol objRol;

    public Usuario() {
    }

    public Usuario(int id_user, String user, String pass, int rol,Rol objRol) {
        this.id_user = id_user;
        this.user = user;
        this.pass = pass;
        this.rol = rol;
        this.objRol = objRol;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Rol getObjRol() {
        return objRol;
    }

    public void setObjRol(Rol objRol) {
        this.objRol = objRol;
    }
    
}
