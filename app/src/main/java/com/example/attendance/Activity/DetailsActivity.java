package com.example.attendance.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.attendance.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class DetailsActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST_CODE = 15;
    private Toolbar toolbar;
    private FirebaseUser firebaseUser;
    private EditText edName, edEmail, edPassword, edPhone;
    private ImageView userImage;
    private Uri userImageUri;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        changeStatusBarColor();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        toolbar = findViewById(R.id.tool_bar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edName = findViewById(R.id.edit_text_name_details);
        edEmail = findViewById(R.id.edit_text_email_details);
        edPassword = findViewById(R.id.edit_text_password_details);
        edPhone = findViewById(R.id.edit_text_number_details);
        userImage = findViewById(R.id.image_in_details);


        toolbar.setTitle(firebaseUser.getDisplayName());
        edName.setText(firebaseUser.getDisplayName());
        edEmail.setText(firebaseUser.getEmail());
        edPhone.setText(firebaseUser.getPhoneNumber());


        if (firebaseUser.getPhotoUrl() != null) {
            userImage.setImageURI(firebaseUser.getPhotoUrl());
        }
        userImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_in_details, menu);
        MenuItem edit = menu.findItem(R.id.edit_data_menu);
        MenuItem save = menu.findItem(R.id.save_data_menu);
        edit.setVisible(true);
        save.setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_data_menu: {

                AlertDialog.Builder builder=new AlertDialog.Builder(this);
builder.setTitle("Alert").setIcon(R.drawable.ic_alert).setMessage("Are You Sure Update Data").setPositiveButton("Accept", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(edName.getText().toString()).setPhotoUri(userImageUri).build();

        firebaseUser.updateProfile(userProfileChangeRequest);
        firebaseUser.updateEmail(edEmail.getText().toString());
        firebaseUser.updatePassword(edPassword.getText().toString());
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        Toast.makeText(getBaseContext(),"Data updated",Toast.LENGTH_LONG).show();


    }
}).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getBaseContext(),"Ignore update",Toast.LENGTH_LONG).show();
        menu.findItem(R.id.edit_data_menu).setVisible(true);
        menu.findItem(R.id.save_data_menu).setVisible(false);

    }
}).show();


disableFields();
                return true;
            }
            case R.id.edit_data_menu: {
                enableFields();
                menu.findItem(R.id.edit_data_menu).setVisible(false);
                menu.findItem(R.id.save_data_menu).setVisible(true);

                return true;
            }

        }
        return false;

    }

    void disableFields() {
        edName.setEnabled(false);
        edPhone.setEnabled(false);
        edPassword.setEnabled(false);
        edEmail.setEnabled(false);


    }

    void enableFields() {

        edName.setEnabled(true);
        edPhone.setEnabled(true);
        edPassword.setEnabled(true);
        edEmail.setEnabled(true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DetailsActivity.IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            userImageUri = data.getData();
            userImage.setImageURI(userImageUri);

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