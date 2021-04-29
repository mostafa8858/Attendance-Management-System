package com.example.attendance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailsActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST_CODE = 15;
    private Toolbar toolbar;
    private FirebaseUser firebaseUser;
    private EditText edName, edEmail, edPassword, edPhone;
    private ImageView userImage;
    private Uri userImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setSupportActionBar(toolbar);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        toolbar = findViewById(R.id.tool_bar_details);
        edName = findViewById(R.id.edit_text_name_details);
        edEmail = findViewById(R.id.edit_text_email_details);
        edPassword = findViewById(R.id.edit_text_password_details);
        edPhone = findViewById(R.id.edit_text_number_details);
        userImage = findViewById(R.id.image_in_details);


        toolbar.setTitle(firebaseUser.getDisplayName());
        edName.setText(firebaseUser.getDisplayName());
        edEmail.setText(firebaseUser.getEmail());
        edPassword.setText("@!sa6889/#$AFEff");
        edPhone.setText(firebaseUser.getPhoneNumber());

        if (firebaseUser.getPhotoUrl() != null) {
            userImage.setImageURI(firebaseUser.getPhotoUrl());
        }
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_in_details,menu);
        MenuItem edit=menu.findItem(R.id.edit_data_menu);
        MenuItem save=menu.findItem(R.id.save_data_menu);
        edit.setVisible(true);
        save.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }

    void disableFields() {
        edName.setActivated(false);
        edPhone.setActivated(false);
        edPassword.setActivated(false);
        edEmail.setActivated(false);


    }

    void enableFields() {

        edName.setActivated(true);
        edPhone.setActivated(true);
        edPassword.setActivated(true);
        edEmail.setActivated(true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DetailsActivity.IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            userImageUri = data.getData();
            userImage.setImageURI(userImageUri);

        }
    }
}