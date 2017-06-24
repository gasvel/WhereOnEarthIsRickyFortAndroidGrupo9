package com.example.gaston.rickyapp.model;


import java.io.Serializable;
import java.util.List;

public class Caso implements Serializable{
    private int id;
    private Pais pais;
    private List<PaisNombre> paisesVisitados;
    private List<PaisNombre> paisesFallidos;

    public Caso(int nId, Pais nPais, List<PaisNombre> nVisitados, List<PaisNombre> nFallidos){
        this.id = nId;
        this.pais = nPais;
        this.paisesVisitados = nVisitados;
        this.paisesFallidos = nFallidos;
    }

    public Caso(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<PaisNombre> getPaisesVisitados() {
        return paisesVisitados;
    }

    public void setPaisesVisitados(List<PaisNombre> paisesVisitados) {
        this.paisesVisitados = paisesVisitados;
    }

    public List<PaisNombre> getPaisesFallidos() {
        return paisesFallidos;
    }

    public void setPaisesFallidos(List<PaisNombre> paisesFallidos) {
        this.paisesFallidos = paisesFallidos;
    }


}
