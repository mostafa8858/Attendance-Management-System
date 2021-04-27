package com.example.attendance3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {
    ImageView registerImage;
    TextView registertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        changeStatusBarColor();

        registerImage = findViewById(R.id.plus_image_in_login);
        registertext = findViewById(R.id.tv_register_in_login);


        registerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });


    }


    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.login_bk_color));
        }
    }
}