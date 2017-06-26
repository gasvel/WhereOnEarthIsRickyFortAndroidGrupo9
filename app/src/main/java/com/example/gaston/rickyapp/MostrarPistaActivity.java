package com.example.gaston.rickyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaston.rickyapp.model.Lugar;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.PistaAdapter;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class MostrarPistaActivity extends AppCompatActivity{

    private Caso caso;
    private Lugar lugar;
    private CarmenSanDiegoService service;
    private TextView infoPista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_pistas);
        service = this.createService();
        if(getIntent().getExtras() != null) {
            this.actualizarDatos();
        }
    }

    private CarmenSanDiegoService createService() {
        String SERVER_IP = "192.168.0.101"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.101";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }

    private void actualizarDatos() {
        this.caso = (Caso) getIntent().getExtras().get("caso");
        this.lugar = (Lugar) getIntent().getExtras().get("lugar");
        TextView nombreLugar = (TextView) findViewById(R.id.nombreLugar) ;
        nombreLugar.setText(lugar.getNombre());
        this.service.darPista(this.lugar.getNombre(), this.caso.getId(), new Callback<PistaAdapter>() {
            @Override
            public void success(PistaAdapter pistaAdapter, Response response) {
                mostrarLaPista(pistaAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MostrarPistaActivity.this,"No me vino ninguna pista",Toast.LENGTH_SHORT);
            }
        });
    }

    private void mostrarLaPista(PistaAdapter pista){
        this.infoPista = (TextView) findViewById(R.id.infoPista);
        this.infoPista.setText(pista.getPista());
    }
}
