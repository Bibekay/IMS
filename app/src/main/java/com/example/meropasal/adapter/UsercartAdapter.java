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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meropasal.R;
import com.example.meropasal.models.Carts;
import com.example.meropasal.models.Orders;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.meropasal.url.url.imagePath;

public class UsercartAdapter extends RecyclerView.Adapter <UsercartAdapter.UsercartViewHolder> {
        Context mContext;
        List<Carts> cartsList;
        private List<Carts> filtercartLists ;


            public UsercartAdapter(Context mContext, List<Carts> cartsList) {
                this.mContext = mContext;
                this.cartsList = cartsList;
                filtercartLists = new ArrayList<>(cartsList);
                }

        @NonNull
        @Override
        public UsercartAdapter.UsercartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_usercart_adapter, parent, false);
                return new UsercartViewHolder(v);

                }

        @Override
        public void onBindViewHolder(@NonNull UsercartAdapter.UsercartViewHolder holder,  int i) {

        final Carts carts = cartsList.get(i);

                String imgPath = imagePath+carts.getProduct().getProduct_image();
                Picasso.get().load(imgPath).into(holder.Product_image);

                holder.title.setText(carts.getProduct().getProduct_name());
                holder.name.setText(carts.getProduct().getProduct_name());
                holder.descriptions.setText(carts.getProduct().getDescription());
                holder.cost.setText(carts.getProduct().getPrice());


                boolean isExpandble = cartsList.get(i).isExpandable();
                holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

                }

        @Override
        public int getItemCount() {
                return cartsList.size();
                }

        public class UsercartViewHolder extends RecyclerView.ViewHolder {

            CircleImageView Product_image;
            TextView name, title, descriptions, cost;
            LinearLayout linearLayout;
            RelativeLayout expandableLayout;

            public UsercartViewHolder(@NonNull View itemView) {
                super(itemView);


                Product_image = itemView.findViewById(R.id.civ_productImage);
                name = itemView.findViewById(R.id.tv_product_name);
                descriptions = itemView.findViewById(R.id.tv_Description);
                cost = itemView.findViewById(R.id.tv_Price);
                title = itemView.findViewById(R.id.tv_productName);


                linearLayout = itemView.findViewById(R.id.linear_layout_adminuserinfo);
                expandableLayout = itemView.findViewById(R.id.expandable_layout_adminuserdetails);

                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Carts carts = cartsList.get(getAdapterPosition());
                        carts.setExpandable(!carts.isExpandable());
                        notifyItemChanged(getAdapterPosition());
                    }
                });
            }

        }
}