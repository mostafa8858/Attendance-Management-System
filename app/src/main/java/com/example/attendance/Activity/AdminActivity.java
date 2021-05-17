package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.Activity.DetailsActivity;
import com.example.attendance.Activity.GenerateQrCode;
import com.example.attendance.Activity.LoginActivity;
import com.example.attendance.FragmentDialoge;
import com.example.attendance.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    private TextView tvAdminLogOut, tvGenerateQrCode, tvAdminName,tvRoomTitle,week;
    private ImageView imAdminDetails;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Intent data;
    private String roomId, roomTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        changeStatusBarColor();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tvAdminName = findViewById(R.id.admin_name);
        tvGenerateQrCode = findViewById(R.id.generate_QR_code_in_admin);
        tvAdminLogOut = findViewById(R.id.log_out);
        imAdminDetails = findViewById(R.id.admin_image);
        tvRoomTitle=findViewById(R.id.tv_room_title_admin);
        week=findViewById(R.id.week);
        tvAdminName.setText(firebaseUser.getDisplayName());


        data = getIntent();
        roomId=data.getStringExtra(AdminRoomActivity.ROOM_ID);
        roomTitle=data.getStringExtra(AdminRoomActivity.ROOM_TITLE);


        tvRoomTitle.setText(roomTitle);

    }

    @Override
    protected void onStart() {
        super.onStart();

        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDiloge();
            }
        });
        tvAdminLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(getBaseContext(), "Log Out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });

        tvGenerateQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), GenerateQrCode.class));
              //openDiloge();
            }
        });
        imAdminDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DetailsActivity.class));
                overridePendingTransition(R.anim.slide_up, R.anim.stay);
            }
        });

    }

    public void openDiloge(){
        FragmentDialoge fragmentDialoge=new FragmentDialoge();
        fragmentDialoge.putRoomDetailsinWeeks(roomId,roomTitle);
        fragmentDialoge.show(getSupportFragmentManager(),"Fragment Dialoge");
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main_bk_color));
        }
    }
}