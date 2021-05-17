package com.example.attendance.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.attendance.Domin.Room;
import com.example.attendance.Domin.WeeksModel;
import com.example.attendance.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;

public class FragmentDialogForAddRoom extends AppCompatDialogFragment {
    private static final int IMAGE_REQEST_CODE =150 ;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private EditText etRoomTitle;
    private ImageView ivRoom;
    private Uri imageUri;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

databaseReference=FirebaseDatabase.getInstance().getReference();
firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_dialog_add_room, null);
        etRoomTitle = view.findViewById(R.id.editTextRoomTitle);
        ivRoom = view.findViewById(R.id.editTextRoomTitle);
        builder.setView(view).setTitle("Create Room").
                setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String roomTitle = etRoomTitle.getText().toString();
                String adminName = firebaseUser.getDisplayName();
                String roomId = databaseReference.push().getKey();
                String adminUid = firebaseUser.getUid();


                Room room = new Room(roomTitle, imageUri, null, adminName, roomId);


                databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(adminUid).child(roomId);
                databaseReference.setValue(room);


                databaseReference = FirebaseDatabase.getInstance().getReference("Rooms").child(roomId);
                databaseReference.setValue(room);


            }
        });

        return builder.create();





    }

    @Override
    public void onStart() {
        super.onStart();
        ivRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQEST_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            ivRoom.setImageURI(imageUri);

        }
    }
}
