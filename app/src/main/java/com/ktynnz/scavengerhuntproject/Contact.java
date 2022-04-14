package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    View background_c;
    EditText fullName, email, msg, phoneNum;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Animated transition from opening one activity to another(in, out)
        overridePendingTransition(R.anim.fadein_transition, R.anim.fadeout_transition);

        //Hide action top action bar and bottom nav bar
        getSupportActionBar().hide();
        background_c = findViewById(R.id.c_background);
        background_c.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        fullName = findViewById(R.id.txtFullName);
        email = findViewById(R.id.txtEmail);
        phoneNum = findViewById(R.id.txtPhoneNumber);
        msg = findViewById(R.id.txtMessage);
        send = findViewById(R.id.btnSend);
    }

    public void exit(View v)
    {
        Intent home = new Intent(this, Home.class);
        startActivity(home);
    }

    public void send(View v) {
        //gets and stores values from the form
        String name = fullName.getText().toString();
        String Email = email.getText().toString();
        String phone = phoneNum.getText().toString();
        String message = msg.getText().toString();

        Intent send = new Intent(Intent.ACTION_SENDTO);
        send.putExtra(Intent.EXTRA_EMAIL, Email);

        send.putExtra(Intent.EXTRA_SUBJECT, "Message from " + name);
        send.putExtra(Intent.EXTRA_TEXT, "Message: " + message + "Email: " + Email + "Phone: " + phone);
        send.setData(Uri.parse("mailto:krystynne.fernando@triosstudent.com"));
        if (send.resolveActivity(getPackageManager()) != null) {
            startActivity(send);
        } else {
            Toast.makeText(this, "There is no email app", Toast.LENGTH_SHORT).show();
        }

        Log.d("name", name);
        Log.d("email", Email);
        Log.d("email", phone);
        Log.d("message", message);
    }

}
