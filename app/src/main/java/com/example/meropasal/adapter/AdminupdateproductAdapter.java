package com.example.meropasal.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meropasal.R;
import com.example.meropasal.activities.admin.AdminupdateproductDeatilsActivity;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meropasal.url.url.imagePath;

public class AdminupdateproductAdapter extends RecyclerView.Adapter<AdminupdateproductAdapter.AdminupdateproductViewHolder> {

    Context mContext;
    List<Products> productsupdatesList;
    private List<Products> filteruserList;

    public AdminupdateproductAdapter(Context mContext, List<Products> productsupdatesList) {
        this.mContext = mContext;
        this.productsupdatesList = productsupdatesList;
        this.filteruserList = filteruserList;
    }


    @NonNull
    @Override
    public AdminupdateproductViewHolder onCreateViewHolder(@NonNull
                                                                                             ViewGroup parent, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_adminupdateproduct_adapter, parent, false);
        return new AdminupdateproductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdminupdateproductAdapter.AdminupdateproductViewHolder holder, final int i) {

        final Products products = productsupdatesList.get(i);

        final String imgPath = imagePath + products.getProduct_image();
        Picasso.get().load(imgPath).into(holder.productImage);

        holder.productName.setText(products.getProduct_name());
        holder.description.setText(products.getDescription());
        holder.price.setText(products.getPrice());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mContext, AdminupdateproductDeatilsActivity.class);
                notify.putExtra("id", products.get_id());
                notify.putExtra("product_name",products.getProduct_name());
                notify.putExtra("description",products.getDescription());
                notify.putExtra("price",products.getPrice());
                notify.putExtra("image", products.getProduct_image());

                mContext.startActivity(notify);

            }
        });
//
//            private void updateProducts() {
//                Products products = new Products(
//                        holder.productName.getText().toString(),
//                        holder.description.getText().toString(),
//                        holder.price.getText().toString()
//                );
//
//                IMS_api ims_api = url.getInstance().create(IMS_api.class);
//                Call<Products> prouductCall = ims_api.updateProduct(url.token, id, products);
//
//                prouductCall.enqueue(new Callback<Products>() {
//                    @Override
//                    public void onResponse(Call<Products> call, Response<Products> response) {
//                        if (!response.isSuccessful()) {
//                            Toast.makeText(mContext, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        Toast.makeText(mContext, "Updated !!!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Products> call, Throwable t) {
//                        Toast.makeText(mContext, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }
//        });
//        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // productsupdatesList.remove(i);
//               // delete();
//
//            }
//            private void delete() {
//
//
//                IMS_api ims_api = url.getInstance().create(IMS_api.class);
//                Call<Products> voidCall = ims_api.DeleteProduct(url.token, id);
//
//                voidCall.enqueue(new Callback<Products>() {
//                    @Override
//                    public void onResponse(Call<Products> call, Response<Products> response) {
//
//                        if (!response.isSuccessful()) {
//                            Toast.makeText(mContext, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        Toast.makeText(mContext, "Deleted !!!", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Products> call, Throwable t) {
//                        Toast.makeText(mContext, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//            }

//
//
}

    @Override
    public int getItemCount() {
        return productsupdatesList.size();
    }

    public class AdminupdateproductViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        TextView productName, description, price;
        Button deleteProduct, btnUpdate;


        public AdminupdateproductViewHolder(@NonNull View itemView) {
            super(itemView);


            productImage = itemView.findViewById(R.id.civ_productImage);
            productName = itemView.findViewById(R.id.et_productName);
            description = itemView.findViewById(R.id.et_description);
            price = itemView.findViewById(R.id.et_price);
            deleteProduct = itemView.findViewById(R.id.btnAddDeleteProduct);
            btnUpdate = itemView.findViewById(R.id.btnAddUpdateProduct);


        }
    }

}