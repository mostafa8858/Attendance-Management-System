package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class StudentRoomActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_room);


        toolbar = findViewById(R.id.toolbar_in_student_room);
        setSupportActionBar(toolbar);


        recyclerView=findViewById(R.id.recycler_view_in_student_room);
    }

}