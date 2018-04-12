
package com.clases;

public class Usuario {
    private int id_user;
    private String user;
    private String pass;
    private int rol;

    public Usuario() {
    }

    public Usuario(int id_user, String user, String pass, int rol) {
        this.id_user = id_user;
        this.user = user;
        this.pass = pass;
        this.rol = rol;
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
    
}
