package com.example.attendance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

<<<<<<< HEAD:app/src/main/java/com/example/attendance/Activity/ScannerStudant.java
<<<<<<< HEAD
=======

>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25

public class ScannerStudant extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scannerView;
    private DatabaseReference databaseReference;
    private String data;


=======
public class ScannerStudant extends AppCompatActivity implements ZXingScannerView.ResultHandler{
            ZXingScannerView scannerView;
    DatabaseReference databaseReference;
>>>>>>> parent of 5876f10 (afd):app/src/main/java/com/example/attendance/Activity/ScannerStudentActivity.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
<<<<<<< HEAD:app/src/main/java/com/example/attendance/Activity/ScannerStudant.java
<<<<<<< HEAD
=======

>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25

        databaseReference = FirebaseDatabase.getInstance().getReference();

=======
        databaseReference= FirebaseDatabase.getInstance().getReference("ehab");
<<<<<<< HEAD

=======
        databaseReference= FirebaseDatabase.getInstance().getReference("ehab");
=======
>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25
>>>>>>> parent of 5876f10 (afd):app/src/main/java/com/example/attendance/Activity/ScannerStudentActivity.java
        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


    }

    @Override
    public void handleResult(Result rawResult) {
<<<<<<< HEAD:app/src/main/java/com/example/attendance/Activity/ScannerStudant.java
<<<<<<< HEAD
=======

>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25

         data = rawResult.getText().toString();


=======
        String data =rawResult.getText().toString();
<<<<<<< HEAD

=======
        String data =rawResult.getText().toString();
=======
>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25
>>>>>>> parent of 5876f10 (afd):app/src/main/java/com/example/attendance/Activity/ScannerStudentActivity.java
        databaseReference.push().setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
<<<<<<< HEAD:app/src/main/java/com/example/attendance/Activity/ScannerStudant.java

                        databaseReference =FirebaseDatabase.getInstance().getReference("Weeks");

                        Toast.makeText(ScannerStudant.this, "Done", Toast.LENGTH_SHORT).show();

<<<<<<< HEAD
=======
        Toast.makeText(ScannerStudant.this, "Done", Toast.LENGTH_SHORT).show();

>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25
=======
                        Toast.makeText(ScannerStudant.this, "Done", Toast.LENGTH_SHORT).show();
>>>>>>> parent of 5876f10 (afd):app/src/main/java/com/example/attendance/Activity/ScannerStudentActivity.java
                        onBackPressed();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
   private ArrayList<String> segmentData(String data){
       return null;
   }



<<<<<<< HEAD
=======


>>>>>>> 571b0467c8745ca1c36112dd35f3170d5cba1c25
}