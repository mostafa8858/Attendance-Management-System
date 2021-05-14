package com.example.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.attendance.R;

public class SplachActivity extends AppCompatActivity {
    private  View first, second, thired, fourth, fifth, sixth;
    private   TextView a, slogan;
    private  Animation topAnimation, bootomAnimatiom, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        changeStatusBarColor();
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bootomAnimatiom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        thired = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        a = findViewById(R.id.a);
        slogan = findViewById(R.id.slogan);
        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        thired.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        sixth.setAnimation(topAnimation);
        a.setAnimation(middleAnimation);
        slogan.setAnimation(bootomAnimatiom);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        });
        thread.start();
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.login_bk_color));
        }
    }

}