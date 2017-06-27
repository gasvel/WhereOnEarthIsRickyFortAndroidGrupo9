package com.example.gaston.rickyapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PantallaPrincipalActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

       mp= MediaPlayer.create(this, R.raw.rickytheme);
       mp.setLooping(true);
       mp.start();
    }

    public void iniciarJuego(View view){

        Intent i = new Intent(this, PresentarCasoActivity.class);
        startActivity(i);
    }

    public void desactivarSonido(View view){
        if(mp!=null) {
            if (mp.isPlaying()) {
                ImageButton btn = (ImageButton)findViewById(R.id.imageButton);
                btn.setSelected(true);
                mp.pause();
       } else {
                ImageButton btn = (ImageButton)findViewById(R.id.imageButton);
                btn.setSelected(false);
                mp.start();
            }
        }

    }
}
