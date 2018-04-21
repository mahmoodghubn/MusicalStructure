package com.example.android.musicalstructure;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayActivity extends Activity {

    private MediaPlayer mediaPlayer;
    public TextView songName, duration;
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the layout of the Activity
        setContentView(R.layout.activity_play);

        //initialize views
        initializeViews();
        ImageButton play =(ImageButton) findViewById(R.id.media_play);
        ImageButton pause =(ImageButton) findViewById(R.id.media_pause);
        ImageButton forward =(ImageButton) findViewById(R.id.media_ff);
        ImageButton rewind =(ImageButton) findViewById(R.id.media_rew);
        ImageButton back =(ImageButton) findViewById(R.id.back);





        play.setOnClickListener(new TextView.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        timeElapsed = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress((int) timeElapsed);

                    }
                });
        pause.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        forward.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                //check if we can go forward at forwardTime seconds before song endes
                if ((timeElapsed + forwardTime) <= finalTime)
                    timeElapsed = timeElapsed + backwardTime;

                    //seek to the exact second of the track
                    mediaPlayer.seekTo((int) timeElapsed);

            }
        });
        rewind.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                timeElapsed = timeElapsed - backwardTime;

                //seek to the exact second of the track
                mediaPlayer.seekTo((int) timeElapsed);

            }
        });
        back.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent mainIntent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
        super.onBackPressed();
    }

    public void initializeViews() {
        songName = (TextView) findViewById(R.id.songName);
        duration = (TextView) findViewById(R.id.songDuration);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int position = extras.getInt("myKey");
        switch (position) {
            case 0:
                mediaPlayer = MediaPlayer.create(this, R.raw.ahibinibelaukad);
                songName.setText("ahbene.mp3");

                break;
            case 1:
                mediaPlayer = MediaPlayer.create(this, R.raw.akhasmakah);
                songName.setText("akasmak.mp3");

                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, R.raw.badekzalanymeni);
                songName.setText("badekzalanymeni.mp3");
                break;

            case 3:
                mediaPlayer = MediaPlayer.create(this, R.raw.habibi);
                songName.setText("habibi.mp3");
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(this, R.raw.hkayetachek);
                songName.setText("Hkayet-Achek.mp3");
                break;
            case 5:
                mediaPlayer = MediaPlayer.create(this, R.raw.intagherhom);
                songName.setText("Inta-Gherhom.mp3");
                break;
            case 6:
                mediaPlayer = MediaPlayer.create(this, R.raw.mabyenshaba);
                songName.setText("mabeshea.mp3");
                break;
            case 7:
                mediaPlayer = MediaPlayer.create(this, R.raw.walaalabalo);
                songName.setText("Wala 3ala Balo.mp3");
                break;


        }
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        finalTime = mediaPlayer.getDuration();
        seekBar.setMax((int) finalTime);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mediaPlayer.seekTo(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        durationHandler.postDelayed(updateSeekBarTime, 100);
    }//end initializeViews method




    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            //get current position
            timeElapsed = mediaPlayer.getCurrentPosition();
            //set seekbar progress
            seekBar.setProgress((int) timeElapsed);
            //set time remaing
            double timeRemaining = finalTime - timeElapsed;
            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);
        }
    };
}
