package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attendance.Adapter.AdapterForWeeks;
import com.example.attendance.Domin.Week;
import com.example.attendance.Listener.RecyclerViewOnClickListenerForWeek;
import com.example.attendance.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.attendance.Activity.AdminRoomActivity.ROOM_ID;
import static com.example.attendance.Activity.WeeksActivity.WEEK_ID;

public class AttendanceListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private ArrayList<Week> weeks;
    private AdapterForWeeks adapterForWeeks;
    private Intent intent;
    private String  roomId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);



        toolbar = findViewById(R.id.toolbar_in_Attendence_);
        setSupportActionBar(toolbar);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        recyclerView=findViewById(R.id.recycler_view_in_Attendence);



        intent = getIntent();
        roomId = intent.getStringExtra(ROOM_ID);




        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weeks = new ArrayList<>();
        adapterForWeeks = new AdapterForWeeks(weeks, new RecyclerViewOnClickListenerForWeek() {
            @Override
            public void onClick(Week week) {
                String weekId=week.getWeekID();

                Intent  intent=new Intent(getBaseContext(), StudentListActivity.class);
                intent.putExtra(ROOM_ID,roomId);
                intent.putExtra(WEEK_ID,weekId);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);


            }
        });
        recyclerView.setAdapter(adapterForWeeks);





        databaseReference = FirebaseDatabase.getInstance().getReference("Weeks").child(roomId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                adapterForWeeks.updateData(weeks);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Week week = dataSnapshot.getValue(Week.class);
                    weeks.add(week);
                }
                adapterForWeeks.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}