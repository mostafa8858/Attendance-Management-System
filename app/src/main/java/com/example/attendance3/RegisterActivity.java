package com.example.attendance3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    ImageView loginImage;
    TextView logintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();


        loginImage = findViewById(R.id.back_image_in_register);
        logintext = findViewById(R.id.tv_login_in_register);


        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), loginActivity.class));

            }
        });

        loginImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), loginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }








    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


}