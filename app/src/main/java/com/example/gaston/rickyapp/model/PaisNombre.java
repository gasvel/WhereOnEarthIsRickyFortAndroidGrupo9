package com.example.gaston.rickyapp.model;

/**
 * Created by Gaston on 22/06/2017.
 */

public class PaisNombre {

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
