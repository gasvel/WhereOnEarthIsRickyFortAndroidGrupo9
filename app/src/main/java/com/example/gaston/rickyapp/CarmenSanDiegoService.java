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

import javax.xml.transform.Result;


public interface CarmenSanDiegoService {

    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);

    @POST("/iniciarjuego")
    void iniciarJuego(Callback<Caso> callback);

    @POST("/viajar/{PaisId}")
    void viajarAPais(@retrofit.http.Path("PaisId") int id, Callback<Caso> callback);

    @GET("/pistaDelLugar/{Lugar}/{casoId}")
    void darPista(@retrofit.http.Path(value="Lugar" ,encode= false) String lugar, @retrofit.http.Path("casoId") int id, Callback<PistaAdapter> callback);

    @POST("/emitirOrdenPara/{VillanoId}/{CasoId}")
    void emitirOrden(@retrofit.http.Path("VillanoId") int villanoId, @retrofit.http.Path("CasoId") int id, Callback<Result> callback);
}
