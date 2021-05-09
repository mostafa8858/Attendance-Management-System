package com.example.attendance.Domin;

import android.net.Uri;

public class User {
    public static class Admin {

        public final static String ADMIN = "Admin";
        private String firstName, lastName, email, phoneNumber, password,id;
        private Uri image;

        public Admin() {
        }

        public Admin(String firstName, String lastName, String email, String phoneNumber, String password,String id, Uri image) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.id=id;
            this.phoneNumber = phoneNumber;
            this.password = password;
            this.image = image;


        }




        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Uri getImage() {
            return image;
        }

        public void setImage(Uri image) {
            this.image = image;
        }
    }

    public static class Student {
        public final static String STUDENT = "Student";
        private String firstName, lastName, email, phoneNumber, password, fingerPrint, grade,id;
        private Uri image;


        public Student() {
        }

        public Student(String firstName, String lastName, String email, String phoneNumber, String password, String fingerPrint, String grade,String id, Uri image) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.id=id;
            this.password = password;
            this.fingerPrint = fingerPrint;
            this.grade = grade;
            this.image = image;

        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFingerPrint() {
            return fingerPrint;
        }

        public void setFingerPrint(String fingerPrint) {
            this.fingerPrint = fingerPrint;
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
