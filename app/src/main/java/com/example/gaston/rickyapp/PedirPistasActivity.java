package com.example.gaston.rickyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Pais;

import java.util.ArrayList;
import java.util.List;

public class PedirPistasActivity extends AppCompatActivity {

    private Pais actual;
    private TextView nombre;
    private Button botonLugar1;
    private Button botonLugar2;
    private Button botonLugar3;
    private Caso caso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_pistas);
        if(getIntent().getExtras() != null) {
            this.actualizarDatos();
        }
    }

    private void actualizarDatos() {
        this.caso = (Caso) getIntent().getExtras().get("caso");
        actual = caso.getPais();
        nombre = (TextView) findViewById(R.id.paisActual);
        nombre.setText(actual.getNombre());
        botonLugar1 = (Button) findViewById(R.id.buttonL1);
        botonLugar1.setText(this.parseCharSequence(caso.getLugar1().getNombre()));
        botonLugar2 = (Button) findViewById(R.id.buttonL2);
        botonLugar2.setText( this.parseCharSequence(caso.getLugar2().getNombre()) );
        botonLugar3 = (Button) findViewById(R.id.buttonL3);
        botonLugar3.setText(this.parseCharSequence(caso.getLugar3().getNombre()));
    }

    public CharSequence parseCharSequence(String cadena){

        CharSequence cs = cadena;

        return cs;
    }

    public void revisarPista1(View view) {
        Intent i = new Intent(this, MostrarPistaActivity.class);
        i.putExtra("lugar",this.caso.getLugar1());
        i.putExtra("caso", this.caso);
        startActivity(i);
    }

    public void revisarPista2(View view) {
        Intent i = new Intent(this, MostrarPistaActivity.class);
        i.putExtra("lugar",this.caso.getLugar2());
        i.putExtra("caso", this.caso);
        startActivity(i);
    }

    public void revisarPista3(View view) {
        Intent i = new Intent(this, MostrarPistaActivity.class);
        i.putExtra("lugar",this.caso.getLugar3());
        i.putExtra("caso", this.caso);
        startActivity(i);
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
