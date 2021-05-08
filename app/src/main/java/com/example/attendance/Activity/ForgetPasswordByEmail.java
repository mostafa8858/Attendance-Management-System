package com.example.attendance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.attendance.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordByEmail extends AppCompatActivity {
    EditText editTextSendEmail;
    Button buttonSendEmail;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_by_email);
        editTextSendEmail=findViewById(R.id.editTextForgetPassByEmail);
        buttonSendEmail=findViewById(R.id.ButtonForgetPassByEmail);
        firebaseAuth=FirebaseAuth.getInstance();
        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    private void sendEmail(){
        String email=editTextSendEmail.getText().toString();
        if(email.isEmpty()){editTextSendEmail.setError("Pleas Enter Email");
        }
        else {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgetPasswordByEmail.this, "Pleas Check Your Email Account", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(),LoginActivity.class));

                    }
                    else {
                        String  errorMass=task.getException().getMessage();
                        Toast.makeText(ForgetPasswordByEmail.this, "Error by "+errorMass, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}