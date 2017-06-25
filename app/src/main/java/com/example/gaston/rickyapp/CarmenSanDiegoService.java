package com.example.gaston.rickyapp;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.PistaAdapter;
import com.example.gaston.rickyapp.model.Villano;
import com.example.gaston.rickyapp.model.Lugar;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;



public interface CarmenSanDiegoService {

    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);

    @POST("/iniciarjuego")
    void iniciarJuego(Callback<Caso> callback);

    @POST("/viajar/{PaisId}")
    void viajarAPais(@retrofit.http.Path("PaisId") int id, Callback<Caso> callback);

    @GET("pistaDelLugar/:lugar/{casoId}")
    void darPista(@Body Lugar lugar, @retrofit.http.Path("casoId") int id, Callback<PistaAdapter> callback);
}
