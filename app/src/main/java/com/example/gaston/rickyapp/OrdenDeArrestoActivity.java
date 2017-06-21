package com.example.gaston.rickyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OrdenDeArrestoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_de_arresto);
    }

    public void viajar(View view) {
        Intent i = new Intent(this, ViajarActivity.class);
        startActivity(i);
    }

    public void buscarPistas(View view){
        Intent i = new Intent(this, PedirPistasActivity.class);
        startActivity(i);
    }
}
