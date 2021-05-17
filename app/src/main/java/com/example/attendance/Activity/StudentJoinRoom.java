package com.example.attendance.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendance.Adapter.AdapterForRooms;
import com.example.attendance.Domin.Room;
import com.example.attendance.R;
import com.example.attendance.Listener.RecyclerViewOnClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentJoinRoom extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private ArrayList<Room> rooms;
    private AdapterForRooms adapterForAdminRooms;
    private ViewGroup dialogView;
    private ImageView ivRoomView;
    private EditText etRooomAdmin;
    private TextView tvRoomTitle, tvRoomId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join_room);


        toolbar = findViewById(R.id.toolbar_in_student_join_room);
        setSupportActionBar(toolbar);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        recyclerView = findViewById(R.id.recycler_view_in_student_join_room);

        dialogView = (ViewGroup) LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_dialog_join_room, null, false);
        ivRoomView = dialogView.findViewById(R.id.room_image_in_dialog_join_room);
        etRooomAdmin = dialogView.findViewById(R.id.editTextRoomTitle_dialog_join_room);
        tvRoomTitle = dialogView.findViewById(R.id.room_title_in_dialog_join_room);
        tvRoomId = dialogView.findViewById(R.id.room_id_in_dialog_join_room);


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        rooms = new ArrayList<>();
        adapterForAdminRooms = new AdapterForRooms(rooms, new RecyclerViewOnClickListener() {
            @Override
            public void onClick(Room room) {

                String roomTitle = room.getRoomTitle();
                String roomId = room.getId();
                String roomAdminName = room.getAdmin();
                Uri imageUri = room.getRoomImageUri();


                tvRoomTitle.setText(roomTitle);
                tvRoomId.setText(roomId);
                ivRoomView.setImageURI(imageUri);


                alertDialog.setTitle("Join To Room").setIcon(R.drawable.ic_alert_blue_24).setView(dialogView)
                        .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String answer = etRooomAdmin.getText().toString();

                                if (answer.length() < 3) {

                                    etRooomAdmin.setError("the name length less than 3");
                                    etRooomAdmin.requestFocus();
                                } else {


                                    if (answer.equals(roomAdminName)) {
                                        databaseReference = FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid()).child(roomId);
                                        Room room = new Room(roomTitle, imageUri, null, answer, roomId);
                                        databaseReference.setValue(room);
                                        Toast.makeText(getBaseContext(), "Join sucssfully", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getBaseContext(), "InCorrect", Toast.LENGTH_LONG).show();


                                    }
                                }

                            }


                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                try {
                    alertDialog.show();
                } catch (Exception e) {


                }


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
        getMenuInflater().inflate(R.menu.menu_in_rooms_activity, menu);
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


    }


}