package com.example.attendance;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.attendance.Domin.Room;
import com.example.attendance.Domin.WeeksModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FragmentDialoge extends AppCompatDialogFragment {
    private EditText editTextWeeks;
    private ImageView imageView;

    private DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Rooms").child("WeekNumber");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String adminUid = firebaseUser.getUid();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.customdialogweeks,null);
        builder.setView(view).setTitle("Create Weeks").
                setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String week=editTextWeeks.getText().toString();

                WeeksModel weeksModel=new WeeksModel(week);

              databaseReference.push().setValue(weeksModel);

            }
        });
        editTextWeeks=view.findViewById(R.id.editTextAddWeeks);
        imageView=view.findViewById(R.id.weeksImageCustom);
return builder.create();
    }
}
