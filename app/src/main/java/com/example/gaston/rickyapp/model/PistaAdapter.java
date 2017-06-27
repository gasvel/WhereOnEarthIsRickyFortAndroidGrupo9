package com.example.gaston.rickyapp.model;

import java.io.Serializable;



public class PistaAdapter implements Serializable{

    String pista ;

    public PistaAdapter(String p){
        pista = p;
    }

    public PistaAdapter(){

    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }
}
