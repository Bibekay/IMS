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
    private List<Orders> filteruserList;

    public UserorderdetailsAdapter(Context mContext, List<Orders> ordersList) {
        this.mContext = mContext;
        this.ordersList = ordersList;
        this.filteruserList = new ArrayList<>(ordersList);
    }

    @NonNull
    @Override
    public UserorderdetailsAdapter.UserorderdetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_userorderdetails_adapter, parent, false);
        return new UserorderdetailsAdapter.UserorderdetailsViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull UserorderdetailsAdapter.UserorderdetailsViewHolder holder, int i) {

        final Orders orders = ordersList.get(i);

        String imgPath = imagePath + orders.product.getProduct_image();
        Picasso.get().load(imgPath).into(holder.productImage);

        holder.p_name.setText(orders.product.getProduct_name());
        holder.productName.setText(orders.product.getProduct_name());
        holder.description.setText(orders.product.getDescription());
        holder.price.setText(orders.product.getPrice());


        boolean isExpandble = ordersList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class UserorderdetailsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        TextView productName, p_name, description, price;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public UserorderdetailsViewHolder(@NonNull View itemView) {
            super(itemView);


            productImage = itemView.findViewById(R.id.iv_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            description = itemView.findViewById(R.id.tv_description);
            price = itemView.findViewById(R.id.tv_price);
            p_name = itemView.findViewById(R.id.tv_productname);


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