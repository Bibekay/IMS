package com.example.meropasal.api;

import com.example.meropasal.models.Users;
import com.example.meropasal.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMS_api {
    //  for Users model //
    @POST("users/signup")
    Call<SignUpResponse> signup(@Body Users users);
}
