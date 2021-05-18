package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.Adapter.AdapterForWeeks;
import com.example.attendance.Domin.Week;
import com.example.attendance.Fragments.FragmentDialoge;
import com.example.attendance.Listener.RecyclerViewOnClickListenerForWeek;
import com.example.attendance.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;

import static com.example.attendance.Activity.AdminRoomActivity.ROOM_ID;
import static com.example.attendance.Activity.AdminRoomActivity.ROOM_TITLE;

public class WeeksActivity extends AppCompatActivity {

    public static final String WEEK_ID = "week id";
    public static final String WEEK_DATE = "week date";
    private FloatingActionButton floatingActionButtonInWeeks;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private ArrayList<Week> weeks;
    private AdapterForWeeks adapterForWeeks;
    private Intent intent;
    private String roomTitle, roomId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks);


        toolbar = findViewById(R.id.toolbar_in_admin_room);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recycler_view_in_WeeksActivity);
        floatingActionButtonInWeeks = findViewById(R.id.floatingActionButtonInWeeksActivity);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        intent = getIntent();
        roomTitle = intent.getStringExtra(ROOM_TITLE);
        roomId = intent.getStringExtra(ROOM_ID);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weeks = new ArrayList<>();
        adapterForWeeks = new AdapterForWeeks(weeks, new RecyclerViewOnClickListenerForWeek() {
            @Override
            public void onClick(Week week) {

                String weekID = week.getWeekID();
                String weekDate=week.getWeekDate();


                Intent intent = new Intent(getBaseContext(), GenerateQrCodeActivity.class);
                intent.putExtra(ROOM_ID, roomId);
                intent.putExtra(ROOM_TITLE, roomTitle);
                intent.putExtra(WEEK_ID,weekID);
                intent.putExtra(WEEK_DATE,weekDate);
                startActivity(intent);

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


    @Override
    protected void onStart() {
        super.onStart();


        floatingActionButtonInWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiloge();
            }
        });

    }

    public void openDiloge() {
        FragmentDialoge fragmentDialoge = new FragmentDialoge();
        fragmentDialoge.putRoomDetailsinWeeks(roomId, roomTitle);
        fragmentDialoge.show(getSupportFragmentManager(), "Fragment Dialoge");
    }


}


