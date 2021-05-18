package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

import static com.example.attendance.Activity.AdminRoomActivity.ROOM_ID;
import static com.example.attendance.Activity.AdminRoomActivity.ROOM_TITLE;

public class AdminActivity extends AppCompatActivity {
    private TextView tvAdminLogOut, tvAdminName, tvRoomTitle, tvweek, tvAttendance;
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
        tvAdminLogOut = findViewById(R.id.log_out);
        imAdminDetails = findViewById(R.id.admin_image);
        tvRoomTitle = findViewById(R.id.tv_room_title_admin);
        tvweek = findViewById(R.id.text_view_week);
        tvAttendance = findViewById(R.id.text_attendence_in_admin);

        tvAdminName.setText(firebaseUser.getDisplayName());


        data = getIntent();
        roomId = data.getStringExtra(ROOM_ID);
        roomTitle = data.getStringExtra(ROOM_TITLE);


        tvRoomTitle.setText(roomTitle);

    }

    @Override
    protected void onStart() {
        super.onStart();


        tvAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getBaseContext(), AttendanceListActivity.class);
                intent.putExtra(ROOM_ID,roomId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

            }
        });

        tvweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WeeksActivity.class);
                intent.putExtra(ROOM_TITLE, roomTitle);
                intent.putExtra(ROOM_ID, roomId);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                startActivity(intent);
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


        imAdminDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DetailsActivity.class));
                overridePendingTransition(R.anim.slide_up, R.anim.stay);
            }
        });

    }


    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main_bk_color));
        }
    }
}