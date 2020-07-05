package com.example.meropasal.activities.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.activities.admin.ProductActivity;
import com.example.meropasal.adapter.UserdisplayproductAdapter;
import com.example.meropasal.adapter.UserproductAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserproductActivity extends AppCompatActivity {

    UserproductActivity activity;
    UserproductAdapter userproductadapter;
    private RecyclerView recyclerView;
    ImageView back;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userproduct);

        activity = this;

        recyclerView = findViewById(R.id.rv_recentlyAddedProducts);
        back = findViewById(R.id.iv_backpressed);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserproductActivity.super.onBackPressed();
            }
        });


        showProducts();
    }

    private void showProducts() {

        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Products>> productsCall = ims_api.getProducts(url.token);


        productsCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(UserproductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Products> productsList = response.body();
                activity.userproductadapter = new UserproductAdapter(UserproductActivity.this, productsList);
                recyclerView.setAdapter(userproductadapter);
                recyclerView.setLayoutManager(new GridLayoutManager(UserproductActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }
}