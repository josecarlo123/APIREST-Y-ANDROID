package com.example.appr.Interfaces;

import com.example.appr.Models.ProductosRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductosAPI {

    @GET("api/Productos")
    Call<ProductosRespuesta> ObtetenerLista();


}
