package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.Adapter.AdapterForWeeks;
import com.example.attendance.Domin.WeeksModel;
import com.example.attendance.Fragments.FragmentDialoge;
import com.example.attendance.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;

public class WeeksActivity extends AppCompatActivity {
    private static final int IMAGE_REQEST_CODE = 103;
private FloatingActionButton floatingActionButtonInWeeks;
    private Toolbar toolbarinWeeks;
    private RecyclerView recyclerViewInWeeks;
    private DatabaseReference databaseReferenceInWeeks;
    private FirebaseUser firebaseUserInWeeks;
    private ArrayList<WeeksModel>arrayListInWeeks;
    private AdapterForWeeks adapterForWeeks;
    private ImageView ivroom;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks);
        recyclerViewInWeeks = findViewById(R.id.recycler_view_in_WeeksActivity);
        toolbarinWeeks = findViewById(R.id.toolbar_in_admin_room);
        floatingActionButtonInWeeks=findViewById(R.id.floatingActionButtonInWeeksActivity);
        setSupportActionBar(toolbarinWeeks);
        firebaseUserInWeeks = FirebaseAuth.getInstance().getCurrentUser();
        databaseReferenceInWeeks = FirebaseDatabase.getInstance().getReference().child("Rooms").child("WeekNumber");
        recyclerViewInWeeks.setHasFixedSize(true);
        recyclerViewInWeeks.setLayoutManager(new GridLayoutManager(this,2));
        arrayListInWeeks=new ArrayList<>();
        adapterForWeeks=new AdapterForWeeks(arrayListInWeeks);
        recyclerViewInWeeks.setAdapter(adapterForWeeks);
        databaseReferenceInWeeks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    WeeksModel weeksModel=dataSnapshot.getValue(WeeksModel.class);
                    arrayListInWeeks.add(weeksModel);
                }
                adapterForWeeks.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        floatingActionButtonInWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiloge();
            }
        });
    }
    public void openDiloge(){
        FragmentDialoge fragmentDialoge=new FragmentDialoge();
        fragmentDialoge.show(getSupportFragmentManager(),"Fragment Dialoge");
    }
}