package com.example.meropasal.activities.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meropasal.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignup = findViewById(R.id.btnsignup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignup();

            }

            private void openSignup() {

                Intent openSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(openSignup);
            }

        });



    }
}
