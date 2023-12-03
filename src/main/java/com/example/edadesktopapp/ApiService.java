package com.example.edadesktopapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @POST("auth/authentication")
    Call<TokenResponse> loginUser(@Body UserEntityRequest student);

    @GET("staff/getOrders")
    Call<List<GetOrdersResponse>> getOrders(@Header("Authorization") String token);

    //@Header("Authorization") String token,
}