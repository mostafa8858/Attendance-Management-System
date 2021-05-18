package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.attendance.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static com.example.attendance.Activity.AdminRoomActivity.ROOM_ID;
import static com.example.attendance.Activity.WeeksActivity.WEEK_DATE;
import static com.example.attendance.Activity.WeeksActivity.WEEK_ID;

public class GenerateQrCodeActivity extends AppCompatActivity {
    private ImageView ivQrCode;
    private TextView tvDate;
    private String data, roomId, weekId,weekDate;
    private Intent intent;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr_code);

        databaseReference = FirebaseDatabase.getInstance().getReference("Weeks");

        ivQrCode = findViewById(R.id.imageView_qr_genrate);
tvDate=findViewById(R.id.text_in_generatecode_date);


        intent = getIntent();
        roomId = intent.getStringExtra(ROOM_ID);
        weekId = intent.getStringExtra(WEEK_ID);
        weekDate=intent.getStringExtra(WEEK_DATE);

tvDate.setText(weekDate);
        data = roomId + "@@" + weekId;

        System.out.println(data);

        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 1000);
        Bitmap bitmap = qrgEncoder.getBitmap();
        ivQrCode.setImageBitmap(bitmap);


    }


}