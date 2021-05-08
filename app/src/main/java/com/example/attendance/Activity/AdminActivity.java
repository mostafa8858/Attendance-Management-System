package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.attendance.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
TextView tvAdminLogOut,tvGenerateQrCode;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        firebaseAuth=FirebaseAuth.getInstance();

tvGenerateQrCode=findViewById(R.id.generate_QR_code_in_admin)   ;
        tvAdminLogOut = findViewById(R.id.log_out);

    }

    @Override
    protected void onStart() {
        super.onStart();




        tvAdminLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(getBaseContext(), "Log Out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
        tvGenerateQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),GenerateQrCode.class));
            }
        });

    }
}