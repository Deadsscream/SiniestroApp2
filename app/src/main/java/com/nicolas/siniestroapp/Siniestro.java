package com.nicolas.siniestroapp;

import java.util.UUID;

public class Siniestro {
    private String ID;
    private String UUIDPropietario;
    private String titulo;
    private String descripcion;
    private String latitud;
    private String longitud;
    //foto
    private String fecha;

    public Siniestro() {
    }

    public Siniestro(String UUIDPropietario, String titulo, String descripcion, String latitud, String longitud, String fecha) {
        this.ID = UUID.randomUUID().toString();
        this.UUIDPropietario = UUIDPropietario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUUIDPropietario(String UUIDPropietario) {
        this.UUIDPropietario = UUIDPropietario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getID() {
        return ID;
    }

    public String getUUIDPropietario() {
        return UUIDPropietario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getFecha() {
        return fecha;
    }
}
