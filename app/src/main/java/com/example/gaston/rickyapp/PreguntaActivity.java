package com.example.gaston.rickyapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Lugar;
import com.example.gaston.rickyapp.model.PistaAdapter;
import com.example.gaston.rickyapp.model.Pregunta;
import com.example.gaston.rickyapp.model.Respuesta;
import com.example.gaston.rickyapp.model.RespuestaAdapter;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PreguntaActivity extends AppCompatActivity {
    Respuesta respuesta1 ;
    Respuesta respuesta2 ;
    Respuesta respuesta3 ;
    int respuestaElegida;
    CarmenSanDiegoService service;
    Caso caso;
    Lugar lugar;
    Button boton1;
    Button boton2;
    Button boton3;
    PistaAdapter pista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        service = this.createService();

        if(getIntent().getExtras() != null) {
            this.actualizarDatos();
        }
    }

    private void actualizarDatos() {
        this.lugar = (Lugar) getIntent().getExtras().get("lugar");
        this.caso = (Caso) getIntent().getExtras().get("caso");
        Pregunta preg= (Pregunta) getIntent().getExtras().get("pregunta");
        this.respuesta1 = preg.getRespuestas().get(0);
        this.respuesta2 = preg.getRespuestas().get(1);
        this.respuesta3 = preg.getRespuestas().get(2);
        TextView textoPreg = (TextView) findViewById(R.id.textoPregunta) ;
        this.boton1 = (Button) findViewById(R.id.pregunta1);
        this.boton2 = (Button) findViewById(R.id.pregunta2);
        this.boton3 = (Button) findViewById(R.id.pregunta3);
        boton1.setText(this.respuesta1.getTexto());
        boton2.setText(this.respuesta2.getTexto());
        boton3.setText(this.respuesta3.getTexto());
        textoPreg.setText(preg.getTexto());


    }

    private CarmenSanDiegoService createService() {
        String SERVER_IP = "192.168.1.40"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.101";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }


    public void responder1(View view){
        this.respuestaElegida = this.respuesta1.getId();
        this.contestar();
    }

    public void responder2(View view) {
        this.respuestaElegida = this.respuesta2.getId();
        this.contestar();


    }

    public void responder3(View view){
        this.respuestaElegida = this.respuesta3.getId();
        this.contestar();


    }

    private void contestar(){
        Lugar lugar = (Lugar) getIntent().getExtras().get("lugar");
        service.enviarRespuesta(new RespuestaAdapter(this.respuestaElegida),lugar.getNombre(),this.caso.getId(),new Callback<PistaAdapter>(){
            @Override
            public void success(PistaAdapter pista, Response response) {
                nuevaRespuesta(pista);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });

    }

    private void nuevaRespuesta(PistaAdapter pista){
        if(pista.getPista().contains("incorrecta")){
            actualizarBotonIncorrecto();
            Button backButton = (Button) findViewById(R.id.backButton);
            backButton.setVisibility(View.VISIBLE);
            Toast.makeText(PreguntaActivity.this,"Respuesta Incorrecta",Toast.LENGTH_SHORT).show();
        }
        else{
            actualizarBotonCorrecto();
            Button acceptButton = (Button) findViewById(R.id.acceptButton);
            acceptButton.setVisibility(View.VISIBLE);
            this.pista = pista;
            Toast.makeText(PreguntaActivity.this,"Correcto!",Toast.LENGTH_SHORT).show();

        }
    }

    private void actualizarBotonIncorrecto(){
        switch(this.respuestaElegida){
            case 1: this.boton1.setBackgroundColor(Color.RED);

                    break;
            case 2: this.boton2.setBackgroundColor(Color.RED);
                    break;
            case 3: this.boton3.setBackgroundColor(Color.RED);
                    break;
        }
        deshabilitarBotones();
    }

    private void deshabilitarBotones(){
        this.boton1.setActivated(false);
        this.boton2.setActivated(false);
        this.boton3.setActivated(false);

    }

    private void actualizarBotonCorrecto(){
        switch(this.respuestaElegida){
            case 1: this.boton1.setBackgroundColor(Color.GREEN);
                break;
            case 2: this.boton2.setBackgroundColor(Color.GREEN);
                break;
            case 3: this.boton3.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void accept(View view){
        this.finish();
        Intent i = new Intent(this, MostrarPistaActivity.class);
        i.putExtra("pista",this.pista);
        i.putExtra("lugar",this.lugar );
        startActivity(i);

    }

    public void back(View view){
        this.finish();
    }
}
