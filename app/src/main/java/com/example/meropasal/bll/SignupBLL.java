package com.example.meropasal.bll;


import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Users;
import com.example.meropasal.serverresponse.SignUpResponse;
import com.example.meropasal.url.url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignupBLL {

    boolean isSuccess = false;

    public boolean signup(String fullname, String username, String contact, String email, String password) {
        Users users = new Users (fullname, username, contact, email, password);

        IMS_api imsApi = url.getInstance().create(IMS_api.class);
        Call<SignUpResponse> UsersCall = imsApi.signup(users);

        try {
            Response<SignUpResponse> signupResponse = UsersCall.execute();
            if (signupResponse.isSuccessful() &&
                    signupResponse.body().getStatus().equals("Signup Successful")) {

                    url.token += signupResponse.body().getToken();
//                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}



