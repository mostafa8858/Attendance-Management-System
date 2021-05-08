package com.example.attendance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreenForGetPassword extends AppCompatActivity {

    @BindView(R.id.txtrestPasswordByPhon)
    TextView txtrestPasswordByPhon;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.txtrestPasswordByEmail)
    TextView txtrestPasswordByEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_for_get_password);
        ButterKnife.bind(this);
        txtrestPasswordByPhon=findViewById(R.id.txtrestPasswordByPhon);
        txtrestPasswordByEmail=findViewById(R.id.txtrestPasswordByEmail);
        txtrestPasswordByEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ForgetPasswordByEmail.class));

            }
        });
        txtrestPasswordByPhon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ForgetPasswordByNumber.class));
            }
        });
    }
}