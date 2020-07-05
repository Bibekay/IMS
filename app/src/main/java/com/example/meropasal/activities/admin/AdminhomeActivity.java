package com.example.meropasal.activities.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.meropasal.R;

import com.example.meropasal.activities.User.LoginActivity;
import com.example.meropasal.activities.User.UserdashActivity;
import com.example.meropasal.activities.User.UserprofileActivity;
import com.example.meropasal.adapter.UserdisplayproductAdapter;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Products;
import com.example.meropasal.url.url;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminhomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    static final float END_SCALE = 0.7f;
    ImageView menuIcon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    AdminhomeActivity activity;
    UserdisplayproductAdapter userdisplayadapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);



        activity = this;

        //Menu Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        recyclerView = findViewById(R.id.rv_recentlyAddedProducts);

        navigationDrawer();
        showProducts();



    }

    private void showProducts() {

        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<List<Products>> usersCall = ims_api.getProducts(url.token);


        usersCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(AdminhomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Products> usersList = response.body();
                activity.userdisplayadapter = new UserdisplayproductAdapter(AdminhomeActivity.this, usersList);
                recyclerView.setAdapter(userdisplayadapter);
                recyclerView.setLayoutManager(new GridLayoutManager(AdminhomeActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }


    //Navigation drawer function

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminhomeActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        SharedPreferences sharedPreferences = AdminhomeActivity.this.getSharedPreferences("IMS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("token");
                        editor.remove("isadmin");
                        editor.remove("status");
                        editor.remove("username");
                        editor.remove("password");
                        editor.commit();
                        url.token = "Bearer ";
                        url.status = "Status";
                        Intent i = new Intent(AdminhomeActivity.this, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                break;

            case R.id.product:
                startActivity(new Intent(getApplicationContext(), ProductActivity.class));

                break;

            case R.id.customer:
                startActivity(new Intent(getApplicationContext(), AdmincustomerActivity.class));

                break;

            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), UserprofileActivity.class));

                break;

        }
        return true;
    }


}
