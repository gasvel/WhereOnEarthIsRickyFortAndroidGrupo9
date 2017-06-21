package com.example.gaston.rickyapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PantallaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.rickytheme);
        mp.start();
    }

    public void iniciarJuego(View view){
        Intent i = new Intent(this, PresentarCasoActivity.class);
        startActivity(i);
    }
}
