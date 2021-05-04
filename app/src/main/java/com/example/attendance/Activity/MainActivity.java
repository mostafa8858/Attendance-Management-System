package com.example.attendance.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.DataBase.DataBaseFire;
import com.example.attendance.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView tvStudentName, tvLogOut;
    private ImageView userImage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        changeStatusBarColor();
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = firebaseAuth.getCurrentUser();


        tvStudentName = findViewById(R.id.user_name);
        tvLogOut = findViewById(R.id.log_out);
        progressBar = findViewById(R.id.progressBar_main);
        userImage = findViewById(R.id.user_image);

        tvStudentName.setText(firebaseUser.getDisplayName());
        if(userImage!=null) {
            userImage.setImageURI(firebaseUser.getPhotoUrl());
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DetailsActivity.class));
                overridePendingTransition(R.anim.slide_up, R.anim.stay);
            }
        });
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(getBaseContext(), "Log Out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });

        tvStudentName.setText(firebaseUser.getDisplayName());
        if(userImage!=null) {
            userImage.setImageURI(firebaseUser.getPhotoUrl());
        }
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main_bk_color));
        }
    }


}