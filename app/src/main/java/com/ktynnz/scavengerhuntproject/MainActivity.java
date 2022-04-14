package com.ktynnz.scavengerhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ktynnz.scavengerhuntproject.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    View background;
    TextView question, correctAnswer, mAnswers;
    EditText answer;
    Button send, restart;
    ImageView heart;
    FloatingActionButton exit;
    Random rand = new Random();

    int num1, num2, numques = 0, correct = 0;

    int userAns;

    // Used to load the 'scavengerhuntproject' library on application startup.
    static {
        System.loadLibrary("scavengerhuntproject");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        //TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());

        overridePendingTransition(R.anim.fadein_transition, R.anim.fadeout_transition);
        //Hide action top action bar and bottom nav bar
        getSupportActionBar().hide();
        background = findViewById(R.id.main_bg);
        background.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        answer = findViewById(R.id.answer);
        question = findViewById(R.id.question);
        correctAnswer = findViewById(R.id.correctAns);
        send = findViewById(R.id.send);
        restart = findViewById(R.id.mHome);
        exit = findViewById(R.id.m_exit);
        mAnswers = findViewById(R.id.mAnswers);
        heart = findViewById(R.id.heart);

        //Hide buttons when page opens
        restart.setVisibility(View.INVISIBLE);
        mAnswers.setVisibility(View.INVISIBLE);
        heart.setVisibility(View.INVISIBLE);

        correctAnswer.setText("You need at least three correct answers to earn a life.");
        question();
    }

    public void send(View v) {
        numques++;

        //If user answers null it will accept it as 0
        if(answer.getText().toString().equals("")){
            userAns = 0;
        }
        else {
            userAns = Integer.parseInt(answer.getText().toString());
        }

        correctAnswer.setText(randomQuestion(num1, num2, userAns));
        answer.setText("");

        if (userAns == num1 + num2) {
            correct++;
        }

        //User gets to answer 5 questions
        if(numques >= 5)
        {
            send.setVisibility(View.INVISIBLE);
            answer.setVisibility(View.INVISIBLE);
            question.setVisibility(View.INVISIBLE);
            correctAnswer.setVisibility(View.INVISIBLE);
            exit.setVisibility(View.INVISIBLE);
            restart.setVisibility(View.VISIBLE);
            mAnswers.setVisibility(View.VISIBLE);

            //user only needs 3 correct answers to earn a life
            if (correct >= 3)
            {
                heart.setVisibility(View.VISIBLE);
                mAnswers.setText("You earned a life.");
            }
            else
            {
                mAnswers.setText("You did not earn a life. Please try again.");
            }
        }
        else
        {
            send.setVisibility(View.VISIBLE);
        }
        question();

    }

    public void mHome(View v)
    {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void exit(View v)
    {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void question()
    {
        num1 = rand.nextInt(9) + 1;
        num2 = rand.nextInt(9) + 1;
        question.setText(num1 + " + " + num2 + "=");
    }

    //public native String stringFromJNI();
    public native String randomQuestion(int num1, int num2, int ans);
}