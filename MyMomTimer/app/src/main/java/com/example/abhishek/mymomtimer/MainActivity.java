package com.example.abhishek.mymomtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView mnumber = (TextView) findViewById(R.id.mnumber);
        final TextView result = (TextView) findViewById(R.id.done);

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                mnumber.setText("Time: "+String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                result.setText("DONE!!!!");
                mnumber.setText("Time: 0");
            }
        }.start();
    }
}
