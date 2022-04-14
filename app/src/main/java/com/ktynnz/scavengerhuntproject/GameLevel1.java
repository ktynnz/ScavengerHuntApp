package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GameLevel1 extends AppCompatActivity implements View.OnClickListener {

    //TIMER
    TextView timeRemaining, txt_thunder, txt_item1, txt_item2, txt_item3;
    ImageButton settings;
    Button home, resume, restart;
    CountDownTimer countDownTimer;
    long startTimeInMillis = 60000;

    //IMAGES
    ImageView cactuseye, circle5, circle3, circle2, circle, round2, round,
                plant, flower2, flower3,                    //to animate when clicked
                game_navBar, item1, item2, item3, thunder;  //missing items

    View gamebg;
    int items = 0;

    //ANIMATION
    Animation rotate, circleRotate, circleRotate1, circleRotate2, circleRotate3, circleRotate4, flowerRotate,
                scaleAnim, circleAnimation;

        //to animate missing items when clicked
    Animation item_1, item_2, item_3, item_thunder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level1);

        overridePendingTransition(R.anim.fadein_transition, R.anim.fadeout_transition);
        //Hide top action bar and bottom nav bar
        getSupportActionBar().hide();
        gamebg = findViewById(R.id.gamebg);
        gamebg.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        //Timer
        timeRemaining = findViewById(R.id.timeRemaining);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);
        home = findViewById(R.id.homeBtn);
        home.setOnClickListener(this);
        resume = findViewById(R.id.resumeBtn);
        resume.setOnClickListener(this);
        restart = findViewById(R.id.restartBtn);
        restart.setOnClickListener(this);


        //Images
        plant = findViewById(R.id.plant);
        plant.setOnClickListener(this);
        flower3 = findViewById(R.id.flower3);
        flower3.setOnClickListener(this);
        circle5 = findViewById(R.id.circle5);
        circle5.setOnClickListener(this);

        //Missing items
        thunder = findViewById(R.id.thunder);
        thunder.setOnClickListener(this);
        item1 = findViewById(R.id.item1);
        item1.setOnClickListener(this);
        item2 = findViewById(R.id.item2);
        item2.setOnClickListener(this);
        item3 = findViewById(R.id.item3);
        item3.setOnClickListener(this);

        game_navBar = findViewById(R.id.game_navbar);
        txt_thunder = findViewById(R.id.txt_thunder);
        txt_item1 = findViewById(R.id.txt_item1);
        txt_item2 = findViewById(R.id.txt_item2);
        txt_item3 = findViewById(R.id.txt_item3);

        //Hide buttons when page opens
        /*home.setVisibility(View.INVISIBLE);
        resume.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.INVISIBLE);
        game_navBar.setVisibility(View.INVISIBLE);*/
        hideNavbar();

        //Animations
        cactuseye = findViewById(R.id.cactusmoneye);
        rotate = AnimationUtils.loadAnimation(this, R.anim.eyerotate);
        cactuseye.startAnimation(rotate);

        circle3 = findViewById(R.id.circle3);
        circleRotate = AnimationUtils.loadAnimation(this, R.anim.circlerotate);
        circle3.startAnimation(circleRotate);

        circle2 = findViewById(R.id.circle2);
        circleRotate2 = AnimationUtils.loadAnimation(this, R.anim.circlerotate2);
        circle2.startAnimation(circleRotate2);

        circle = findViewById(R.id.circle);
        circleRotate4 = AnimationUtils.loadAnimation(this, R.anim.circlerotate3);
        circle.startAnimation(circleRotate4);

        round2 = findViewById(R.id.round2);
        circleRotate3 = AnimationUtils.loadAnimation(this, R.anim.circlerotate3);
        round2.startAnimation(circleRotate3);

        round = findViewById(R.id.round);
        circleRotate1 = AnimationUtils.loadAnimation(this, R.anim.circlerotate1);
        round.startAnimation(circleRotate1);

        flower2 = findViewById(R.id.flower2);
        flowerRotate = AnimationUtils.loadAnimation(this, R.anim.slowrotate);
        flower2.startAnimation(flowerRotate);

        //automatically starts timer as soon as page opens
        startTimer();
    }

    /********** TIMER FUNCTIONS **********/
    public void mTimer()
    {
        countDownTimer = new CountDownTimer( startTimeInMillis, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                startTimeInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish()
            {
                timeRemaining.setText("TIME'S UP!");
                restart.setVisibility(View.VISIBLE);
                home.setVisibility(View.VISIBLE);
                game_navBar.setVisibility(View.VISIBLE);

                //disable btn for all missing items and/or items left to find
                disableMissingItems();
            }
        }.start();
    }

    public void startTimer()
    {
        mTimer();
    }

    public void updateTimer()
    {
        //Format number to appear as 00 00 instead of 0 0
        NumberFormat f = new DecimalFormat("00");

        //Conversion from millis to hour, min, and sec
        //long hour = (millisUntilFinished / 3600000) % 24;
        long min = (startTimeInMillis / 60000) % 60;
        long sec = (startTimeInMillis / 1000) % 60;
        timeRemaining.setText(f.format(min) + ":" + f.format(sec));

        if (min == 0 && sec < 10) {
            timeRemaining.setTextColor(Color.RED);
        }
        else {
            timeRemaining.setTextColor(Color.BLACK);
        }
    }

    //Navbar buttons
    public void hideNavbar()
    {
        home.setVisibility(View.INVISIBLE);
        resume.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.INVISIBLE);
        game_navBar.setVisibility(View.INVISIBLE);
    }

    public void showNavbar()
    {
        home.setVisibility(View.VISIBLE);
        resume.setVisibility(View.VISIBLE);
        restart.setVisibility(View.VISIBLE);
        game_navBar.setVisibility(View.VISIBLE);
    }

    public void enableMissingItems()
    {
        thunder.setEnabled(true);
        item1.setEnabled(true);
        item2.setEnabled(true);
        item3.setEnabled(true);
    }

    public void disableMissingItems()
    {
        thunder.setEnabled(false);
        item1.setEnabled(false);
        item2.setEnabled(false);
        item3.setEnabled(false);
    }
    //BUTTONS
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //Animate images when clicked
            case R.id.plant:
                scaleAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                plant.startAnimation(scaleAnim);
                break;
            case R.id.flower3:
                scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
                flower3.startAnimation(scaleAnim);
                break;
            case R.id.circle5:
                circleAnimation = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
                circle5.startAnimation(circleAnimation);
                break;

                //SETTINGS
            //When clicked, the game will pause and show the Resume, Reset, and Home buttons.
            case R.id.settings:
                countDownTimer.cancel();
                showNavbar();
                disableMissingItems();
                break;
            case R.id.homeBtn:
                Intent homePage = new Intent(this, Home.class);
                startActivity(homePage);
                break;
            case R.id.resumeBtn:
                startTimer();
                hideNavbar();
                enableMissingItems();
                break;
            case R.id.restartBtn:
                startTimeInMillis = 12000;
                items = 0;

                hideNavbar();
                enableMissingItems();

                //Make all missing items visible
                thunder.setVisibility(View.VISIBLE);
                item1.setVisibility(View.VISIBLE);
                item2.setVisibility(View.VISIBLE);
                item3.setVisibility(View.VISIBLE);

                txt_thunder.setText("0/1");
                txt_item1.setText("0/1");
                txt_item2.setText("0/1");
                txt_item3.setText("0/1");
                updateTimer();
                mTimer();
                break;

                //Hidden items
            case R.id.thunder:
                items++;
                item_thunder = AnimationUtils.loadAnimation(this, R.anim.item_scale);
                thunder.startAnimation(item_thunder);
                thunder.setVisibility(View.INVISIBLE);
                txt_thunder.setText("1/1");
                break;
            case R.id.item1:
                items++;
                item_1 = AnimationUtils.loadAnimation(this, R.anim.item_scale);
                item1.startAnimation(item_1);
                item1.setVisibility(View.INVISIBLE);
                txt_item1.setText("1/1");
                break;
            case R.id.item2:
                items++;
                item_2 = AnimationUtils.loadAnimation(this, R.anim.item_scale);
                item2.startAnimation(item_2);
                item2.setVisibility(View.INVISIBLE);
                txt_item2.setText("1/1");
                break;
            case R.id.item3:
                items++;
                item_3 = AnimationUtils.loadAnimation(this, R.anim.item_scale);
                item3.startAnimation(item_3);
                item3.setVisibility(View.INVISIBLE);
                txt_item3.setText("1/1");
                break;
        }
        itemComplete();
    }

    public void itemComplete()
    {
        if (items == 4)
        {
            timeRemaining.setTextColor(Color.BLACK);
            countDownTimer.cancel();
            home.setVisibility(View.VISIBLE);
            game_navBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "YAAAASSSS! You found all " + items + " items!", Toast.LENGTH_SHORT).show();
            timeRemaining.setText("OOOOPPPSSSS!!!! LEVEL 2 IS STILL UNDER CONSTRUCTION.");
            //resume.setVisibility(View.VISIBLE);
            //restart.setVisibility(View.VISIBLE);
        }
    }
}

