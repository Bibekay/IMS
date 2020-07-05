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
import com.example.meropasal.adapter.AdmincustomerAdapter;
import com.example.meropasal.adapter.UserproductAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Products;
import com.example.meropasal.models.Users;
import com.example.meropasal.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdmincustomerActivity extends AppCompatActivity {

    ImageView back;
    AdmincustomerActivity activity;
    AdmincustomerAdapter admincustomerAdapter;
    private RecyclerView recyclerView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincustomer);

        activity = this;
        recyclerView = findViewById(R.id.rv_customers);
        back = findViewById(R.id.iv_backpressed);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdmincustomerActivity.super.onBackPressed();
            }
        });

        showCustomers();

    }

    private void showCustomers() {
        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Users>> usersCall = ims_api.getCustomerDetails(url.token);


        usersCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(AdmincustomerActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Users> customersList = response.body();
                activity.admincustomerAdapter = new AdmincustomerAdapter(AdmincustomerActivity.this, customersList);
                recyclerView.setAdapter(admincustomerAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(AdmincustomerActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });

    }
}