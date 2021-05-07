package com.example.attendance.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.R;
import com.facebook.internal.Validate;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordByNumber extends AppCompatActivity {
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.phoneText)
    EditText phoneText;
    @BindView(R.id.buttonOfForgetPassword)
    Button buttonOfForgetPassword;
    String number;
    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationStateChangedCallbacks;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
  /*      ccp.registerCarrierNumberEditText(phoneText);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void sendCode(View view){
        number=ccp.getFullNumberWithPlus();
        setUpVerifictionCallback();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,60, TimeUnit.SECONDS,this,verificationStateChangedCallbacks
        );

    }
    private void   setUpVerifictionCallback(){
        verificationStateChangedCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
             //   SignInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
        }
    }
    }*/
}}