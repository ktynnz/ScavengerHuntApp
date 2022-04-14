package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView scavenger, s_h, s_u, s_n, s_t;
    Animation scavenger_, h, u, n, t;
    ImageView shapes;
    View bglayout;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        bglayout = findViewById(R.id.s_bglayout);
        scavenger = findViewById(R.id.s_scavenger);
        s_h = findViewById(R.id.H);
        s_u = findViewById(R.id.U);
        s_n = findViewById(R.id.N);
        s_t = findViewById(R.id.T);

        //Hide action top action bar and bottom nav bar
        getSupportActionBar().hide();
        bglayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //ANIMATION
        scavenger_ = AnimationUtils.loadAnimation(this, R.anim.anim_fade);
        scavenger.startAnimation(scavenger_);

        h = AnimationUtils.loadAnimation(this, R.anim.anim_h);
        s_h.startAnimation(h);

        u = AnimationUtils.loadAnimation(this, R.anim.anim_u);
        s_u.startAnimation(u);

        n = AnimationUtils.loadAnimation(this, R.anim.anim_n);
        s_n.startAnimation(n);

        t = AnimationUtils.loadAnimation(this, R.anim.anim_t);
        s_t.startAnimation(t);

        shapes = findViewById(R.id.s_shapebg);
        Animation bgshape2 = AnimationUtils.loadAnimation(this, R.anim.bgslide);
        shapes.startAnimation(bgshape2);

        //Delay of 3 sec before it opens next page
        Intent intent = new Intent(this, Home.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        },3000);
    }
}