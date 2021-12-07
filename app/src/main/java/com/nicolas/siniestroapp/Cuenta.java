package com.nicolas.siniestroapp;

import java.util.UUID;

public class Cuenta {
    private String ID;
    private String correo;
    private String contraseña;
    private String nombre;
    private String genero;
    private String fecha_nacimiento;

    public Cuenta() {
        this.ID = UUID.randomUUID().toString();
        this.correo = "test@test.test";
        this.contraseña = Tools.encriptarMD5("test");
        this.nombre = "nombre test";
        this.genero = "genero test";
        this.fecha_nacimiento = "01/12/test";
    }

    public Cuenta(String ID, String correo, String contraseña, String nombre, String genero, String fecha_nacimiento) {
        this.ID = ID;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public Cuenta(String correo, String contraseña, String nombre, String genero, String fecha_nacimiento) {
        this.ID = UUID.randomUUID().toString();
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
