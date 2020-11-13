package com.example.appr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appr.Interfaces.ProductosAPI;
import com.example.appr.Models.Producto;
import com.example.appr.Models.ProductosRespuesta;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //RequestQueue queue;
    //String url = "http://192.168.1.116:8081/api/Productos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//queue = Volley.newRequestQueue(this);
ObtenerDatos();
    }
    private void ObtenerDatos(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.116:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ProductosAPI productosAPI = retrofit.create(ProductosAPI.class);
        Call<ProductosRespuesta> call  = productosAPI.ObtetenerLista();
        call.enqueue(new Callback<ProductosRespuesta>() {
            @Override
            public void onResponse(Call<ProductosRespuesta> call, Response<ProductosRespuesta> response) {
                try {
                    if(response.isSuccessful()) {
                        ProductosRespuesta productosRespuesta = response.body();
                    }
                    }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT );
                }
            }

            @Override
            public void onFailure(Call<ProductosRespuesta> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT );

            }
        });

    }
}