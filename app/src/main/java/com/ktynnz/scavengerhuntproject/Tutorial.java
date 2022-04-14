package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Tutorial extends AppCompatActivity {

    TextView instruction1, num;
    ImageView hand, thunder, arrow;
    FloatingActionButton reset, home;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //Animated transition from opening one activity to another(in, out)
        overridePendingTransition(R.anim.fadein_transition, R.anim.fadeout_transition);

        //Animated Gradient Background
        //1. create 3 separate drawable resource files. Add gradient to each.
        //2. create 1 animation list (drawable resource file) then add the 3 gradients you created to the list
        //3. set background to gradientlist you created then connect to animation
        ConstraintLayout backgroundGradient = findViewById(R.id.background);
        AnimationDrawable animationDrawable = (AnimationDrawable) backgroundGradient.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        instruction1 = findViewById(R.id.instruction1);
        thunder = findViewById(R.id.thunderyellow);
        arrow = findViewById(R.id.arrow);
        num = findViewById(R.id.numItem);
        reset = findViewById(R.id.reset);
        home = findViewById(R.id.t_home);
        hand = findViewById(R.id.hand);

        //Hide action top action bar and bottom nav bar
        getSupportActionBar().hide();
        backgroundGradient.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        tutorial();
    }

    public void tutorial()
    {
        reset.setVisibility(View.INVISIBLE);
        home.setVisibility(View.INVISIBLE);
        hand.setVisibility(View.INVISIBLE);
        instruction1.setText("Find everything to beat a level.");
        thunder.setEnabled(false);

        Animation arrowhead = AnimationUtils.loadAnimation(this, R.anim.anim_arrow);
        //arrowhead.setRepeatCount(Animation.INFINITE);
        //arrowhead.setRepeatMode(Animation.REVERSE);
        arrow.startAnimation(arrowhead);

        Animation slide = AnimationUtils.loadAnimation(this, R.anim.slide_downup);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                arrow.clearAnimation();
                instruction1.setText("Click to obtain the missing item.");
                instruction1.setTextColor(Color.rgb(255, 189, 230));
                hand.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.INVISIBLE);
                thunder.setEnabled(true);
                hand.startAnimation(slide);
            }
        }, 8000);
    }

    public void reset(View v)
    {
        tutorial();
        instruction1.setTextColor(Color.rgb(213, 202,60));
        num.setText("0/1");
        thunder.setVisibility(View.VISIBLE);
        arrow.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);
        home.setVisibility(View.INVISIBLE);
    }

    public void home(View v)
    {
        Intent home = new Intent(this, Home.class);
        startActivity(home);
    }

    public void thunder(View v)
    {
        reset.setVisibility(View.VISIBLE);
        home.setVisibility(View.VISIBLE);
        hand.clearAnimation();
        hand.setVisibility(View.INVISIBLE);
        thunder.setVisibility(View.INVISIBLE);
        num.setText("1/1");
        instruction1.setText("Good job! You are all set!");
        instruction1.setTextColor(Color.rgb(244,252,217));
    }
}