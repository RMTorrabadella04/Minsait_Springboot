package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Ejercicio {
    private String titulo;
    private String descripcion;
    private String textoLargo;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private String copia;

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLongText() {
        return textoLargo;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCopia() {
        return copia;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLongText(String longText) {
        this.textoLargo = longText;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCopia(String copia) {
        this.copia = copia;
    }
}
