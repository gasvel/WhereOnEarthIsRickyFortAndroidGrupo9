package com.example.gaston.rickyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.PaisNombre;
import com.example.gaston.rickyapp.model.Villano;

import javax.xml.transform.Result;


public class OrdenDeArrestoActivity extends Activity {

    private Caso actual;
    private Integer villanoId;
    private Villano villanoConOrden;
    private CarmenSanDiegoService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_de_arresto);
        service = this.createService();
        actual = (Caso) getIntent().getExtras().get("caso");
        service.getVillanos(new Callback<List<Villano>>(){
            @Override
            public void success(List<Villano> villanos, Response response) {
                actualizarDatos(actual,villanos);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    public void viajar(View view) {
        Intent i = new Intent(this, ViajarActivity.class);
        i.putExtra("caso",actual);
        i.putExtra("villanoConOrden",villanoConOrden);

        this.finish();
        startActivity(i);
    }

    private CarmenSanDiegoService createService(){
        String SERVER_IP = "192.168.1.40"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.108";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }


    public void actualizarDatos(Caso caso,List<Villano> villanos){
        actual = caso;
        villanoConOrden = (Villano) getIntent().getExtras().get("villanoConOrden");
        if(villanoConOrden.getNombre() != null){
            TextView villano = (TextView) findViewById(R.id.nombreVillanoConOrden);
            villano.setText(villanoConOrden.getNombre());
        }
        ListView lista = (ListView) findViewById(R.id.listVillanos);
        ArrayAdapter adapter = new VillanoListAdapter(OrdenDeArrestoActivity.this,R.id.listaPaises,villanos );
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Villano item = (Villano) parent.getItemAtPosition(position);
                Toast.makeText(OrdenDeArrestoActivity.this,item.getNombre(),Toast.LENGTH_SHORT).show();
                villanoConOrden = item;


            }
        });

    }

    public void buscarPistas(View view){
        Intent i = new Intent(this, PedirPistasActivity.class);
        i.putExtra("caso",actual);
        i.putExtra("villanoConOrden",villanoConOrden);
        this.finish();
        startActivity(i);
    }

    public void emitirOrden(View view){
        service.emitirOrden(villanoConOrden.getId(), actual.getId(), new Callback<Result>() {
            @Override
            public void success(Result o, Response response) {
                TextView nombre = (TextView) findViewById(R.id.nombreVillanoConOrden);
                nombre.setText(villanoConOrden.getNombre());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

}
