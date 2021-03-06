package com.example.meropasal.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meropasal.R;
import com.example.meropasal.models.Orders;
import com.example.meropasal.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.meropasal.url.url.imagePath;

public class UserorderdetailsAdapter extends RecyclerView.Adapter <UserorderdetailsAdapter.UserorderdetailsViewHolder> {
    Context mContext;
    List<Orders> ordersList;
    private List<Orders> filterorderLists ;


    public UserorderdetailsAdapter(Context mContext, List<Orders> ordersList) {
        this.mContext = mContext;
        this.ordersList = ordersList;
        filterorderLists = new ArrayList<>(ordersList);
    }

    @NonNull
    @Override
    public UserorderdetailsAdapter.UserorderdetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_userorderdetails_adapter, parent, false);
        return new UserorderdetailsViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull UserorderdetailsAdapter.UserorderdetailsViewHolder holder,  int i) {

        final Orders orders = ordersList.get(i);

        String imgPath = imagePath+orders.getProducts().getProduct_image();
        Picasso.get().load(imgPath).into(holder.Product_image);

        holder.title.setText(orders.getProducts().getProduct_name());
        holder.name.setText(orders.getProducts().getProduct_name());
        holder.descriptions.setText(orders.getProducts().getDescription());
        holder.cost.setText(orders.getProducts().getPrice());


        boolean isExpandble = ordersList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class UserorderdetailsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView Product_image;
        TextView name, title, descriptions, cost;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public UserorderdetailsViewHolder(@NonNull View itemView) {
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
                    Orders orders = ordersList.get(getAdapterPosition());
                    orders.setExpandable(!orders.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

    }
}