package com.example.gaston.rickyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Pais;

public class PedirPistasActivity extends AppCompatActivity {

    private Pais actual;
    private TextView nombre;
    private Caso caso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_pistas);
        if(getIntent().getExtras() != null) {
            this.caso = (Caso) getIntent().getExtras().get("caso");
            actual = caso.getPais();
            nombre = (TextView) findViewById(R.id.paisActual);
            nombre.setText(actual.getNombre());
        }
    }

    public void ordenDeArresto(View view) {
        Intent i = new Intent(this, OrdenDeArrestoActivity.class);
        i.putExtra("caso",this.caso);
        startActivity(i);
    }

    public void viajar(View view){
        Intent i = new Intent(this, ViajarActivity.class);
        i.putExtra("caso",this.caso);
        startActivity(i);
    }
}
