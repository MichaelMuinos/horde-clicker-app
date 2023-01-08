package com.justplaingoatapps.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.justplaingoatapps.hordeclicker.R;

public class Start extends Activity {

    private String TAG = "TAG";

    private ImageButton startButton;
    private ImageButton creditButton;
    private ImageButton resetButton;

    //All Audio
    private SoundPool soundPool;
    private int soundButtonClickID;
    private float actVolume, maxVolume, volume;
    private AudioManager audioManager;

    private boolean sound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        load();

        setupAudio();

        startButton = (ImageButton) findViewById(R.id.startButtonId);
        creditButton = (ImageButton) findViewById(R.id.creditsButtonId);
        resetButton = (ImageButton) findViewById(R.id.resetButtonId);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound == false) {
                    playButtonClickSound();
                }
                Intent intent = new Intent(Start.this, MainActivity.class);
                startActivity(intent);
            }
        });

        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound == false) {
                    playButtonClickSound();
                }
                Intent intent = new Intent(Start.this,CreditView.class);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound == false) {
                    playButtonClickSound();
                }
                startActivity(new Intent(Start.this,Reset.class));
            }
        });


    }

    private void setupAudio() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundButtonClickID = soundPool.load(this,R.raw.buttonclick,1);
    }

    private void playButtonClickSound() {
        soundPool.play(soundButtonClickID,volume,volume,1,0,1f);
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences != null) {
            sound = sharedPreferences.getBoolean("soundBut", false);
        }
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

}
