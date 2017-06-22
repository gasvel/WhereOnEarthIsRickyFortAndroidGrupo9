package com.example.gaston.rickyapp.model;


import java.util.List;

public class Pais {

    private int id;
    private String nombre;
    private List<Lugar> lugares;
    private List<PaisNombre> conexiones;

    public Pais(int nId , String nNombre, List<Lugar> nLugares, List<PaisNombre> nConexiones){
        this.id = nId;
        this.nombre = nNombre;
        this.lugares = nLugares;
        this.conexiones = nConexiones;
    }

    public Pais(){

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

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public List<PaisNombre> getConexiones() {
        return conexiones;
    }

    public void setConexiones(List<PaisNombre> conexiones) {
        this.conexiones = conexiones;
    }
}
