package com.example.attendance.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.attendance.Adapter.AdapterForRooms;
import com.example.attendance.Domin.Room;
import com.example.attendance.R;
import com.example.attendance.RecyclerViewOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton bnJoinRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_room);


        toolbar = findViewById(R.id.toolbar_in_student_room);
        setSupportActionBar(toolbar);



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        recyclerView=findViewById(R.id.recycler_view_in_student_room);
        bnJoinRoom =findViewById(R.id.floatingActionButtonJoinRoom);



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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_in_rooms_activity,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_in_rooms).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;

    }

    @Override
    protected void onStart() {
        super.onStart();


       ViewGroup dialogView = (ViewGroup) LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_dialog, null, false);
        EditText etRoomTitle = dialogView.findViewById(R.id.editTextRoomTitle);




        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this).setTitle("Join Room")
                .setIcon(R.drawable.ic_alert_blue_24).setView(dialogView);
        bnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String studentUid=firebaseUser.getUid();
                        String roomId=etRoomTitle.getText().toString();

databaseReference=FirebaseDatabase.getInstance().getReference("Student").child(studentUid).child(roomId);


                    }
                }).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                try {
                    alertDialog.show();
                } catch (Exception e) {
                    System.out.println("aaaaaaaaaaaaaaaaa   " + e.getMessage());
                }

            }
        });




    }
}