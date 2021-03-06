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
}

    @Override
    public int getItemCount() {
        return productsupdatesList.size();
    }

    public class AdminupdateproductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, description, price;
        Button btnUpdate;


        public AdminupdateproductViewHolder(@NonNull View itemView) {
            super(itemView);


            productImage = itemView.findViewById(R.id.iv_productimage);
            productName = itemView.findViewById(R.id.tv_product_name);
            description = itemView.findViewById(R.id.tv_description);
            price = itemView.findViewById(R.id.tv_price);
            btnUpdate = itemView.findViewById(R.id.btnAddProductUpdate);


        }
    }

}