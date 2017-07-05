package com.example.gaston.rickyapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gaston on 05/07/2017.
 */

public class Pregunta implements Serializable {

    String texto;
    List<Respuesta> respuestas;
    String categoria;

    public Pregunta(String textoP, List<Respuesta> respuestasP, String categoriaP){
        this.texto = textoP;
        this.respuestas = respuestasP;
        this.categoria = categoriaP;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
