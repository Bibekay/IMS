package com.example.meropasal.adapter;

import androidx.annotation.NonNull;;
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
import com.example.meropasal.activities.admin.AdminaddproductActivity;
import com.example.meropasal.models.Categories;
;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.meropasal.url.url.imagePath;

public class AdmincategoryAdapter extends RecyclerView.Adapter<AdmincategoryAdapter.AdmincategoryViewHolder> {


    Context mContext;
    List<Categories> categoriesList;
    private List<Categories> filteruserList;

    public AdmincategoryAdapter(Context mContext, List<Categories> categoriesList) {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
        filteruserList = new ArrayList<>(categoriesList);

    }

    @NonNull
    @Override
    public AdmincategoryAdapter.AdmincategoryViewHolder onCreateViewHolder(@NonNull
                                                                                   ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admincategory_adapter, parent, false);
        return new AdmincategoryViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull AdmincategoryAdapter.AdmincategoryViewHolder holder, int i) {

        final Categories categories = categoriesList.get(i);

        holder.categoryName.setText(categories.getCategory_name());

      final  String imgPath = imagePath + categories.getCategory_image();
        Picasso.get().load(imgPath).into(holder.categoryImage);

        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewProductDetails = new Intent(mContext, AdminaddproductActivity.class);

                viewProductDetails.putExtra("id",categories.get_id());

                viewProductDetails.putExtra("category_image", categories.getCategory_image());
                viewProductDetails.putExtra("category_name", categories.getCategory_name());
                mContext.startActivity(viewProductDetails);

            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class AdmincategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;
        Button add_product;

        public AdmincategoryViewHolder(@NonNull View itemView) {
            super(itemView);


            categoryName = itemView.findViewById(R.id.tv_categoryname);
            categoryImage = itemView.findViewById(R.id.cv_category);
            add_product = itemView.findViewById(R.id.btn_Product);


        }
    }
}