package com.example.gaston.rickyapp.model;


import java.io.Serializable;

public class PaisNombre implements Serializable {

    private int id;
    private String nombre;

    public PaisNombre(int nId, String nNombre){
        this.id= nId;
        this.nombre= nNombre;
    }

    public PaisNombre(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
