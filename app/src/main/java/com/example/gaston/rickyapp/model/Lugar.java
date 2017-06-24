package com.example.gaston.rickyapp.model;


import java.io.Serializable;

public class Lugar implements Serializable{

    private String nombre;

    public Lugar(String nNombre){
        this.nombre = nNombre;
    }



    public Lugar(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
