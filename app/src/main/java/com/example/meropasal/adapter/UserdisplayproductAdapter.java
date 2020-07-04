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

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.meropasal.url.url.BASE_URL;
import static com.example.meropasal.url.url.imagePath;
import static com.example.meropasal.url.url.token;

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

        String imgPath = imagePath+products.getProduct_image();
        Picasso.get().load(imgPath).into(holder.productImage);

        holder.p_name.setText(products.getProduct_name());
        holder.productName.setText(products.getProduct_name());
        holder.description.setText(products.getDescription());
        holder.price.setText(products.getPrice());


        boolean isExpandble = productsList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }



    public class UserdisplayproductViewHolder extends RecyclerView.ViewHolder{

        CircleImageView productImage;
        TextView productName, p_name, description, price;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public UserdisplayproductViewHolder(@NonNull View itemView) {
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
                    Products products = productsList.get(getAdapterPosition());
                    products.setExpandable(!products.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}