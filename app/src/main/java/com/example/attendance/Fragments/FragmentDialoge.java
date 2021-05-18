package com.example.attendance.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatDialogFragment;


import com.example.attendance.Domin.Week;
import com.example.attendance.R;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class FragmentDialoge extends AppCompatDialogFragment {
    private EditText editTextWeeks;
    private ImageView imageView;

    private DatabaseReference databaseReference;

    private String roomId, roomTitle;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Weeks").child(roomId);

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
                Date date = new Date();
                String weekDate = date.toString();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Weeks").child(roomId).child(weekId);
                Week week = new Week();
                week.setWeekName(weekNumber);
                week.setWeekID(weekId);
                week.setWeekDate(weekDate);

                databaseReference.setValue(week);


            }
        });
        editTextWeeks = view.findViewById(R.id.editTextAddWeeks);
        imageView = view.findViewById(R.id.weeksImageCustom);
        return builder.create();
    }

    public void putRoomDetailsinWeeks(String roomIdInWeeks, String roomTitleInWeeks) {
        this.roomId = roomIdInWeeks;
        this.roomTitle = roomTitleInWeeks;


    }

}
