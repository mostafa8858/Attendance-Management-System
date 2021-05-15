package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.Adapter.AdapterForRooms;
import com.example.attendance.Domin.Room;
import com.example.attendance.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class WeeksActivity extends AppCompatActivity {
    private static final int IMAGE_REQEST_CODE = 103;
private FloatingActionButton floatingActionButtonInWeeks;
    private Toolbar toolbarinWeeks;
    private RecyclerView recyclerViewInWeeks;
    private DatabaseReference databaseReferenceInWeeks;
    private EditText etRoomTitleInWeeks;
    private FirebaseUser firebaseUserInWeeks;
    private ArrayList<Room> roomsInWeeks;
    private AdapterForRooms adapterForAdminRoomsInWeeks;
    private ImageView ivroom;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks);
        toolbarinWeeks = findViewById(R.id.toolbar_in_admin_room);
        floatingActionButtonInWeeks=findViewById(R.id.floatingActionButtonInWeeksActivity);
        setSupportActionBar(toolbarinWeeks);
        firebaseUserInWeeks = FirebaseAuth.getInstance().getCurrentUser();
        databaseReferenceInWeeks = FirebaseDatabase.getInstance().getReference();
        floatingActionButtonInWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiloge();
            }
        });
    }
    public void openDiloge(){}
}