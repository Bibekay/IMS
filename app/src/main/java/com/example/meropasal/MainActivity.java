package com.example.meropasal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meropasal.activities.User.LoginActivity;
import com.example.meropasal.api.IMS_api;
import com.example.meropasal.models.Users;
import com.example.meropasal.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button abc, lo;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abc = findViewById(R.id.btn_logout);
        lo=findViewById(R.id.lgout);
        name = findViewById(R.id.username);
        viewUserDetails();


            lo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setMessage("Do you want to Logout?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            //if user pressed "yes", then he is allowed to exit from application
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("IMS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("token");
                            editor.remove("status");
                            editor.remove("isadmin");
                            editor.remove("username");
                            editor.remove("password");
                            editor.apply();
                            url.token = "Bearer ";
                            url.status = "Status";
                            Intent i = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    });
                }
            });



    }

    private void viewUserDetails() {
        IMS_api ims_api = url.getInstance().create(IMS_api.class);
        Call<Users> usersCall = ims_api.getUserDetails(url.token);
        //System.out.println("token is:"+url.token);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                name.setText(response.body().getUsername());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}