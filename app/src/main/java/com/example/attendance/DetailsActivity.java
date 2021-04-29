package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailsActivity extends AppCompatActivity {
private Toolbar toolbar;
private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setSupportActionBar(toolbar);


        toolbar=findViewById(R.id.tool_bar_details);



        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        toolbar.setTitle(firebaseUser.getDisplayName());
    }
}