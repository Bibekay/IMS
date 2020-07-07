package com.example.meropasal.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.meropasal.R;

public class AdminaddproductActivity extends AppCompatActivity {

    String id;
    EditText categoryName;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminaddproduct);
        categoryName = findViewById(R.id.et_category);

        back = findViewById(R.id.iv_backpressed);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminaddproductActivity.super.onBackPressed();
            }
        });


        //retriving single data through recycle view from admincategories adapter //
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null ){
            id = bundle.getString("id");
            categoryName.setText(bundle.getString("category_name"));
        }




    }
}