package com.example.meropasal.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meropasal.R;
import com.example.meropasal.activities.User.UserorderActivity;
import com.example.meropasal.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.meropasal.url.url.imagePath;

public class UserproductAdapter extends RecyclerView.Adapter <UserproductAdapter.UserproductViewHolder> {


    Context mContext;
    List<Products> productsList;
    private List<Products> filteruserList;
    public UserproductAdapter(Context mContext, List<Products> productsList) {

        this.mContext = mContext;
        this.productsList = productsList;
        filteruserList = new ArrayList<>(productsList);

    }

        @NonNull
        @Override
        public UserproductAdapter.UserproductViewHolder onCreateViewHolder(@NonNull
    ViewGroup parent, int i) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_userproduct_adapter,parent,false);
            return new UserproductViewHolder(v);

        }



        @Override
        public void onBindViewHolder(@NonNull UserproductAdapter.UserproductViewHolder holder, int i) {

            final Products products = productsList.get(i);

            String imgPath = imagePath+products.getProduct_image();
            Picasso.get().load(imgPath).into(holder.productImage);

            holder.productName.setText(products.getProduct_name());
            holder.description.setText(products.getDescription());
            holder.price.setText(products.getPrice());

            holder.order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openOderActivity = new Intent(mContext, UserorderActivity.class);
                    openOderActivity.putExtra("id", products.get_id());
                    openOderActivity.putExtra("product_name",products.getProduct_name());
                    openOderActivity.putExtra("description",products.getDescription());
                    openOderActivity.putExtra("price",products.getPrice());
                    openOderActivity.putExtra("image", products.getProduct_image());

                    mContext.startActivity(openOderActivity);


                }
            });


        }

        @Override
        public int getItemCount() {
            return productsList.size();
        }

        public class UserproductViewHolder extends RecyclerView.ViewHolder {

            ImageView productImage;
            TextView productName, description, price;
            Button order;

            public UserproductViewHolder(@NonNull View itemView) {
                super(itemView);


                productImage = itemView.findViewById(R.id.iv_productimage);
                productName = itemView.findViewById(R.id.tv_product_name);
                description = itemView.findViewById(R.id.tv_description);
                price = itemView.findViewById(R.id.tv_price);
                order = itemView.findViewById(R.id.btnOrder);


            }
        }
}