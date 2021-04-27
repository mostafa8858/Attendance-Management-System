package com.example.attendance;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private ImageView tologinImage, facebookRegisterImage, googleRegisterImage;
    private TextView tologintext;
    private EditText edEmail, edMobile, edName, edPassword, edReWritePassword;
    private Button registerButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        firebaseAuth = FirebaseAuth.getInstance();

        tologinImage = findViewById(R.id.to_back_image_in_register);
        tologintext = findViewById(R.id.to_tv_login_in_register);
        edEmail = findViewById(R.id.editInputEmailRegister);
        edMobile = findViewById(R.id.editInputMobileRegister);
        edName = findViewById(R.id.editInputNameRegister);
        edPassword = findViewById(R.id.editInputPasswordRegister);
        edReWritePassword = findViewById(R.id.editInputReWritePasswordRegister);
        registerButton = findViewById(R.id.register_Button);
        facebookRegisterImage = findViewById(R.id.facebook_image_register);
        googleRegisterImage = findViewById(R.id.google_image_register);
        progressBar = findViewById(R.id.progressBar_register);

        tologintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
        tologinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNewUser();
            }
        });
    }
    private void insertNewUser() {

        String name, email, password, mobile, reWritepassword;
        name = edName.getText().toString();
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        mobile = edMobile.getText().toString();
        reWritepassword = edReWritePassword.getText().toString();

        // name check
        if (name.isEmpty()) {
            edName.setError("Enter the Name");
            edName.requestFocus();
        } else if (name.length() < 3) {
            edName.setError("the name length less than 3");
            edName.requestFocus();
        }
        //email check
        else if (email.isEmpty()) {
            edEmail.setError("Enter the Email");
            edEmail.requestFocus();
        } else if (!(email.contains("@") && email.contains("."))) {
            edEmail.setError("Example@domin.com");
            edEmail.requestFocus();
        }
        //mobile check
        else if (mobile.isEmpty()) {
            edMobile.setError("Enter the Phone Number");
            edMobile.requestFocus();
        }
        //password check
        else if (password.isEmpty()) {
            edPassword.setError("Enter the Password");
            edPassword.requestFocus();
        } else if (password.length() < 6) {
            edPassword.setError("password length less than 6");
            edPassword.requestFocus();

        }
        //rewrite password
        else if (reWritepassword.isEmpty()) {
            edReWritePassword.setError("Enter the rewrite password");
            edReWritePassword.requestFocus();
        } else if (!password.equals(reWritepassword)) {
            edReWritePassword.setError("password is not equal");
            edReWritePassword.requestFocus();
        }

        //No wrong detect
        else {
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(getBaseContext(), "Added Account", Toast.LENGTH_LONG).show();

                      firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name).setPhotoUri(null)
                                .build();
                        firebaseUser.updateProfile(userProfileChangeRequest);


                        // دي فيها شك

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthCredential.zzb("02", mobile);
                        firebaseUser.updatePhoneNumber(phoneAuthCredential);

                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getBaseContext(), LoginActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });


        }


    }
    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


}
