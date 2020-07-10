package com.example.meropasal.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meropasal.R;
import com.example.meropasal.models.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.meropasal.url.url.imagePath;

public class AdminupdateproductAdapter extends  RecyclerView.Adapter <AdminupdateproductAdapter.AdminupdateproductViewHolder>{


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
    public AdminupdateproductAdapter.AdminupdateproductViewHolder onCreateViewHolder(@NonNull
                                                                               ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adminupdateproduct_adapter,parent,false);
        return new AdminupdateproductAdapter.AdminupdateproductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdminupdateproductAdapter.AdminupdateproductViewHolder holder, int i) {

        final Products products = productsupdatesList.get(i);

        String imgPath = imagePath+products.getProduct_image();
        Picasso.get().load(imgPath).into(holder.productImage);

        holder.productName.setText(products.getProduct_name());
        holder.description.setText(products.getDescription());
        holder.price.setText(products.getPrice());


    }
    @Override
    public int getItemCount() {
        return productsupdatesList.size();
    }

    public class AdminupdateproductViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        TextView productName, description, price;

        public AdminupdateproductViewHolder(@NonNull View itemView) {
            super(itemView);


            productImage = itemView.findViewById(R.id.civ_productImage);
            productName = itemView.findViewById(R.id.et_productName);
            description = itemView.findViewById(R.id.et_description);
            price = itemView.findViewById(R.id.et_price);


        }
    }

}