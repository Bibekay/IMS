package com.example.meropasal.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.activities.User.UserproductActivity;
import com.example.meropasal.adapter.AdmincategoryAdapter;
import com.example.meropasal.adapter.AdminupdateproductAdapter;
import com.example.meropasal.adapter.UserproductAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Categories;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminupdateproductActivity extends AppCompatActivity {


    AdminupdateproductActivity activity;
    AdminupdateproductAdapter adminupdateproductAdapter;
    ImageView back;

    private RecyclerView recyclerView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupdateproduct);

        activity = this;
        recyclerView = findViewById(R.id.rv_updateProduct);
        back = findViewById(R.id.iv_backpressed);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminupdateproductActivity.super.onBackPressed();
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
                    Toast.makeText(AdminupdateproductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Products> productsupdateList = response.body();
                activity.adminupdateproductAdapter = new AdminupdateproductAdapter(AdminupdateproductActivity.this, productsupdateList);
                recyclerView.setAdapter(adminupdateproductAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(AdminupdateproductActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }
}