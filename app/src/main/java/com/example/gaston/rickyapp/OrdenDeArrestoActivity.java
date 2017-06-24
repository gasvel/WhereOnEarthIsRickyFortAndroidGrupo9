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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Villano;


public class OrdenDeArrestoActivity extends Activity {

    private Caso actual;
    private ListView listVillanos;
    private List<String> listaVillanosNombres = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_de_arresto);
        CarmenSanDiegoService service = this.createService();
        actual = (Caso) getIntent().getExtras().get("caso");
        service.getVillanos(new Callback<List<Villano>>(){
            @Override
            public void success(List<Villano> villanos, Response response) {
                asignarVillanos(villanos);
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
        this.finish();
        startActivity(i);
    }

    private CarmenSanDiegoService createService(){
        String SERVER_IP = "10.0.2.2"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.100";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP_GENY +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }

    public void buscarPistas(View view){
        Intent i = new Intent(this, PedirPistasActivity.class);
        i.putExtra("caso",actual);
        this.finish();
        startActivity(i);
    }

    private void asignarVillanos(List<Villano> villanos){


        List<String> villanosNombres = new ArrayList<String>();
        for(Villano v: villanos){
            villanosNombres.add(v.getNombre());
        }
        listVillanos =(ListView)findViewById(R.id.listVillanos);
        this.listVillanos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, villanosNombres));
    }
}
