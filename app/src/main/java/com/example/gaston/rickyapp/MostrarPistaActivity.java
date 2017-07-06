package com.example.gaston.rickyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private PistaAdapter pista;
    private Lugar lugar;
    private TextView infoPista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_pistas);
        if(getIntent().getExtras() != null) {
            this.actualizarDatos();
        }
    }

    private void actualizarDatos() {
        infoPista = (TextView) findViewById(R.id.infoPista);
        pista = (PistaAdapter) getIntent().getExtras().get("pista");
        infoPista.setText(pista.getPista());
        lugar = (Lugar) getIntent().getExtras().get("lugar");
        TextView nombreLugar = (TextView) findViewById(R.id.nombreLugar) ;
        nombreLugar.setText(lugar.getNombre());
        if(pista.getPista().contains("Atrapaste")){
            Button finish = (Button) findViewById(R.id.finishButton);
            finish.setVisibility(View.VISIBLE);
            Button accept = (Button) findViewById(R.id.aceptarBoton);
            accept.setVisibility(View.INVISIBLE);
            ImageView meme = (ImageView) findViewById(R.id.meme);
            meme.setImageResource(R.drawable.komo_lo_zupo);
        }
        if(pista.getPista().contains("It's a trap")){
            ImageView meme = (ImageView) findViewById(R.id.meme);
            meme.setImageResource(R.drawable.trap);
        }

        if(pista.getPista().contains("generaste")){
            ImageView meme = (ImageView) findViewById(R.id.meme);
            meme.setImageResource(R.drawable.fin_sad);
        }

        if(pista.getPista().contains("boludo")){
            ImageView meme = (ImageView) findViewById(R.id.meme);
            meme.setImageResource(R.drawable.atendedor);
        }

    }

    public void finish(View view){
        this.finish();
        Intent i = new Intent(this, PantallaPrincipalActivity.class);

        startActivity(i);
    }

    public void accept(View view){
        this.finish();
    }

}
