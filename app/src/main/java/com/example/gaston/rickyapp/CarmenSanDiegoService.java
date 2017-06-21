package com.example.gaston.rickyapp;

import retrofit.Callback;
import retrofit.http.GET;
import java.util.List;



public interface CarmenSanDiegoService {

    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);
}
