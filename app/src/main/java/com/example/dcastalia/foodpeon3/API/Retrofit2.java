package com.example.dcastalia.foodpeon3.API;

import com.example.dcastalia.foodpeon3.Model.Resturent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dcastalia on 2/12/18.
 */

public interface Retrofit2 {

//String Base_URL="https://api.myjson.com";

    @GET("bins/afmtp")
    public Call<List<Resturent>> getRes();
}

