package com.example.gaston.rickyapp.model;


import java.io.Serializable;
import java.util.List;

public class Villano implements Serializable {

    private int id;
    private String nombre;
    private String sexo;
    private List<String> senas_particulares;
    private List<String> hobbies;

    public Villano(String nNombre, String nSexo, List<String> senas, List<String> nHobbies){
        this.nombre = nNombre;
        this.senas_particulares = senas;
        this.sexo = nSexo;
        this.hobbies = nHobbies;
    }

    public Villano(){

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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<String> getSenas_particulares() {
        return senas_particulares;
    }

    public void setSenas_particulares(List<String> senas_particulares) {
        this.senas_particulares = senas_particulares;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }



}
