package com.example.attendance.DataBase;

import androidx.annotation.NonNull;

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

    public ArrayList<User.Admin> getAllAdmin() {

        ArrayList<User.Admin> admins = new ArrayList<>();

        firebaseDatabaseReferencee = FirebaseDatabase.getInstance().getReference(User.Admin.ADMIN);
        firebaseDatabaseReferencee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User.Admin admin = dataSnapshot.getValue(User.Admin.class);
                    admins.add(admin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return admins;
    }

    public ArrayList<User.Student> getAllStudent() {

        ArrayList<User.Student> students = new ArrayList<>();

        firebaseDatabaseReferencee = FirebaseDatabase.getInstance().getReference(User.Student.STUDENT);
        firebaseDatabaseReferencee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User.Student student = dataSnapshot.getValue(User.Student.class);
                    students.add(student);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return students;
    }

    ArrayList<String> getAdminEmail() {
        ArrayList<String> adminEmails = new ArrayList<>();
        firebaseDatabaseReferencee = FirebaseDatabase.getInstance().getReference(User.Admin.ADMIN);
        firebaseDatabaseReferencee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User.Admin admin = dataSnapshot.getValue(User.Admin.class);
                    adminEmails.add(admin.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return adminEmails;
    }

    ArrayList<String> getStudentEmail() {
        ArrayList<String> studentEmail = new ArrayList<>();
        firebaseDatabaseReferencee = FirebaseDatabase.getInstance().getReference(User.Student.STUDENT);
        firebaseDatabaseReferencee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User.Student student = dataSnapshot.getValue(User.Student.class);
                    studentEmail.add(student.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return studentEmail;
    }

    public boolean adminCheck(String email) {
        ArrayList<String> emails = getAdminEmail();
        if (emails.contains(email)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean StudentCheck(String email) {
        ArrayList<String> emails = getStudentEmail();

        if (emails.contains(email)) {

            return true;
        }

        return false;
    }

}