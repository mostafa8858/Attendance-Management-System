package com.example.attendance.Domin;

import android.net.Uri;

import java.util.ArrayList;

public class Room {

    String roomTitle,admin,id;
    Uri roomImageUri;
    ArrayList<User.Student> students;


    public Room(String roomTitle, Uri roomImageUri, ArrayList<User.Student> students, String admin,String id) {
        this.roomTitle = roomTitle;
        this.roomImageUri = roomImageUri;
        this.students = students;
        this.admin = admin;
        this.id=id;
    }

    public Room() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Uri getRoomImageUri() {
        return roomImageUri;
    }

    public void setRoomImageUri(Uri roomImageUri) {
        this.roomImageUri = roomImageUri;
    }

    public ArrayList<User.Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<User.Student> students) {
        this.students = students;
    }

}
