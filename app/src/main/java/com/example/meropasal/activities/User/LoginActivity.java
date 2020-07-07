package com.example.meropasal.activities.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meropasal.MainActivity;
import com.example.meropasal.R;
import com.example.meropasal.activities.admin.AdminaddproductActivity;
import com.example.meropasal.activities.admin.AdminhomeActivity;
import com.example.meropasal.bll.LoginBLL;
import com.example.meropasal.strictmode.StrictModeClass;
import com.example.meropasal.url.url;

public class LoginActivity extends AppCompatActivity {

    private Button btnsignup, btnlogin;
    private EditText etusername, etpassword;
    Vibrator vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnsignup = findViewById(R.id.btnsignup);
        etusername = findViewById(R.id.etusername);
        vibrate = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnSignin);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignup();

            }

            private void openSignup() {

                Intent openSignup = new Intent(LoginActivity.this, AdminaddproductActivity.class);
                startActivity(openSignup);
            }

        });

        SharedPreferences sharedPreferences = getSharedPreferences("IMS",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","empty");
        String status = sharedPreferences.getString("status","isadmin");


        if(!token.equals("empty") && status.equals("isadmin")){
            url.token = token;
            url.status = status;
            Intent intent = new Intent(LoginActivity.this, AdminhomeActivity.class);
            startActivity(intent);
        }

       else if(!token.equals("empty") && !status.equals("isadmin")){
            url.token = token;
            url.status = status;
            Intent intent = new Intent(LoginActivity.this, UserdashActivity.class);
            startActivity(intent);
        }
//         else {
//            url.token = token;
//            url.status = status;
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }





        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///validation for login
                String username = etusername.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    etusername.setError("Enter username");
                    return;
                }

                String password = etpassword.getText().toString();

                if (TextUtils.isEmpty(password)) {
                    etpassword.setError("Enter password");
                    return;
                }

                login();


            }
        });
    }

    private void login() {

        String username = etusername.getText().toString().trim();
        String password = etpassword.getText().toString().trim();


        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {

            SharedPreferences sharedPreferences = getSharedPreferences("IMS",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", url.token);
            editor.putString("status", url.status);
            editor.putString("username",  username);
            editor.putString("password",  password);
            editor.commit();
             Intent intent = new Intent(LoginActivity.this, UserdashActivity.class);
             startActivity(intent);
             finish();

        }
        else {
            if (loginBLL.checkadmin(username, password)) {

                SharedPreferences sharedPreferences = getSharedPreferences("IMS",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", url.token);
                editor.putString("isadmin", url.status);
                editor.putString("username",  username);
                editor.putString("password",  password);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, AdminhomeActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Username or Password doesnot match", Toast.LENGTH_SHORT).show();
                vibrate.vibrate(1000);
                etusername.requestFocus();
            }
        }




    }
}



