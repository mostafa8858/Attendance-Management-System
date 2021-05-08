package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import com.example.attendance.R;

import org.w3c.dom.Text;

import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQrCode extends AppCompatActivity {
    EditText edGenerateCode;
    Button btGenerate, btAutoGenerate;
    ImageView ivQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr_code);

        edGenerateCode = findViewById(R.id.edit_text_genrate_qrcode);
        btGenerate = findViewById(R.id.button_generate_code);
        btAutoGenerate = findViewById(R.id.button_auto_generate_code);
        ivQrCode = findViewById(R.id.imageView_qr_genrate);
    }


    @Override
    protected void onStart() {
        super.onStart();

        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateByAdmin();

            }
        });
        btAutoGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoGenerateByAdmin();
            }
        });

    }


    void generateByAdmin() {
//check edit Text
        if (edGenerateCode.getText().toString().length() < 4) {
            edGenerateCode.setError("less than 4");
            edGenerateCode.requestFocus();

        } else {
            String data = edGenerateCode.getText().toString();
            QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 1000);
            Bitmap bitmap = qrgEncoder.getBitmap();
            ivQrCode.setImageBitmap(bitmap);
            btGenerate.setVisibility(View.GONE);
            btAutoGenerate.setVisibility(View.GONE);
        }

    }

    void autoGenerateByAdmin(){
        Date date=new Date();
        String data = date.toString();
        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 1000);
        Bitmap bitmap = qrgEncoder.getBitmap();
        ivQrCode.setImageBitmap(bitmap);
        btGenerate.setVisibility(View.GONE);
        btAutoGenerate.setVisibility(View.GONE);

    }
}