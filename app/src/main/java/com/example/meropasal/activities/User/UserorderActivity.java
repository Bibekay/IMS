package com.example.meropasal.activities.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meropasal.R;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserorderActivity extends AppCompatActivity {
    String id;
    TextView productName, descricption, productPrice;
    CircleImageView productImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userorder);

        productName = findViewById(R.id.et_productName);
        descricption = findViewById(R.id.et_description);
        productPrice = findViewById(R.id.et_price);
        productImage = findViewById(R.id.civ_productImage);


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
    }
}