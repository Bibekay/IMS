package com.example.meropasal.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.adapter.AdmincategoryAdapter;
import com.example.meropasal.adapter.AdmincustomerAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Categories;
import com.example.meropasal.models.Users;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    ImageView back;
    ProductActivity activity;
    AdmincategoryAdapter admincategoryAdapter;

    private RecyclerView recyclerView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        activity = this;
        recyclerView = findViewById(R.id.rv_category);
        back = findViewById(R.id.iv_backpressed);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AdminhomeActivity.class);
                startActivity(intent);
            }
        });


        showCategories();
    }

    private void showCategories() {
        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Categories>> categoriesCall = ims_api.getCategories(url.token);


        categoriesCall.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(ProductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Categories> categoriesList = response.body();
                activity.admincategoryAdapter = new AdmincategoryAdapter(ProductActivity.this, categoriesList);
                recyclerView.setAdapter(admincategoryAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(ProductActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

            }
        });

    }


}