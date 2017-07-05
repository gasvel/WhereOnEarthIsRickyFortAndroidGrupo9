package com.example.gaston.rickyapp.model;

import java.io.Serializable;

/**
 * Created by Gaston on 05/07/2017.
 */

public class RespuestaAdapter implements Serializable{

    int id;

    public RespuestaAdapter(int idRes){
        this.id=idRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
