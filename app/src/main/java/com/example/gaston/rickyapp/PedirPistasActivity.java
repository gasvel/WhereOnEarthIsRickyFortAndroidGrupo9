package com.example.gaston.rickyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Lugar;
import com.example.gaston.rickyapp.model.Pais;
import com.example.gaston.rickyapp.model.PistaAdapter;
import com.example.gaston.rickyapp.model.Pregunta;
import com.example.gaston.rickyapp.model.Villano;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PedirPistasActivity extends AppCompatActivity {

    private Pais actual;
    private TextView nombre;
    private Button botonLugar1;
    private Button botonLugar2;
    private Button botonLugar3;
    private Caso caso;
    private CarmenSanDiegoService service;
    private Villano villanoConOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_pistas);
        service = this.createService();
        if(getIntent().getExtras() != null) {
            this.actualizarDatos();
        }
    }

    private void actualizarDatos() {
        villanoConOrden = (Villano) getIntent().getExtras().get("villanoConOrden");
        if(villanoConOrden.getNombre() != null){
            TextView villano = (TextView) findViewById(R.id.villanoNombre);
            villano.setText(villanoConOrden.getNombre());
        }
        this.caso = (Caso) getIntent().getExtras().get("caso");
        actual = caso.getPais();
        nombre = (TextView) findViewById(R.id.paisActual);
        nombre.setText(actual.getNombre());
        botonLugar1 = (Button) findViewById(R.id.buttonL1);
        botonLugar1.setText(caso.getPais().getLugares().get(0).getNombre());
        botonLugar2 = (Button) findViewById(R.id.buttonL2);
        botonLugar2.setText( caso.getPais().getLugares().get(1).getNombre() );
        botonLugar3 = (Button) findViewById(R.id.buttonL3);
        botonLugar3.setText(caso.getPais().getLugares().get(2).getNombre());
    }

    private CarmenSanDiegoService createService() {
        String SERVER_IP = "192.168.0.105"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.101";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }


    public void buscarPista(final Lugar lugar){
        service.darPista(lugar.getNombre(), caso.getId(), new Callback<Pregunta>() {
            @Override
            public void success(Pregunta pregunta, Response response) {

                mostrarPregunta(pregunta,lugar);
         }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                Toast.makeText(PedirPistasActivity.this,"No me vino ninguna pista",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void mostrarPregunta(Pregunta pregunta ,Lugar lugar){
        Intent i = new Intent(this, PreguntaActivity.class);
        i.putExtra("lugar",lugar);
        i.putExtra("pregunta", pregunta);
        i.putExtra("caso",this.caso);
        startActivity(i);
    }

    public void revisarPista1(View view) {
        buscarPista(caso.getPais().getLugares().get(0));


    }

    public void revisarPista2(View view) {
        buscarPista(caso.getPais().getLugares().get(1));

    }

    public void revisarPista3(View view) {
        buscarPista(caso.getPais().getLugares().get(2));

    }

    public void ordenDeArresto(View view) {
        Intent i = new Intent(this, OrdenDeArrestoActivity.class);
        i.putExtra("caso",this.caso);
        i.putExtra("villanoConOrden",villanoConOrden);
        this.finish();
        startActivity(i);
    }

    public void viajar(View view){
        Intent i = new Intent(this, ViajarActivity.class);
        i.putExtra("caso",this.caso);
        i.putExtra("villanoConOrden",villanoConOrden);
        this.finish();
        startActivity(i);
    }
}
