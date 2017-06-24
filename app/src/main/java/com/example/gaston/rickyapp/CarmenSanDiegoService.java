package com.example.gaston.rickyapp;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Villano;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;



public interface CarmenSanDiegoService {

    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);

    @POST("/iniciarjuego")
    void iniciarJuego(Callback<Caso> callback);

    @GET("/pistaDelLugar/:lugar/:casoId")
    void obtenerPista();
}
