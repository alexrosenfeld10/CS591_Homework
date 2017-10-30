package com.example.megan.w7_p3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView vidView = (VideoView)findViewById(R.id.videoView);

        Uri vidUri = Uri.parse("android.resource://" + getPackageName() + "/raw/khaaaaan");

        vidView.setVideoURI(vidUri);

        vidView.start();

        MediaController vidControl = new MediaController(this);

        vidView.setMediaController(vidControl);
    }
}
