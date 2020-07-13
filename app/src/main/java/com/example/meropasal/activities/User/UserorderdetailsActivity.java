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
import com.example.meropasal.adapter.UserdisplayproductAdapter;
import com.example.meropasal.adapter.UserorderdetailsAdapter;
import com.example.meropasal.adapter.UserproductAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Orders;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserorderdetailsActivity extends AppCompatActivity {
    UserorderdetailsActivity activity;
    UserorderdetailsAdapter userorderdetailsAdapter;
    private RecyclerView recyclerView;
    ImageView back;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useorderdetails);

        activity = this;
        recyclerView = findViewById(R.id.rv_myOrders);
        back = findViewById(R.id.iv_backpressed);

        showOders();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserorderdetailsActivity.super.onBackPressed();
            }
        });


    }

    private void showOders() {

        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Orders>> orders = ims_api.getMYOrders(url.token);


        orders.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UserorderdetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Orders> ordersList = response.body();
                activity.userorderdetailsAdapter = new UserorderdetailsAdapter(UserorderdetailsActivity.this, ordersList);
                recyclerView.setAdapter(userorderdetailsAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(UserorderdetailsActivity.this, 1));

            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {

            }
        });


    }
}