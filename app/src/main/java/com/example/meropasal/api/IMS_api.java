package com.example.meropasal.api;

import com.example.meropasal.models.Categories;
import com.example.meropasal.models.Orders;
import com.example.meropasal.models.Products;
import com.example.meropasal.models.Users;
import com.example.meropasal.serverresponse.ImageResponse;
import com.example.meropasal.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IMS_api {
    //  for Users model //
    @POST("users/signup")
    Call<SignUpResponse> signup(@Body Users users);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @GET("users/me")
    Call<Users> getUserDetails(@Header("Authorization") String token);

    @GET("users/user")
    Call<List<Users>> getCustomerDetails(@Header("Authorization") String token);

    @Multipart
    @POST("upload/user")
    Call<ImageResponse> uploadImage(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @PUT("users/me")
    Call<Users> updateUser(@Header("Authorization") String token, @Body Users users);


    //For Categories model //
    @GET("categories/category")
    Call<List<Categories>> getCategories(@Header("Authorization") String token);


    //For Products model //
    @Multipart
    @POST("upload/product")
    Call<ImageResponse> uploadProductImage(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("products/product")
    Call<Products> addProducts(@Header("Authorization") String token,
                               @Field("category") String category,
                               @Field("product_name") String product_name,
                               @Field("description") String description,
                               @Field("price") String price,
                               @Field("product_image") String product_image);

    @GET("products/product")
    Call<List<Products>> getProducts(@Header("Authorization") String token);


    @DELETE("products/deleteProduct/{id}/")
    Call<Products> DeleteProduct(@Header("Authorization") String token, @Path("id") String id);

    @PUT("products/{id}/Update")
    Call<Products> updateProduct(@Header("Authorization") String token, @Path ("id") String id, @Body Products products);



    //for model orders//

    @FormUrlEncoded
    @POST("orders/order")
    Call<Orders> oderProduct(@Header("Authorization") String token,
                             @Field("product") String product);


}
