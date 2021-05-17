package com.example.attendance.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatDialogFragment;


import com.example.attendance.Activity.AdminActivity;
import com.example.attendance.Activity.GenerateQrCode;
import com.example.attendance.Domin.WeeksModel;
import com.example.attendance.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentDialoge extends AppCompatDialogFragment {
    private EditText editTextWeeks;
    private ImageView imageView;

    private DatabaseReference databaseReference;

    private String roomIdInWeeks, roomTitleInWeeks;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Weeks").child(roomIdInWeeks);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.customdialogweeks, null);
        builder.setView(view).setTitle("Create Weeks").
                setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weekNumber = editTextWeeks.getText().toString();
                String weekId = databaseReference.push().getKey();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Weeks").child(roomIdInWeeks).child(weekId);
                WeeksModel weeksModel = new WeeksModel(weekNumber);
                databaseReference.setValue(weeksModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });


            }
        });
        editTextWeeks = view.findViewById(R.id.editTextAddWeeks);
        imageView = view.findViewById(R.id.weeksImageCustom);
        return builder.create();
    }

    public void putRoomDetailsinWeeks(String roomIdInWeeks, String roomTitleInWeeks) {
        this.roomIdInWeeks = roomIdInWeeks;
        this.roomTitleInWeeks = roomTitleInWeeks;


    }

}
