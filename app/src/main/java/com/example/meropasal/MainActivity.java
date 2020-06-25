package com.example.meropasal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meropasal.activities.User.LoginActivity;
import com.example.meropasal.url.url;

public class MainActivity extends AppCompatActivity {
    Button blogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogout = findViewById(R.id.btn_logout);



        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }

            private void logout() {

//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
//                builder.setCancelable(false);
//                builder.setMessage("Do you want to Logout?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //if user pressed "yes", then he is allowed to exit from application
//                        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("IMS", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.remove("token");
//                        editor.remove("status");
//                        editor.remove("name");
//                        editor.remove("password");
//                        editor.apply();
//                        url.token = "Bearer ";
//                        url.status = "Status ";
//                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(i);
//                    }
//                });

            }

        });
    }
}