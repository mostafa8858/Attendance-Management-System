package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.attendance.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.attendance.Activity.AdminRoomActivity.ROOM_ID;
import static com.example.attendance.Activity.WeeksActivity.WEEK_ID;

public class StudentListActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
private Intent intent;
private String roomId,weekId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        databaseReference=FirebaseDatabase.getInstance().getReference();






        intent=getIntent();
        roomId=intent.getStringExtra(ROOM_ID);
        weekId=intent.getStringExtra(WEEK_ID);
    }
}