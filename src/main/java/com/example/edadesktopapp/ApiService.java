package com.example.edadesktopapp;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    @POST("auth/authentication")
    Call<TokenResponse> loginUser(@Body UserEntityRequest student);

    @GET("staff/getOrders")
    Call<List<GetOrdersResponse>> getOrders(@Header("Authorization") String token);

    @PUT("staff/changeStatus")
    Call<String> changeStatus(@Header("Authorization") String token, @Body ChangeOrderStatusRequest changeOrderStatusRequest);

    @GET("staff/getFood")
    Call<List<GetFoodResponse>> getFood(@Header("Authorization") String token);

    @POST("admin/addFood")
    Call<String> addFood(@Header("Authorization") String token, @Body AddFoodRequest addFoodRequest);

    @GET("orders/getCategories")
    Call<List<GetCategoriesResponse>> getCategories(@Header("Authorization") String token);

    @POST("orders/addOrder")
    Call<String> addOrder(@Header("Authorization") String token, @Body AddOrderRequest addOrderRequest);

    @POST("orders/addCategory")
    Call<String> addCategory(@Header("Authorization") String token, @Body AddCategoryRequest addCategoryRequest);


    //@Header("Authorization") String token,
}