package com.example.meropasal.activities.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.adapter.UserproductAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Orders;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserorderActivity extends AppCompatActivity {
    String id;
    TextView productName, descricption, productPrice;
    ImageView productImage, imageBack;
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userorder);

        productName = findViewById(R.id.et_productName);
        descricption = findViewById(R.id.et_description);
        productPrice = findViewById(R.id.et_price);
        productImage = findViewById(R.id.civ_productImage);
        order = findViewById(R.id.btnConformOrder);
        imageBack = findViewById(R.id.iv_backpressed);


        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               UserorderActivity.super.onBackPressed();
            }
        });



        //retriving single data through recycle view from productAdapter  //
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            productName.setText(bundle.getString("product_name"));
            descricption.setText(bundle.getString("description"));
            productPrice.setText(bundle.getString("price"));

            String imagepath = url.BASE_URL + bundle.getString("image");
            Picasso.get().load(imagepath).into(productImage);
        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oderProduct();
            }

            private void oderProduct() {
                String product = id;
                IMS_api ims_api = url.getInstance().create(IMS_api.class);
                Call<Orders> orderCall = ims_api.oderProduct(url.token, product);

                orderCall.enqueue(new Callback<Orders>() {
                    @Override
                    public void onResponse(Call<Orders> call, Response<Orders> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(UserorderActivity.this, "Order Successfull", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(UserorderActivity.this, "Order Successfull", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Orders> call, Throwable t) {
                        Toast.makeText(UserorderActivity.this,  "Order Successfull", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}