package com.example.attendance.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Backend;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

import butterknife.ButterKnife;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
   Paper paper;
    private static final String EMAIL = "email";
    private CheckBox checkBox;
    private LoginButton faceBookLogin;
    private ImageView registerImage;
    private TextView tvRegister, tvForgetPassword;
    private EditText edEmail, edPassword;
    private Button loginButton;
    private ProgressBar progressBar;
    public FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private CallbackManager mCallbackManager;
    private String email, password;
    private static final String TAG = "FacebookAuth";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        changeStatusBarColor();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        checkBox=findViewById(R.id.checkboxLogin);

        FacebookSdk.sdkInitialize(LoginActivity.this);
        mCallbackManager = CallbackManager.Factory.create();
        faceBookLogin = findViewById(R.id.login_button_facebook);
        registerImage = findViewById(R.id.plus_image_in_login);
        tvRegister = findViewById(R.id.tv_register_in_login);
        edEmail = findViewById(R.id.editInputEmaillogin);
        edPassword = findViewById(R.id.editInputPasswordlogin);
        loginButton = findViewById(R.id.loginbutton);
        progressBar = findViewById(R.id.progressBar_login);
        tvForgetPassword = findViewById(R.id.textView_forgetPassword);

     //   firebaseAuth.signOut();

    }

    @Override
    public void onStart() {
        super.onStart();


        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainScreenForGetPassword.class));
            }
        });


        faceBookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFaceBook();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
//                userLoginRealTime();

            }

        });
        registerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                finish();
            }
        });


        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }

    }

    // FaceBook Login
    private void loginWithFaceBook() {

        faceBookLogin.setPermissions(Arrays.asList(EMAIL));
        faceBookLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError" + error);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(), "login sucsses", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getBaseContext(), StudentActivity.class));
            finish();
        } else {
            Toast.makeText(this, "please sign to continue", Toast.LENGTH_SHORT).show();
        }
    }

    // End of FaceBook Login
    private boolean checkFields() {

        email = edEmail.getText().toString();
        password = edPassword.getText().toString();

        //check Name
        if (email.isEmpty()) {
            edEmail.setError("Email is required");
            edEmail.requestFocus();
            return false;
        }
        //check password
        else if (password.isEmpty()) {
            edPassword.setError("password is required");
            edPassword.requestFocus();
            return false;
        } else {
            return true;
        }


    }

    private void userLogin() {

        if (checkFields()) {
            progressBar.setVisibility(View.VISIBLE);
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        checkUserAccess();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }

                }
            });
        }
    }

    private void checkUserAccess() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference documentReference = firebaseFirestore.collection("Admin").document(user.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // is admin
                    startActivity(new Intent(getBaseContext(), AdminRoomActivity.class));

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "login success", Toast.LENGTH_LONG).show();
                    Toast.makeText(getBaseContext(), "you are admin", Toast.LENGTH_LONG).show();
                    finish();
                } else {

                    //is student
                    startActivity(new Intent(getBaseContext(), StudentRoomActivity.class));
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "login success", Toast.LENGTH_LONG).show();
                    Toast.makeText(getBaseContext(), "you are student", Toast.LENGTH_LONG).show();
                    finish();
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

