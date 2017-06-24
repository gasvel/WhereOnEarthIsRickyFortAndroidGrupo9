package com.example.gaston.rickyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import retrofit.RestAdapter;

public class PedirPistasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_pistas);
        CarmenSanDiegoService service = this.createService();
        service.obtenerPista();
    }

    private CarmenSanDiegoService createService(){
        String SERVER_IP = "10.0.2.2"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP_GENY +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }

    public void ordenDeArresto(View view) {
        Intent i = new Intent(this, OrdenDeArrestoActivity.class);
        startActivity(i);
    }

    public void viajar(View view){
        Intent i = new Intent(this, ViajarActivity.class);
        startActivity(i);
    }
}
