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
import com.example.meropasal.models.Orders;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.meropasal.url.url.imagePath;

public class AdminvieworderAdapter extends RecyclerView.Adapter<AdminvieworderAdapter.AdminvieworderViewHolder> {

    Context mContext;
    List<Orders> ordersList;
    private List<Orders> filterorderLists;

    public AdminvieworderAdapter(Context mContext, List<Orders> ordersList) {
        this.mContext = mContext;
        this.ordersList = ordersList;
        filterorderLists = new ArrayList<>(ordersList);
    }

    @NonNull
    @Override
    public AdminvieworderAdapter.AdminvieworderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adminvieworder_adapter, parent, false);
        return new AdminvieworderViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdminvieworderAdapter.AdminvieworderViewHolder holder, int i) {

        final Orders orders = ordersList.get(i);

        String imgPath = imagePath + orders.getUser().getImage();
        Picasso.get().load(imgPath).into(holder.user_image);

        String imageHotel = imagePath + orders.getProducts().getProduct_image();
        Picasso.get().load(imageHotel).into(holder.productImage);


        holder.username.setText(orders.getUser().getUsername());
        holder.productName.setText(orders.getProducts().getProduct_name());
        holder.descriptions.setText(orders.getProducts().getDescription());
        holder.cost.setText(orders.getProducts().getPrice());


        boolean isExpandble = ordersList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }


    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class AdminvieworderViewHolder extends RecyclerView.ViewHolder {

        CircleImageView user_image;
        ImageView productImage;
        TextView username, productName, descriptions, cost;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public AdminvieworderViewHolder(@NonNull View itemView) {
            super(itemView);


            user_image = itemView.findViewById(R.id.civ_userImage);
            productImage = itemView.findViewById(R.id.iv_productImage);
            username = itemView.findViewById(R.id.tv_username);
            productName = itemView.findViewById(R.id.tv_poduct_name1);
            descriptions = itemView.findViewById(R.id.tv_Description);
            cost = itemView.findViewById(R.id.tv_Price);


            linearLayout = itemView.findViewById(R.id.linear_layout_adminuserinfo);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_adminuserdetails);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Orders orders = ordersList.get(getAdapterPosition());
                    orders.setExpandable(!orders.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}