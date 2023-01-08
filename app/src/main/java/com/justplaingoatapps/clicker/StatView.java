package com.justplaingoatapps.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.justplaingoatapps.hordeclicker.R;
import com.vstechlab.easyfonts.EasyFonts;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StatView extends Activity {

    private String TAG = "TAG";

    private boolean sound = false;

    private List<Stat> statList = new ArrayList<Stat>();

    private double totalZombies = 0;
    private double totalZombiesAllTime = 0;
    private double totalZps = 0;

    private double zombieStat = 0;
    private double currentZombieStat = 0;
    private int upgradeStat = 0;
    private double handMadeZombiesStat = 0;
    private int clickStat = 0;
    private int achStat = 0;
    private int eyeClicksStat = 0;

    private TextView title;

    private ImageButton close;

    //All Audio
    private SoundPool soundPool;
    private int soundExitClickID;
    private float actVolume, maxVolume, volume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_list_view);

        title = (TextView) findViewById(R.id.statTitleId);
        title.setTypeface(EasyFonts.captureIt(this));

        close = (ImageButton) findViewById(R.id.statCloseButtonId);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound == false) {
                    playExitClickSound();
                }
                Intent intent = new Intent(StatView.this,MainActivity.class);
                startActivity(intent);
            }
        });

        load();
        setupAudio();
        runAlways();

        populateStatList();
        populateListView();
    }

    private void setupAudio() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundExitClickID = soundPool.load(this,R.raw.exitsound,1);
    }

    private void playExitClickSound() {
        soundPool.play(soundExitClickID, volume, volume, 1, 0, 1f);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"Stat View onPause");
        save();
    }

    private void runAlways() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new MyAsyncTask().execute(totalZombies);
            }
        }, 0, 1000);
    }

    private void populateStatList() {
        statList.add(new Stat("Total Zombies (Ever)",zombieStat));
        statList.add(new Stat("Total Zombies (Current)",currentZombieStat));
        statList.add(new Stat("Total Upgrades",upgradeStat));
        statList.add(new Stat("Hand-Made Zombies",handMadeZombiesStat));
        statList.add(new Stat("Total Clicks",clickStat));
        statList.add(new Stat("Hordes Created",eyeClicksStat));
        statList.add(new Stat("Total Achievements", achStat));
    }

    private void populateListView() {
        ArrayAdapter<Stat> arrayAdapter = new MyListAdapter();
        ListView statListView = (ListView) findViewById(R.id.statListViewId);
        statListView.setAdapter(arrayAdapter);
    }

    private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        MainActivity.putDouble(editor,"totalZombies",totalZombies);
        MainActivity.putDouble(editor,"totalZombiesAllTime",totalZombiesAllTime);

        editor.commit();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences != null) {
            totalZombies = MainActivity.getDouble(sharedPreferences,"totalZombies",UpgradeView.DEFAULT);
            totalZombiesAllTime = MainActivity.getDouble(sharedPreferences, "totalZombiesAllTime", UpgradeView.DEFAULT);
            totalZps = MainActivity.getDouble(sharedPreferences,"totalZPS",UpgradeView.DEFAULT);
            sound = sharedPreferences.getBoolean("soundBut", false);

            zombieStat = MainActivity.getDouble(sharedPreferences, "totalZombiesAllTime", UpgradeView.DEFAULT);
            currentZombieStat = MainActivity.getDouble(sharedPreferences,"totalZombies",UpgradeView.DEFAULT);
            upgradeStat = sharedPreferences.getInt("totalUpgrades", UpgradeView.DEFAULT);
            handMadeZombiesStat = MainActivity.getDouble(sharedPreferences,"handMadeZombies", UpgradeView.DEFAULT);
            clickStat = sharedPreferences.getInt("totalClicks", UpgradeView.DEFAULT);
            achStat = sharedPreferences.getInt("achCount", UpgradeView.DEFAULT);
            eyeClicksStat = sharedPreferences.getInt("eyeClicks", UpgradeView.DEFAULT);
        }
    }

    private class MyListAdapter extends ArrayAdapter<Stat> {

        public MyListAdapter() {
            super(StatView.this,R.layout.activity_stat_view,statList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.activity_stat_view,parent,false);
            }

            Stat currentStat = statList.get(position);

            TextView nameTextView = (TextView) view.findViewById(R.id.statDescId);
            nameTextView.setText(currentStat.getStatDesc());
            nameTextView.setTypeface(EasyFonts.walkwayBold(getContext()));

            TextView number = (TextView) view.findViewById(R.id.statNumId);
            if(currentStat.getStatDesc().equals("Total Achievements")) {
                number.setText(MainActivity.df.format(currentStat.getStatNum()) + "/" + "60");
            } else {
                String stringNum = MainActivity.df.format(currentStat.getStatNum());
                double doubleNum = Double.valueOf(stringNum);
                number.setText(NumberFormat.getInstance().format(doubleNum));
            }

            return view;
        }
    }

    class MyAsyncTask extends AsyncTask<Double,Double,Double> {
        @Override
        protected Double doInBackground(Double... params) {
            totalZombies = totalZombies + totalZps;
            totalZombiesAllTime = totalZombiesAllTime + totalZps;
            return totalZombies;
        }
    }

}
