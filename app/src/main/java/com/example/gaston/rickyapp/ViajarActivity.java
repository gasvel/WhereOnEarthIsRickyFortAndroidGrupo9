package com.example.gaston.rickyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaston.rickyapp.model.Caso;
import com.example.gaston.rickyapp.model.Pais;
import com.example.gaston.rickyapp.model.PaisNombre;
import com.example.gaston.rickyapp.model.Villano;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ViajarActivity extends Activity {
    private Caso actual;
    private Pais paisActual;
    private Integer paisId;
    private CarmenSanDiegoService service;
    private Villano villanoConOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajar);

        service = this.createService();

        Caso caso = (Caso) getIntent().getExtras().get("caso");
        this.actualizarDatos(caso);


    }

    private CarmenSanDiegoService createService(){
        String SERVER_IP = "192.168.1.40"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.108";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService juegoService = restAdapter.create(CarmenSanDiegoService.class);
        return juegoService;
    }
    public void viajar(View v){
        if(paisId!= null) {
            service.viajarAPais(paisId, new Callback<Caso>() {
                @Override
                public void success(Caso caso, Response response) {
                    actualizarDatos(caso);
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }
        else{
            Toast.makeText(ViajarActivity.this,"No hay ningun pais seleccionado",Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarDatos(Caso caso){

        actual = caso;
        paisActual = caso.getPais();
        paisId = null;
        villanoConOrden = (Villano) getIntent().getExtras().get("villanoConOrden");
        if(villanoConOrden.getNombre() != null){
            TextView villano = (TextView) findViewById(R.id.nombreVillanoViajar);
            villano.setText(villanoConOrden.getNombre());
        }
        TextView nombre = (TextView) findViewById(R.id.nombreLugar);
        nombre.setText(paisActual.getNombre());
        ListView lista = (ListView) findViewById(R.id.listaPaises);
        ArrayAdapter adapter = new PaisListAdapter(ViajarActivity.this,R.id.listaPaises, paisActual.getConexiones());
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PaisNombre item = (PaisNombre) parent.getItemAtPosition(position);

                paisId = item.getId();
            }
        });
        TextView listaRecorrido = (TextView) findViewById(R.id.recorrido);
        String paisesRecorridos = "";
        for(PaisNombre pais : actual.getPaisesVisitados()){
            paisesRecorridos= paisesRecorridos + pais.getNombre() + " > ";
        }
        listaRecorrido.setText(paisesRecorridos);
    }

    public void ordenDeArresto(View view) {
        Intent i = new Intent(this, OrdenDeArrestoActivity.class);
        i.putExtra("caso",actual);
        i.putExtra("villanoConOrden",villanoConOrden);
        this.finish();
        startActivity(i);
    }

    public void buscarPistas(View view){
        Intent i = new Intent(this, PedirPistasActivity.class);
        i.putExtra("caso",actual);
        i.putExtra("villanoConOrden",villanoConOrden);
        this.finish();
        startActivity(i);
    }
}
