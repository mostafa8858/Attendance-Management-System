package com.example.attendance.Domin;

import android.net.Uri;

import java.util.ArrayList;

public class Room {

    String roomTitle;
    Uri roomImageUri;
    ArrayList<User.Student> students;
    User.Admin admin;

    public Room(String roomTitle, Uri roomImageUri, ArrayList<User.Student> students, User.Admin admin) {
        this.roomTitle = roomTitle;
        this.roomImageUri = roomImageUri;
        this.students = students;
        this.admin = admin;
    }

    public Room() {
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

    public User.Admin getAdmin() {
        return admin;
    }

    public void setAdmin(User.Admin admin) {
        this.admin = admin;
    }
}
