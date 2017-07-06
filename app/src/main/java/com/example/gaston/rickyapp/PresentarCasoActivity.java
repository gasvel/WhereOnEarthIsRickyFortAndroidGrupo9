package com.example.gaston.rickyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Villano;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PresentarCasoActivity extends AppCompatActivity {

    private TextView prst;
    private String presentacion= "";
    private Caso casoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentar_caso);

        CarmenSanDiegoService service = this.createService();
        service.iniciarJuego(new Callback<Caso>(){
            @Override
            public void success(Caso caso, Response response) {
                nuevoCaso(caso);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });

        }

    private void nuevoCaso(Caso caso){
        casoActual = caso;
        presentacion = "Se han robado un valioso objeto en el pais " + caso.getPais().getNombre() +
                " .Su trabajo es encontrar al villano y traer el objeto sano y salvo.";

        prst = (TextView) findViewById(R.id.presentacionCaso);
        prst.setText(presentacion);


        if(presentacion.contains("Brasil")){
            ImageView img = (ImageView) findViewById(R.id.imagenPais);
            img.setImageResource(R.drawable.cristo_redentor);
        }

        if(presentacion.contains("Argentina")){
            ImageView img = (ImageView) findViewById(R.id.imagenPais);
            img.setImageResource(R.drawable.obelisco);
        }

        if(presentacion.contains("Mexico")){
            ImageView img = (ImageView) findViewById(R.id.imagenPais);
            img.setImageResource(R.drawable.mariachi);
        }

        if(presentacion.contains("EEUU")){
            ImageView img = (ImageView) findViewById(R.id.imagenPais);
            img.setImageResource(R.drawable.trump1);
        }

        if(presentacion.contains("Alemania")){
            ImageView img = (ImageView) findViewById(R.id.imagenPais);
            img.setImageResource(R.drawable.hitla);
        }
    }

    public void salir(View view){
        finish();
    }

    private CarmenSanDiegoService createService(){
        String SERVER_IP = "192.168.0.105"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.108";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }

    public void aceptarCaso(View view){
        Intent i = new Intent(this,PedirPistasActivity.class);
        i.putExtra("caso", casoActual);
        i.putExtra("villanoConOrden", new Villano());

        this.finish();
        startActivity(i);
    }
}
