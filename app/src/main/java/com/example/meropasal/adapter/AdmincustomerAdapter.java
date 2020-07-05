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
import com.example.meropasal.models.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.meropasal.url.url.imagePath;

public class AdmincustomerAdapter extends RecyclerView.Adapter <AdmincustomerAdapter.AdmincustomerViewHolder> {


        Context mContext;
        List<Users> customersList;
private List<Users> filteruserList;
public AdmincustomerAdapter(Context mContext, List<Users> customersList) {

        this.mContext = mContext;
        this.customersList = customersList;
        filteruserList = new ArrayList<>(customersList);

        }

@NonNull
@Override
public AdmincustomerAdapter.AdmincustomerViewHolder onCreateViewHolder(@NonNull
                                                                           ViewGroup parent, int i) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admincustomer_adapter,parent,false);
            return new AdmincustomerViewHolder(v);

            }



@Override
public void onBindViewHolder(@NonNull AdmincustomerAdapter.AdmincustomerViewHolder holder, int i) {

final Users users = customersList.get(i);

       // String imgPath = imagePath+products.getProduct_image();
        //Picasso.get().load(imgPath).into(holder.productImage);

        holder.fullname.setText(users.getFullname());
        holder.contact.setText(users.getContact());
        holder.email.setText(users.getEmail());
        holder.username.setText(users.getUsername());


        }

@Override
public int getItemCount() {
        return customersList.size();
        }

public class AdmincustomerViewHolder extends RecyclerView.ViewHolder {

    ImageView customer_image;
    TextView fullname, contact, email, username;

    public AdmincustomerViewHolder(@NonNull View itemView) {
        super(itemView);


        fullname = itemView.findViewById(R.id.tv_customer_name);
        contact = itemView.findViewById(R.id.tv_contact);
        email = itemView.findViewById(R.id.tv_email);
        customer_image = itemView.findViewById(R.id.iv_customer_image);
        username = itemView.findViewById(R.id.tv_username);


    }
}
}