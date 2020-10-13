package com.example.meropasal.activities.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.adapter.UsercartAdapter;
import com.example.meropasal.adapter.UserorderdetailsAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Carts;
import com.example.meropasal.models.Orders;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsercartActivity extends AppCompatActivity {

    UsercartActivity activity;
    UsercartAdapter usercartAdapter;
    private RecyclerView recyclerView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercart);

        activity = this;
        recyclerView = findViewById(R.id.rv_viewCart);
        back = findViewById(R.id.iv_backpressed);

        showCarts();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsercartActivity.super.onBackPressed();
            }
        });



    }

    private void showCarts() {
        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Carts>> carts = ims_api.getMyCarts(url.token);


        carts.enqueue(new Callback<List<Carts>>() {
            @Override
            public void onResponse(Call<List<Carts>> call, Response<List<Carts>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UsercartActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Carts> cartsList = response.body();
                activity.usercartAdapter = new UsercartAdapter(UsercartActivity.this, cartsList);
                recyclerView.setAdapter(usercartAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(UsercartActivity.this, 1));

            }

            @Override
            public void onFailure(Call<List<Carts>> call, Throwable t) {
                Toast.makeText(UsercartActivity.this, "error message" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}