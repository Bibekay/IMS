package com.example.meropasal.activities.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Products;
import com.example.meropasal.serverresponse.ImageResponse;
import com.example.meropasal.strictmode.StrictModeClass;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminupdateproductDeatilsActivity extends AppCompatActivity {
    String id;
    EditText productName, descricption, productPrice;
    CircleImageView productImage;
    Button update, delete;
    String imagePath, imageName;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupdateproduct_deatils);

        productName = findViewById(R.id.et_productName);
        descricption = findViewById(R.id.et_description);
        productPrice = findViewById(R.id.et_price);
        productImage = findViewById(R.id.civ_productImage);
        update = findViewById(R.id.btnSave);
        delete = findViewById(R.id.btnAddDeleteProduct);


        //retriving single data through recycle view from admincategories adapter //
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            productName.setText(bundle.getString("product_name"));
            descricption.setText(bundle.getString("description"));
            productPrice.setText(bundle.getString("price"));

            String imagepath = url.BASE_URL + bundle.getString("image");
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
                        Intent intent = new Intent(AdminupdateproductDeatilsActivity.this, AdminupdateproductActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(AdminupdateproductDeatilsActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                updateProduct();
            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
    }
        private void saveImageOnly() {

            if (imagePath != null) {
                File file = new File(imagePath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

                IMS_api ims_api = url.getInstance().create(IMS_api.class);
                Call<ImageResponse> imageResponseCall = ims_api.uploadProductImage(url.token, body);


                StrictModeClass.StrictMode();

                try {
                    Response<ImageResponse> imageResponseResponse = imageResponseCall.execute();
                    imageName = imageResponseResponse.body().getFilename();
                } catch (IOException e) {
                    Toast.makeText(AdminupdateproductDeatilsActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(AdminupdateproductDeatilsActivity.this, "Please choose file to update picture", Toast.LENGTH_SHORT).show();
            }


        }


        private void BrowseImage() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                if (resultCode == RESULT_OK && data!=null) {

                    Uri uri = data.getData();
                    productImage.setImageURI(uri);
                    imagePath = getRealPathFromUri(uri);

                } else {
                    Toast.makeText(AdminupdateproductDeatilsActivity.this, "Please select an image ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AdminupdateproductDeatilsActivity.this, "image couldnot upload", Toast.LENGTH_SHORT).show();
            }
        }

        private String getRealPathFromUri(Uri uri) {
            String[] proj = {MediaStore.Images.Media.DATA};
            CursorLoader loader = new CursorLoader(AdminupdateproductDeatilsActivity.this.getApplicationContext(), uri, proj, null, null, null);

            Cursor cursor = loader.loadInBackground();
            int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String res = cursor.getString(colIndex);
            cursor.close();
            return res;
        }

        private void updateProduct(){
            String product_name = productName.getText().toString();
            String description = descricption.getText().toString();
            String price = productPrice.getText().toString();

            Products products = new Products(product_name, description, price, imageName);

            IMS_api ims_api = url.getInstance().create(IMS_api.class);
            Call<Products> productsCall = ims_api.updateProduct(url.token,id, products);

            productsCall.enqueue(new Callback<Products>() {
                @Override
                public void onResponse(Call<Products> call, Response<Products> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AdminupdateproductDeatilsActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(AdminupdateproductDeatilsActivity.this, AdminupdateproductActivity.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Products> call, Throwable t) {
                    Toast.makeText(AdminupdateproductDeatilsActivity.this, "Code" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        }

    }