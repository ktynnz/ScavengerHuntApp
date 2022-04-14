package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Home extends AppCompatActivity {

    View homebg;
    //NavBar
    Button startGame, learn, about, contact;
    ImageButton settings;
    View exit;
    ImageView navbg;

    //Timer
    TextView timeRemaining, lifeRemaining;
    CountDownTimer countDown;
    int life=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        overridePendingTransition(R.anim.fadein_transition, R.anim.fadeout_transition);
        getSupportActionBar().hide();
        homebg = findViewById(R.id.home_bg);
        homebg.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        timeRemaining = findViewById(R.id.timelife);
        lifeRemaining = findViewById(R.id.life);

        //buttons
        startGame = findViewById(R.id.startGame);
        learn = findViewById(R.id.learn);

        //navbar
        settings = findViewById(R.id.h_settings);
        exit = findViewById(R.id.h_exit);
        about = findViewById(R.id.h_about);
        contact = findViewById(R.id.h_contact);
        navbg = findViewById(R.id.navbg);

        lifeRemaining.setText(String.valueOf(life));

        countTimer();
        navBtn();
    }

    public void settings(View v)
    {
        //show buttons when navbar is open
        about.setVisibility(View.VISIBLE);
        contact.setVisibility(View.VISIBLE);
        navbg.setVisibility(View.VISIBLE);
        exit.setVisibility(View.VISIBLE);
        settings.setVisibility(View.INVISIBLE);

        //hide buttons when navbar is open
        startGame.setVisibility(View.INVISIBLE);
        learn.setVisibility(View.INVISIBLE);

        //animation when opening navbar
        Animation t = AnimationUtils.loadAnimation(this, R.anim.anim_isnavbar);
        navbg.startAnimation(t);
        about.startAnimation(t);
        contact.startAnimation(t);
    }

    public void exit (View v)
    {
        //Images and/or buttons will still show if animate is not cleared.
        navbg.clearAnimation();
        about.clearAnimation();
        contact.clearAnimation();
        navBtn();

        Animation t = AnimationUtils.loadAnimation(this, R.anim.anim_xnavbar);
        navbg.startAnimation(t);
        about.startAnimation(t);
        contact.startAnimation(t);
        settings.setVisibility(View.VISIBLE);
        startGame.setVisibility(View.VISIBLE);
        learn.setVisibility(View.VISIBLE);
    }

    public void about(View v)
    {
        Intent about = new Intent(this, About.class);
        startActivity(about);
    }

    public void contact(View v)
    {
        Intent contact = new Intent(this, Contact.class);
        startActivity(contact);
    }

    public void start(View v)
    {
        //decrease life by 1 when user plays a level
        life--;
        lifeRemaining.setText(String.valueOf(life));
        countTimer();
        Intent game = new Intent(this, GameLevel1.class);
        startActivity(game);
    }

    public void learn(View v)
    {
        Intent learn = new Intent(this, Tutorial.class);
        startActivity(learn);
    }

    public void addlife(View v)
    {
        Intent addlife = new Intent(this, MainActivity.class);
        startActivity(addlife);
    }

    /********** FUNCTIONS **********/
    public void navBtn()
    {
        about.setVisibility(View.INVISIBLE);
        contact.setVisibility(View.INVISIBLE);
        navbg.setVisibility(View.INVISIBLE);
        exit.setVisibility(View.INVISIBLE);
    }

    //counter
    public void countTimer()
    {
        countDown = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                //conversions
                //long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timeRemaining.setText(f.format(min) + ":" + f.format(sec));
            }

            @Override
            public void onFinish() {
                //Conditions when timer reaches 00:00

                //if life is still less than 3, countdown will start again.
                if (life < 3)
                {
                    life++;
                    countTimer();
                    lifeRemaining.setText(String.valueOf(life));
                }

                //If life is equal to 3 then timer will pause/stop and will display "FULL"
                if (life == 3)
                {
                    timeRemaining.setText("FULL");
                    countDown.cancel();
                }

            }
        }.start();
    }

}