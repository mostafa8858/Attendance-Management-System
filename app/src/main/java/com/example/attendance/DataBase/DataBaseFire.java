package com.example.attendance.DataBase;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.attendance.Activity.MainActivity;
import com.example.attendance.Domin.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataBaseFire {
    private DatabaseReference firebaseDatabaseReferencee;

    public DataBaseFire() {
    }

  public  ArrayList<User.Admin> getAllAdmin() {

        ArrayList<User.Admin> admins=new ArrayList<>();

       firebaseDatabaseReferencee= FirebaseDatabase.getInstance().getReference();
        firebaseDatabaseReferencee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
           for (DataSnapshot dataSnapshot : snapshot.child("Admin").getChildren()){
               User.Admin admin=dataSnapshot.getValue(User.Admin.class);
               admins.add(admin);


           }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
return  admins;
    }

}
