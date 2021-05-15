package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attendance.Adapter.AdapterForRooms;
import com.example.attendance.Domin.Room;
import com.example.attendance.R;
import com.example.attendance.RecyclerViewOnClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentRoomActivity extends AppCompatActivity {
    public static final String ROOM_TITLE = "roomTitle";
    public static final String ROOM_ID = "roomId";
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private ArrayList<Room> rooms;
    private AdapterForRooms adapterForAdminRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_room);


        toolbar = findViewById(R.id.toolbar_in_student_room);
        setSupportActionBar(toolbar);



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        recyclerView=findViewById(R.id.recycler_view_in_student_room);



        rooms = new ArrayList<>();
        adapterForAdminRooms = new AdapterForRooms(rooms, new RecyclerViewOnClickListener() {
            @Override
            public void onClick(Room room) {

                String roomTitle, roomId;
                roomTitle = room.getRoomTitle();
                roomId = room.getId();
                Intent intent = new Intent(getBaseContext(), StudentActivity.class);
                intent.putExtra(ROOM_TITLE, roomTitle);
                intent.putExtra(ROOM_ID, roomId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapterForAdminRooms);
        recyclerView.setLayoutManager(manager);



        databaseReference.getDatabase().getReference("Rooms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                adapterForAdminRooms.updateData(rooms);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Room room = dataSnapshot.getValue(Room.class);
                    rooms.add(room);

                }
                recyclerView.setAdapter(adapterForAdminRooms);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();




    }
}