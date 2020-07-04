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
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserdisplayproductAdapter extends RecyclerView.Adapter <UserdisplayproductAdapter.UserdisplayproductViewHolder> {

    Context mContext;
    List<Products> productsList;
    private List<Products> filteruserList;

    public UserdisplayproductAdapter(Context mContext, List<Products> productsList) {

        this.mContext = mContext;
        this.productsList = productsList;
        filteruserList = new ArrayList<>(productsList);

    }

    @NonNull
    @Override
    public UserdisplayproductAdapter.UserdisplayproductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_userdisplayproduct_adapter,parent,false);
        return new UserdisplayproductViewHolder(v);

    }



    @Override
    public void onBindViewHolder(@NonNull UserdisplayproductAdapter.UserdisplayproductViewHolder holder, int i) {

        final Products products = productsList.get(i);
      // String imgPath = url.imagePath+Products.getProduct_image();
        holder.p_name.setText(products.getProduct_name());
     //   holder.p_image.setText(products.getProduct_image());
        holder.dis.setText(products.getDescription());
        holder.pr.setText(products.getPrice());


//   Picasso.get().load(imgPath).into(holder.p_image);

      //  boolean isExpandble = productsList.get(i).isExpandable();
       // holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }



    public class UserdisplayproductViewHolder extends RecyclerView.ViewHolder{


        TextView p_name, dis, pr;
        ImageView p_image;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public UserdisplayproductViewHolder(@NonNull View itemView) {
            super(itemView);


            p_name = itemView.findViewById(R.id.user_fullname);
            dis = itemView.findViewById(R.id.user_contact);
            pr = itemView.findViewById(R.id.user_email);
            p_image = itemView.findViewById(R.id.imguserimage);


            linearLayout = itemView.findViewById(R.id.linear_layout_adminuserinfo);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_adminuserdetails);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Products products = productsList.get(getAdapterPosition());
                    products.setExpandable(!products.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}