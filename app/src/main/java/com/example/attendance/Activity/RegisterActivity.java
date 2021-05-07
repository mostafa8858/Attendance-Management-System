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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private ImageView tologinImage;
    private TextView tologintext;
    private EditText edEmail, edMobile, edFirstName, edLastName, edPassword, edReWritePassword;
    private Button registerButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference firebaseDatabaseRefrence;

    private TextView rbAdmin, rbStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabaseRefrence = FirebaseDatabase.getInstance().getReference();


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
                 Prevalent.DATA_BASE_NAME_ADMINS="Users";
             }
         });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insertNewUser();
                insertNewUserRealTime();
            }
        });
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


    }
    private void insertNewUserRealTime() {

        String fName, lName, email, password, mobile, reWritepassword, userKind;
        fName = edFirstName.getText().toString();
        lName = edLastName.getText().toString();
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        mobile = edMobile.getText().toString();
        reWritepassword = edReWritePassword.getText().toString();
        //   userKind = selectedRadioButton();


        // first name check

        // .', '#', '$', '[', or ']'

        if (fName.isEmpty()) {
            edFirstName.setError("Enter the Name");
            edFirstName.requestFocus();
        } else if (fName.length() < 3) {
            edFirstName.setError("the name length less than 3");
            edFirstName.requestFocus();
        } else if (fName.contains(".") || fName.contains("#") || fName.contains("$") || fName.contains("[") || fName.contains("]")) {
            edFirstName.setError("must not contain '.', '#', '$', '[', or ']'");
            edFirstName.requestFocus();
        } else if (fName.length() > 10) {
            edFirstName.setError("the name length more than 10");
            edFirstName.requestFocus();

        }


        //last name check
        else if (lName.isEmpty()) {
            edLastName.setError("Enter the Name");
            edLastName.requestFocus();
        } else if (lName.length() < 3) {
            edLastName.setError("the name length less than 3");
            edLastName.requestFocus();
        } else if (lName.contains(".") || lName.contains("#") || lName.contains("$") || lName.contains("[") || lName.contains("]")) {
            edLastName.setError("must not contain '.', '#', '$', '[', or ']'");
            edLastName.requestFocus();
        }


        //email check
        else if (email.isEmpty()) {
            edEmail.setError("Enter the Email");
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


        //user kind check

        // else if (!rbStudent.isChecked() && !rbAdmin.isChecked()) {
        //   Toast.makeText(getBaseContext(), "Select Kind Of User", Toast.LENGTH_LONG).show();

        //      }


        //No wrong detect
        else {
            progressBar.setVisibility(View.VISIBLE);
            // valide the email
            valideEmailAdmin(fName, lName, email, password, reWritepassword,mobile);
            valideEmailUser(fName, lName, email, password, reWritepassword,mobile);
        }


    }
    private void  valideEmailUser(String fName, String lName,String  email,String password,String reWritepassword,String mobile){
        firebaseDatabaseRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child(Prevalent.DATA_BASE_NAME_User).child(email).exists())){
                    HashMap<String, Object> hashMapAdmins = new HashMap<>();
                    hashMapAdmins.put("firstName",fName);
                    hashMapAdmins.put("secondName", lName);
                    hashMapAdmins.put("emailSinUp", email);
                    hashMapAdmins.put("password", password);
                    hashMapAdmins.put("re_password", reWritepassword);
                    hashMapAdmins.put("mobile", mobile);
                    firebaseDatabaseRefrence.child(Prevalent.DATA_BASE_NAME_User).child(email).updateChildren(hashMapAdmins)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Login successfule", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                                        finish();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "NetWork Error ", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void  valideEmailAdmin(String fName, String lName,String  email,String password,String reWritepassword,String mobile){
        firebaseDatabaseRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child(Prevalent.DATA_BASE_NAME_ADMINS).child(email).exists())){
                    HashMap<String, Object> hashMapAdmins = new HashMap<>();
                    hashMapAdmins.put("firstName",fName);
                    hashMapAdmins.put("secondName", lName);
                    hashMapAdmins.put("emailSinUp", email);
                    hashMapAdmins.put("password", password);
                    hashMapAdmins.put("re_password", reWritepassword);
                    hashMapAdmins.put("mobile", mobile);
                    firebaseDatabaseRefrence.child(Prevalent.DATA_BASE_NAME_ADMINS).child(email).updateChildren(hashMapAdmins)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Login successfule", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(getBaseContext(), AdminActivity.class));
                                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                                        finish();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "NetWork Error ", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

   /* private String selectedRadioButton() {
        String userKind;
        if (rbAdmin.isChecked()) {
            userKind = rbAdmin.getText().toString();
        } else if (rbStudent.isChecked()) {
            userKind = rbStudent.getText().toString();

        } else {
            userKind = null;
        }
        return userKind;

    }*/
/*
    private void insertNewUser() {

        String fName, lName, email, password, mobile, reWritepassword, userKind;
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
        } else if (fName.length() < 3) {
            edFirstName.setError("the name length less than 3");
            edFirstName.requestFocus();
        } else if (fName.contains(".") || fName.contains("#") || fName.contains("$") || fName.contains("[") || fName.contains("]")) {
            edFirstName.setError("must not contain '.', '#', '$', '[', or ']'");
            edFirstName.requestFocus();
        } else if (fName.length() > 10) {
            edFirstName.setError("the name length more than 10");
            edFirstName.requestFocus();

        }


        //last name check
        else if (lName.isEmpty()) {
            edLastName.setError("Enter the Name");
            edLastName.requestFocus();
        } else if (lName.length() < 3) {
            edLastName.setError("the name length less than 3");
            edLastName.requestFocus();
        } else if (lName.contains(".") || lName.contains("#") || lName.contains("$") || lName.contains("[") || lName.contains("]")) {
            edLastName.setError("must not contain '.', '#', '$', '[', or ']'");
            edLastName.requestFocus();
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


        //user kind check

        else if (!rbStudent.isChecked() && !rbAdmin.isChecked()) {
            Toast.makeText(getBaseContext(), "Select Kind Of User", Toast.LENGTH_LONG).show();

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
                                .setDisplayName(fName).setPhotoUri(null)
                                .build();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        if (userKind.equals(User.Admin.ADMIN)) {


                            String firstName, lastName, email, phoneNumber, password, id;


                            firebaseDatabaseRefrence = FirebaseDatabase.getInstance().getReference("Admin");
                            firstName = edFirstName.getText().toString();
                            lastName = edLastName.getText().toString();
                            email = edEmail.getText().toString();
                            phoneNumber = edMobile.getText().toString();
                            password = edPassword.getText().toString();
                            id = firebaseDatabaseRefrence.push().getKey();
                            User.Admin admin = new User.Admin(firstName, lastName, email, phoneNumber, password, id, null);
                            firebaseDatabaseRefrence.child(admin.getFirstName() + admin.getLastName() + admin.getId()).setValue(admin);


                        } else if (userKind.equals(User.Student.STUDENT)) {
                            String firstName, lastName, email, phoneNumber, password, fingerPrint, grade, id;
                            firebaseDatabaseRefrence = FirebaseDatabase.getInstance().getReference("Student");
                            firstName = edFirstName.getText().toString();
                            lastName = edLastName.getText().toString();
                            email = edEmail.getText().toString();
                            phoneNumber = edMobile.getText().toString();
                            password = edPassword.getText().toString();
                            id = firebaseDatabaseRefrence.push().getKey();
                            User.Student student =
                                    new User.Student(firstName, lastName, email, phoneNumber, password, "null", "null", id, null);
                            firebaseDatabaseRefrence.child(student.getFirstName() + student.getLastName()+  "   " + student.getGrade() +"    "    +student.getId() ).setValue(student);
                        }


                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getBaseContext(), LoginActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();
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


    }*/


    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


}
