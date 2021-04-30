package com.example.attendance.Domin;

import android.net.Uri;

public class User {
    public class Admin {


        String FirstName, LastName, Email, PhoneNumber, Password, id;
        Uri image;

        public Admin() {
        }

        public Admin(String firstName, String lastName, String email, String phoneNumber, String password, String id, Uri image) {
            FirstName = firstName;
            LastName = lastName;
            Email = email;
            PhoneNumber = phoneNumber;
            Password = password;
            this.id = id;
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            PhoneNumber = phoneNumber;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public Uri getImage() {
            return image;
        }

        public void setImage(Uri image) {
            this.image = image;
        }
    }

    public class Student {
        String FirstName, LastName, Email, PhoneNumber, Password, FingerPrint, grade,id;
        Uri image;

        public Student() {
        }

        public Student(String firstName, String lastName, String email, String phoneNumber, String password, String fingerPrint, String grade, String id, Uri image) {
            FirstName = firstName;
            LastName = lastName;
            Email = email;
            PhoneNumber = phoneNumber;
            Password = password;
            FingerPrint = fingerPrint;
            this.grade = grade;
            this.id = id;
            this.image = image;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            PhoneNumber = phoneNumber;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getFingerPrint() {
            return FingerPrint;
        }

        public void setFingerPrint(String fingerPrint) {
            FingerPrint = fingerPrint;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public Uri getImage() {
            return image;
        }

        public void setImage(Uri image) {
            this.image = image;
        }
    }


}
