package com.example.gaston.rickyapp.model;



public class Lugar {

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
