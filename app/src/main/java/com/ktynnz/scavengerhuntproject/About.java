package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class About extends AppCompatActivity {

    View background;
    TextView about;
    ImageButton git, ig, fb, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Animated transition from opening one activity to another(in, out)
        overridePendingTransition(R.anim.fadein_transition, R.anim.fadeout_transition);

        //Hide action top action bar and bottom nav bar
        getSupportActionBar().hide();
        background = findViewById(R.id.a_background);
        background.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        git = findViewById(R.id.img_git);
        ig = findViewById(R.id.img_ig);
        fb = findViewById(R.id.img_fb);
        contact = findViewById(R.id.img_mail);

        about = findViewById(R.id.textAbout);
        about.setText("This is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\n" +
                "This is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\n" +
                "This is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\n" +
                "This is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\n" +
                "This is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\nThis is my Android Dev Project. @kfernando\n");
    }

    public void fb(View v)
    {
        Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
        startActivity(fb);
    }

    public void ig(View v)
    {
        Intent ig = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com"));
        startActivity(ig);
    }

    public void git(View v)
    {
        //link to github project
        Intent git = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.github.com"));
        startActivity(git);
    }

    public void email(View v)
    {
        Intent support = new Intent(this, Contact.class);
        startActivity(support);
    }

    public void exit(View v)
    {
        Intent home = new Intent(this, Home.class);
        startActivity(home);
    }

}