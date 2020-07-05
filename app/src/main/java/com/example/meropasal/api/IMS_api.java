package com.example.meropasal.api;

import com.example.meropasal.models.Products;
import com.example.meropasal.models.Users;
import com.example.meropasal.serverresponse.ImageResponse;
import com.example.meropasal.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

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
    @POST ("upload/user")
    Call <ImageResponse> uploadImage(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @PUT("users/me")
    Call<Users> updateUser(@Header("Authorization")String token,@Body Users users);



    //For Products model //

    @GET("products/product")
    Call<List<Products>> getProducts(@Header("Authorization") String token);


}
