package com.example.myapplication.Network;

import com.example.myapplication.Model.ApiModel;
import com.example.myapplication.Model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfce {

    @GET("api.php")
    Call<Example> getPosts();

}
