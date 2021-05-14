package com.example.attendance.Activity;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.Domin.User;
import com.example.attendance.Prevalent;
import com.example.attendance.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    private ImageView tologinImage;
    private TextView tologintext;
    private EditText edEmail, edMobile, edFirstName, edLastName, edPassword, edReWritePassword;
    private RadioButton rbAdmin, rbStudent;
    private Button registerButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference firebaseDatabaseRefrence;
    private FirebaseFirestore firebaseFirestore;

    private String fName, lName, email, password, mobile, reWritepassword, userKind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabaseRefrence = FirebaseDatabase.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        tologinImage = findViewById(R.id.to_back_image_in_register);
        tologintext = findViewById(R.id.to_tv_login_in_register);
        edEmail = findViewById(R.id.editInputEmailRegister);
        edMobile = findViewById(R.id.editInputMobileRegister);
        edFirstName = findViewById(R.id.editInputFirstNameRegister);
        edLastName = findViewById(R.id.editInputLastNameRegister);
        edPassword = findViewById(R.id.editInputPasswordRegister);
        edReWritePassword = findViewById(R.id.editInputReWritePasswordRegister);
        registerButton = findViewById(R.id.register_Button);
        rbAdmin = findViewById(R.id.radioButtonAdmin);
        rbStudent = findViewById(R.id.radioButtonStudent);
        progressBar = findViewById(R.id.progressBar_register);

        rbAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setText("Register Admin");
                rbAdmin.setVisibility(v.INVISIBLE);
                rbStudent.setVisibility(v.VISIBLE);
                Prevalent.DATA_BASE_NAME_ADMINS="Admins";

            }
        });
         rbStudent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 registerButton.setText("Register Admin");
                 rbAdmin.setVisibility(v.VISIBLE);
                 rbStudent.setVisibility(v.INVISIBLE);
                 Prevalent.DATA_BASE_NAME_User="Users";
             }
         });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                 insertNewUser();
//                insertNewUserRealTime();
=======
                // insertNewUser();
              //  insertNewUserRealTime();
>>>>>>> a4afea4d2de3b12d7c677f912f49fe595277b6c5
            }
        });

//        rbAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                registerButton.setText("Register Admin");
//                rbAdmin.setVisibility(v.INVISIBLE);
//                rbStudent.setVisibility(v.VISIBLE);
//                Prevalent.DATA_BASE_NAME_ADMINS="Admins";
//
//            }
//        });
//         rbStudent.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 registerButton.setText("Register Admin");
//                 rbAdmin.setVisibility(v.VISIBLE);
//                 rbStudent.setVisibility(v.INVISIBLE);
//                 Prevalent.DATA_BASE_NAME_ADMINS="Users";
//             }
//         });

    }
    @Override
    protected void onStart() {
        super.onStart();

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
//                insertNewUserRealTime();
            }
        });


    }
    private String selectedRadioButton() {
        String userKind;
        if (rbAdmin.isChecked()) {
            userKind = rbAdmin.getText().toString();
        } else if (rbStudent.isChecked()) {
            userKind = rbStudent.getText().toString();

        } else {
            userKind = null;
        }
        return userKind;

    }
    private boolean checkFields() {

        fName = edFirstName.getText().toString();
        lName = edLastName.getText().toString();
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        mobile = edMobile.getText().toString();
        reWritepassword = edReWritePassword.getText().toString();
        userKind = selectedRadioButton();


        // first name check

        // .', '#', '$', '[', or ']'

        if (fName.isEmpty()) {
            edFirstName.setError("Enter the Name");
            edFirstName.requestFocus();
            return false;
        } else if (fName.length() < 3) {
            edFirstName.setError("the name length less than 3");
            edFirstName.requestFocus();
            return false;

        } else if (fName.contains(".") || fName.contains("#") || fName.contains("$") || fName.contains("[") || fName.contains("]")) {
            edFirstName.setError("must not contain '.', '#', '$', '[', or ']'");
            edFirstName.requestFocus();
            return false;

        } else if (fName.length() > 10) {
            edFirstName.setError("the name length more than 10");
            edFirstName.requestFocus();
            return false;

        }


        //last name check
        else if (lName.isEmpty()) {
            edLastName.setError("Enter the Name");
            edLastName.requestFocus();
            return false;

        } else if (lName.length() < 3) {
            edLastName.setError("the name length less than 3");


            edLastName.requestFocus();
            return false;

        } else if (lName.contains(".") || lName.contains("#") || lName.contains("$") || lName.contains("[") || lName.contains("]")) {
            edLastName.setError("must not contain '.', '#', '$', '[', or ']'");
            edLastName.requestFocus();
            return false;

        }


        //email check
        else if (email.isEmpty()) {
            edEmail.setError("Enter the Email");
            edEmail.requestFocus();
            return false;

        } else if (!(email.contains("@") && email.contains("."))) {
            edEmail.setError("Example@domin.com");
            edEmail.requestFocus();
            return false;

        }
        //mobile check
        else if (mobile.isEmpty()) {
            edMobile.setError("Enter the Phone Number");
            edMobile.requestFocus();
            return false;

        }
        //password check
        else if (password.isEmpty()) {
            edPassword.setError("Enter the Password");
            edPassword.requestFocus();
            return false;

        } else if (password.length() < 6) {
            edPassword.setError("password length less than 6");
            edPassword.requestFocus();
            return false;


        }
        //rewrite password
        else if (reWritepassword.isEmpty()) {
            edReWritePassword.setError("Enter the rewrite password");
            edReWritePassword.requestFocus();
            return false;

        } else if (!password.equals(reWritepassword)) {
            edReWritePassword.setError("password is not equal");
            edReWritePassword.requestFocus();
            return false;

        }


        //user kind check

        else if (!rbStudent.isChecked() && !rbAdmin.isChecked()) {
            Toast.makeText(getBaseContext(), "Select Kind Of User", Toast.LENGTH_LONG).show();
            return false;


        } else {
            return true;
        }


    }
    private void insertNewUser() {
        if (checkFields()) {
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fName).setPhotoUri(null)
                                .build();

                        firebaseUser.updateProfile(userProfileChangeRequest);

                        if (rbAdmin.isChecked()) {
                            // admin
                            insertNewAdmin();
                            insertUserInFireStore();
                        } else {
                            //user
                            insertNewStudent();
                            insertUserInFireStore();
                        }
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getBaseContext(), "this Email already  registered", Toast.LENGTH_LONG).show();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }
    private void insertNewStudent() {
        User.Student user = new User.Student(fName, lName, email, mobile, password, null, null, null, null);

        DocumentReference documentReference = firebaseFirestore.collection("Student").document(firebaseUser.getUid());
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Added Account", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
            }
        });
    }
    private void insertNewAdmin() {

        User.Admin user = new User.Admin(fName, lName, email, mobile, password, null, null);

        DocumentReference documentReference = firebaseFirestore.collection("Admin").document(firebaseUser.getUid());
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Added Account", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
            }
        });
    }
    void insertUserInFireStore(){
        if (rbAdmin.isChecked()){
            User.Admin user = new User.Admin(fName, lName, email, mobile, password, null, null);
            DocumentReference documentReference = firebaseFirestore.collection("User").document(firebaseUser.getUid());
            documentReference.set(user);
        }
        else {
            User.Student user = new User.Student(fName, lName, email, mobile, password, null, null, null, null);
            DocumentReference documentReference = firebaseFirestore.collection("User").document(firebaseUser.getUid());
            documentReference.set(user);
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
