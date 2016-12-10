package com.example.student.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button pauseButtonVar, playButtonVar, rewindButtonVar, fastFowardButtonVar, stopButtonVar;
    pubic TextView currentTimeViewVar, endTimeViewVar;
    public MediaPlayer stopPlayer;
    public double endTimeMS;
    public myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playButtonVar = (Button) findViewById(R.id.playButton);
        pauseButtonVar = (Button) findViewById(R.id.pauseButton);
        stopButtonVar = (Button) findViewById(R.id.stopButton);
        rewindButtonVar = (Button) findViewById(R.id.rewindButton);
        fastFowardButtonVar = (Button) findViewById(R.id.fastFowardButton);

        currentTimeViewVar = (TextView) findViewById(R.id.currentTimeView);
        endTimeViewVar= (TextView) findViewById(R.id.endTimeView);

        songPlayer = MediaPlayer.create(this, R.raw.song);
        endTimeMS = songPlayer.getDuration();
        int endTimeMin = (int) endTime/1000/60;
        int endTimeS = (int) (endTimeMS/1000) % 60;
        endTimeViewVar.setText(endTimeMin + " min," + endTimeS + " sec");

        pauseButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(false);

        myHandler.postDelayed(UpdateSongTime, 100);

    }

    public void playSong(View view){
        Toast.makeText(getApplicationContext(), "Playing song", Toast.LENGTH_SHORT).show();
        pauseButtonVar.setEnabled(true);
        playButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(true);
        songPlayer.start();
    }
    public void pauseSong(View view){
        Toast.makeText(getApplicationContext(), "Pausing song", Toast.LENGTH_SHORT).show();
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        songPlayer.pause();
    }

    public void stopSong(View view){
        Toast.makeText(getApplicationContext(), "Stopping song", Toast.LENGTH_SHORT).show();
        stopButtonVar.setEnabled(false);
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        songPlayer.seekTo(0);
        songPlayer.pause();
    }

    public void fastFowardSong(View view){
        Toast.makeText(getApplicationContext(), "Foward 5 second", Toast.LENGTH_SHORT).show();
        if(currentTimeMS < endTimeMS-5000){
        songPlayer.seekTo( (int)currentTimeMS +5000);}
    }

    public void rewindSong(View view){
        Toast.makeText(getApplicationContext(), "Back 5 second", Toast.LENGTH_SHORT).show();
        if(currentTimeMS >5000){
        songPlayer.seekTo( (int)currentTimeMS -5000);}
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTimeMS = songPlayer.getCurrentPostiton();
            int currentTimeMin = (int) currentTimeMS/1000/60;
            int currentTimeS = (int) (currentTimeMS/1000) % 60;
            currentTimeViewVar.setText(currentTimeMin + " min, " + currentTimeS + " sec");


            myHandler.postDelayed(this, 100);
        }
    };
}
