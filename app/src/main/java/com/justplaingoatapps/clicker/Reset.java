package com.justplaingoatapps.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.justplaingoatapps.hordeclicker.R;
import com.vstechlab.easyfonts.EasyFonts;

public class Reset extends Activity {

    private String TAG = "TAG";

    private TextView question;
    private TextView question2;
    private ImageButton yes;
    private ImageButton no;

    private boolean noReset = false;

    //All Audio
    private SoundPool soundPool;
    private int soundButtonClickID;
    private float actVolume, maxVolume, volume;
    private AudioManager audioManager;

    private boolean sound = false;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8),(int) (height * .2));

        question = (TextView) findViewById(R.id.questionId);
        question.setTypeface(EasyFonts.walkwayBold(this));

        question2 = (TextView) findViewById(R.id.question2Id);
        question2.setTypeface(EasyFonts.walkwayBold(this));

        setupAudio();
        load();

        yes = (ImageButton) findViewById(R.id.yesButtonId);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound == false) {
                    playButtonClickSound();
                }
                DeleteFile.getInstance().clearApplicationData();
                SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                MainActivity.clawAch1 = null;
                UpgradeView.checkUpgrade = false;

                onBackPressed();
            }
        });

        no = (ImageButton) findViewById(R.id.noButtonId);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound == false) {
                    playButtonClickSound();
                }

                noReset = true;
                save();

                onBackPressed();
            }
        });
    }

    private void playButtonClickSound() {
        soundPool.play(soundButtonClickID, volume, volume, 1, 0, 1f);
    }

    private void setupAudio() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundButtonClickID = soundPool.load(this,R.raw.exitsound,1);
    }

    private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("noReset",noReset);

        editor.commit();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences != null) {
            sound = sharedPreferences.getBoolean("soundBut", false);
        }
    }

}
