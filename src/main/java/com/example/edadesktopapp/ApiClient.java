package com.example.edadesktopapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://95.165.91.211:8081/api/v1/";
    private static Retrofit retrofit = null;

    private static String token = "Bearer ";
    public static Retrofit getClient(){

        //int i = client.interceptors().size();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.client(client)
                    .build();
        return retrofit;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ApiClient.token += token;
    }
}