package com.example.meropasal.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminupdateproductDeatilsActivity extends AppCompatActivity {
    String id;
    EditText productName, descricption, price;
    CircleImageView productImage;
    Button update, delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupdateproduct_deatils);

        productName = findViewById(R.id.et_productName);
        descricption = findViewById(R.id.et_description);
        price = findViewById(R.id.et_price);
        productImage = findViewById(R.id.civ_productImage);
        update = findViewById(R.id.btnSave);
        delete = findViewById(R.id.btnAddDeleteProduct);


        //retriving single data through recycle view from admincategories adapter //
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            productName.setText(bundle.getString("product_name"));
            descricption.setText(bundle.getString("description"));
            price.setText(bundle.getString("price"));

            String imagepath = url.BASE_URL +bundle.getString("image");
            Picasso.get().load(imagepath).into(productImage);
        }


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();

            }

            private void deleteProduct() {



                IMS_api ims_api = url.getInstance().create(IMS_api.class);
                Call<Products> voidCall = ims_api.DeleteProduct(url.token, id);

                voidCall.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {

                        if (!response.isSuccessful()) {
                            Toast.makeText(AdminupdateproductDeatilsActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(AdminupdateproductDeatilsActivity.this, "Deleted !!!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(AdminupdateproductDeatilsActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//

            }
        });


    }
}