package com.example.abhishek.myvideoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. get video view
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        // 2. point to video resource
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.introvideo);
        // 3. run it
        videoView.start();
    }
}
