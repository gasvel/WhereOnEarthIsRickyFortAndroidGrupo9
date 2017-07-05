package com.example.gaston.rickyapp.model;

import java.io.Serializable;

/**
 * Created by Gaston on 05/07/2017.
 */

public class Respuesta implements Serializable{

    int id;
    String texto;

    public Respuesta(){

    }

    public Respuesta(int idRes, String textoRes){
        this.id=idRes;
        this.texto=textoRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
