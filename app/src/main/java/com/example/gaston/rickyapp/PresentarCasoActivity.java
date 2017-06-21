package com.example.gaston.rickyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PresentarCasoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentar_caso);
    }

    public void salir(View view){
        finish();
    }

    public void aceptarCaso(View view){
        Intent i = new Intent(this, OrdenDeArrestoActivity.class);
        startActivity(i);
    }
}
