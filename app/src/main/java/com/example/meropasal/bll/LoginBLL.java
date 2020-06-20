package com.example.meropasal.bll;

import com.example.meropasal.api.IMS_api;
import com.example.meropasal.serverresponse.SignUpResponse;
import com.example.meropasal.url.url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        IMS_api imsApi = url.getInstance().create(IMS_api.class);
        Call<SignUpResponse> UsersCall = imsApi.checkUser(username, password);

        try {
            Response<SignUpResponse> loginResponse = UsersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login Successful")) {

                url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

}
