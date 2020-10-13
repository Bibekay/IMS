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
import com.example.meropasal.adapter.AdminvieworderAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Orders;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminviewordersActivity extends AppCompatActivity {

    AdminviewordersActivity activity;
    AdminvieworderAdapter adminvieworderAdapter;
    private RecyclerView recyclerView;
    ImageView back;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminvieworders);

        activity = this;
        recyclerView = findViewById(R.id.rv_viewOrders);
        back = findViewById(R.id.iv_backpressed);

        showusersOrders();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminviewordersActivity.super.onBackPressed();
            }
        });



    }

    private void showusersOrders() {
        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Orders>> orders = ims_api.getAllOrders(url.token);

        orders.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AdminviewordersActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Orders> ordersList = response.body();
                activity.adminvieworderAdapter = new AdminvieworderAdapter(AdminviewordersActivity.this, ordersList);
                recyclerView.setAdapter(adminvieworderAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(AdminviewordersActivity.this, 1));

            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                Toast.makeText(AdminviewordersActivity.this, "error message" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}