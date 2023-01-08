package com.justplaingoatapps.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.justplaingoatapps.hordeclicker.R;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AchievementView extends Activity {

    private String TAG = "TAG";

    private double totalZombies = 0;
    private double totalZombiesAllTime = 0;
    private double totalZps = 0;

    private List<Achievement> achievementList = new ArrayList<Achievement>();

    private TextView title;

    private boolean sound = false;

    private ImageButton close;

    private Achievement clawAch1;
    private Achievement clawAch2;
    private Achievement clawAch3;
    private Achievement biteAch1;
    private Achievement biteAch2;
    private Achievement biteAch3;
    private Achievement speedAch1;
    private Achievement speedAch2;
    private Achievement speedAch3;
    private Achievement smellAch1;
    private Achievement smellAch2;
    private Achievement smellAch3;
    private Achievement jumpAch1;
    private Achievement jumpAch2;
    private Achievement jumpAch3;
    private Achievement acidAch1;
    private Achievement acidAch2;
    private Achievement acidAch3;
    private Achievement explodeAch1;
    private Achievement explodeAch2;
    private Achievement explodeAch3;
    private Achievement bulletAch1;
    private Achievement bulletAch2;
    private Achievement bulletAch3;
    private Achievement pounceAch1;
    private Achievement pounceAch2;
    private Achievement pounceAch3;
    private Achievement chargeAch1;
    private Achievement chargeAch2;
    private Achievement chargeAch3;
    private Achievement gangUpAch1;
    private Achievement gangUpAch2;
    private Achievement gangUpAch3;
    private Achievement stealthAch1;
    private Achievement stealthAch2;
    private Achievement stealthAch3;
    private Achievement clicksOnEarthAch1;
    private Achievement clicksOnEarthAch2;
    private Achievement clicksOnEarthAch3;
    private Achievement totalZombieAch1;
    private Achievement totalZombieAch2;
    private Achievement totalZombieAch3;
    private Achievement totalZombieAllTimeAch1;
    private Achievement totalZombieAllTimeAch2;
    private Achievement totalZombieAllTimeAch3;
    private Achievement totalZpsAch1;
    private Achievement totalZpsAch2;
    private Achievement totalZpsAch3;
    private Achievement totalPerClickCountAch;
    private Achievement totalUpgradeCountAch1;
    private Achievement totalUpgradeCountAch2;
    private Achievement totalUpgradeCountAch3;
    private Achievement readCreditsAch;
    private Achievement hordeAch1;
    private Achievement hordeAch2;
    private Achievement hordeAch3;
    private Achievement startAppAch;
    private Achievement secretAch;
    private Achievement secretAch2;
    private Achievement getAllAch;

    //All Audio
    private SoundPool soundPool;
    private int soundExitClickID;
    private float actVolume, maxVolume, volume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_list_view);

        title = (TextView) findViewById(R.id.achTitleId);
        title.setTypeface(EasyFonts.captureIt(this));

        close = (ImageButton) findViewById(R.id.achCloseButtonId);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound == false) {
                    playExitClickSound();
                }
                Intent intent = new Intent(AchievementView.this,MainActivity.class);
                startActivity(intent);
            }
        });

        load();
        setupAudio();
        runAlways();

        populateAchievementList();
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
        soundPool.play(soundExitClickID,volume,volume,1,0,1f);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        MainActivity.putDouble(editor,"totalZombies",totalZombies);
        MainActivity.putDouble(editor,"totalZombiesAllTime",totalZombiesAllTime);

        editor.commit();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        totalZombies = MainActivity.getDouble(sharedPreferences,"totalZombies",UpgradeView.DEFAULT);
        totalZombiesAllTime = MainActivity.getDouble(sharedPreferences,"totalZombiesAllTime",UpgradeView.DEFAULT);
        totalZps = MainActivity.getDouble(sharedPreferences,"totalZPS",UpgradeView.DEFAULT);
        sound = sharedPreferences.getBoolean("soundBut", false);

        Gson gson = new Gson();

        String jsonClawAch1 = sharedPreferences.getString("clawAch1","");
        String jsonClawAch2 = sharedPreferences.getString("clawAch2", "");
        String jsonClawAch3 = sharedPreferences.getString("clawAch3", "");
        String jsonBiteAch1 = sharedPreferences.getString("biteAch1", "");
        String jsonBiteAch2 = sharedPreferences.getString("biteAch2", "");
        String jsonBiteAch3 = sharedPreferences.getString("biteAch3", "");
        String jsonSpeedAch1 = sharedPreferences.getString("speedAch1", "");
        String jsonSpeedAch2 = sharedPreferences.getString("speedAch2", "");
        String jsonSpeedAch3 = sharedPreferences.getString("speedAch3", "");
        String jsonSmellAch1 = sharedPreferences.getString("smellAch1", "");
        String jsonSmellAch2 = sharedPreferences.getString("smellAch2", "");
        String jsonSmellAch3 = sharedPreferences.getString("smellAch3", "");
        String jsonJumpAch1 = sharedPreferences.getString("jumpAch1", "");
        String jsonJumpAch2 = sharedPreferences.getString("jumpAch2", "");
        String jsonJumpAch3 = sharedPreferences.getString("jumpAch3", "");
        String jsonAcidAch1 = sharedPreferences.getString("acidAch1", "");
        String jsonAcidAch2 = sharedPreferences.getString("acidAch2", "");
        String jsonAcidAch3 = sharedPreferences.getString("acidAch3", "");
        String jsonExplodeAch1 = sharedPreferences.getString("explodeAch1", "");
        String jsonExplodeAch2 = sharedPreferences.getString("explodeAch2", "");
        String jsonExplodeAch3 = sharedPreferences.getString("explodeAch3", "");
        String jsonBulletAch1 = sharedPreferences.getString("bulletAch1", "");
        String jsonBulletAch2 = sharedPreferences.getString("bulletAch2", "");
        String jsonBulletAch3 = sharedPreferences.getString("bulletAch3", "");
        String jsonPounceAch1 = sharedPreferences.getString("pounceAch1", "");
        String jsonPounceAch2 = sharedPreferences.getString("pounceAch2", "");
        String jsonPounceAch3 = sharedPreferences.getString("pounceAch3", "");
        String jsonChargeAch1 = sharedPreferences.getString("chargeAch1", "");
        String jsonChargeAch2 = sharedPreferences.getString("chargeAch2", "");
        String jsonChargeAch3 = sharedPreferences.getString("chargeAch3", "");
        String jsonGangUpAch1 = sharedPreferences.getString("gangUpAch1", "");
        String jsonGangUpAch2 = sharedPreferences.getString("gangUpAch2", "");
        String jsonGangUpAch3 = sharedPreferences.getString("gangUpAch3", "");
        String jsonStealthAch1 = sharedPreferences.getString("stealthAch1", "");
        String jsonStealthAch2 = sharedPreferences.getString("stealthAch2", "");
        String jsonStealthAch3 = sharedPreferences.getString("stealthAch3", "");
        String jsonClicksOnEarthAch1 = sharedPreferences.getString("clicksOnEarthAch1", "");
        String jsonClicksOnEarthAch2 = sharedPreferences.getString("clicksOnEarthAch2", "");
        String jsonClicksOnEarthAch3 = sharedPreferences.getString("clicksOnEarthAch3", "");
        String jsonTotalZombieAch1 = sharedPreferences.getString("totalZombieAch1", "");
        String jsonTotalZombieAch2 = sharedPreferences.getString("totalZombieAch2", "");
        String jsonTotalZombieAch3 = sharedPreferences.getString("totalZombieAch3", "");
        String jsonTotalZombieAllTimeAch1 = sharedPreferences.getString("totalZombieAllTimeAch1", "");
        String jsonTotalZombieAllTimeAch2 = sharedPreferences.getString("totalZombieAllTimeAch2", "");
        String jsonTotalZombieAllTimeAch3 = sharedPreferences.getString("totalZombieAllTimeAch3", "");
        String jsonTotalZpsAch1 = sharedPreferences.getString("totalZpsAch1", "");
        String jsonTotalZpsAch2 = sharedPreferences.getString("totalZpsAch2", "");
        String jsonTotalZpsAch3 = sharedPreferences.getString("totalZpsAch3", "");
        String jsonTotalPerClickCountAch = sharedPreferences.getString("totalPerClickCountAch", "");
        String jsonTotalUpgradeCountAch1 = sharedPreferences.getString("totalUpgradeCountAch1", "");
        String jsonTotalUpgradeCountAch2 = sharedPreferences.getString("totalUpgradeCountAch2", "");
        String jsonTotalUpgradeCountAch3 = sharedPreferences.getString("totalUpgradeCountAch3", "");
        String jsonReadCreditsAch = sharedPreferences.getString("readCreditsAch", "");
        String jsonHordeAch1 = sharedPreferences.getString("hordeAch1", "");
        String jsonHordeAch2 = sharedPreferences.getString("hordeAch2", "");
        String jsonHordeAch3 = sharedPreferences.getString("hordeAch3", "");
        String jsonStartAppAch = sharedPreferences.getString("startAppAch", "");
        String jsonSecretAch = sharedPreferences.getString("secretAch", "");
        String jsonSecretAch2 = sharedPreferences.getString("secretAch2", "");
        String jsonGetAllAch = sharedPreferences.getString("getAllAch", "");

        clawAch1 = gson.fromJson(jsonClawAch1,Achievement.class);
        clawAch2 = gson.fromJson(jsonClawAch2,Achievement.class);
        clawAch3 = gson.fromJson(jsonClawAch3,Achievement.class);
        biteAch1 = gson.fromJson(jsonBiteAch1,Achievement.class);
        biteAch2 = gson.fromJson(jsonBiteAch2,Achievement.class);
        biteAch3 = gson.fromJson(jsonBiteAch3,Achievement.class);
        speedAch1 = gson.fromJson(jsonSpeedAch1,Achievement.class);
        speedAch2 = gson.fromJson(jsonSpeedAch2,Achievement.class);
        speedAch3 = gson.fromJson(jsonSpeedAch3,Achievement.class);
        smellAch1 = gson.fromJson(jsonSmellAch1,Achievement.class);
        smellAch2 = gson.fromJson(jsonSmellAch2,Achievement.class);
        smellAch3 = gson.fromJson(jsonSmellAch3,Achievement.class);
        jumpAch1 = gson.fromJson(jsonJumpAch1,Achievement.class);
        jumpAch2 = gson.fromJson(jsonJumpAch2,Achievement.class);
        jumpAch3 = gson.fromJson(jsonJumpAch3,Achievement.class);
        acidAch1 = gson.fromJson(jsonAcidAch1,Achievement.class);
        acidAch2 = gson.fromJson(jsonAcidAch2,Achievement.class);
        acidAch3 = gson.fromJson(jsonAcidAch3,Achievement.class);
        explodeAch1 = gson.fromJson(jsonExplodeAch1,Achievement.class);
        explodeAch2 = gson.fromJson(jsonExplodeAch2,Achievement.class);
        explodeAch3 = gson.fromJson(jsonExplodeAch3,Achievement.class);
        bulletAch1 = gson.fromJson(jsonBulletAch1,Achievement.class);
        bulletAch2 = gson.fromJson(jsonBulletAch2,Achievement.class);
        bulletAch3 = gson.fromJson(jsonBulletAch3,Achievement.class);
        pounceAch1 = gson.fromJson(jsonPounceAch1,Achievement.class);
        pounceAch2 = gson.fromJson(jsonPounceAch2,Achievement.class);
        pounceAch3 = gson.fromJson(jsonPounceAch3,Achievement.class);
        chargeAch1 = gson.fromJson(jsonChargeAch1,Achievement.class);
        chargeAch2 = gson.fromJson(jsonChargeAch2,Achievement.class);
        chargeAch3 = gson.fromJson(jsonChargeAch3,Achievement.class);
        gangUpAch1 = gson.fromJson(jsonGangUpAch1,Achievement.class);
        gangUpAch2 = gson.fromJson(jsonGangUpAch2,Achievement.class);
        gangUpAch3 = gson.fromJson(jsonGangUpAch3,Achievement.class);
        stealthAch1 = gson.fromJson(jsonStealthAch1,Achievement.class);
        stealthAch2 = gson.fromJson(jsonStealthAch2,Achievement.class);
        stealthAch3 = gson.fromJson(jsonStealthAch3,Achievement.class);
        clicksOnEarthAch1 = gson.fromJson(jsonClicksOnEarthAch1,Achievement.class);
        clicksOnEarthAch2 = gson.fromJson(jsonClicksOnEarthAch2,Achievement.class);
        clicksOnEarthAch3 = gson.fromJson(jsonClicksOnEarthAch3,Achievement.class);
        totalZombieAch1 = gson.fromJson(jsonTotalZombieAch1,Achievement.class);
        totalZombieAch2 = gson.fromJson(jsonTotalZombieAch2,Achievement.class);
        totalZombieAch3 = gson.fromJson(jsonTotalZombieAch3,Achievement.class);
        totalZombieAllTimeAch1 = gson.fromJson(jsonTotalZombieAllTimeAch1,Achievement.class);
        totalZombieAllTimeAch2 = gson.fromJson(jsonTotalZombieAllTimeAch2,Achievement.class);
        totalZombieAllTimeAch3 = gson.fromJson(jsonTotalZombieAllTimeAch3,Achievement.class);
        totalZpsAch1 = gson.fromJson(jsonTotalZpsAch1,Achievement.class);
        totalZpsAch2 = gson.fromJson(jsonTotalZpsAch2,Achievement.class);
        totalZpsAch3 = gson.fromJson(jsonTotalZpsAch3,Achievement.class);
        totalPerClickCountAch = gson.fromJson(jsonTotalPerClickCountAch,Achievement.class);
        totalUpgradeCountAch1 = gson.fromJson(jsonTotalUpgradeCountAch1,Achievement.class);
        totalUpgradeCountAch2 = gson.fromJson(jsonTotalUpgradeCountAch2,Achievement.class);
        totalUpgradeCountAch3 = gson.fromJson(jsonTotalUpgradeCountAch3,Achievement.class);
        readCreditsAch = gson.fromJson(jsonReadCreditsAch,Achievement.class);
        hordeAch1 = gson.fromJson(jsonHordeAch1,Achievement.class);
        hordeAch2 = gson.fromJson(jsonHordeAch2,Achievement.class);
        hordeAch3 = gson.fromJson(jsonHordeAch3,Achievement.class);
        startAppAch = gson.fromJson(jsonStartAppAch,Achievement.class);
        secretAch = gson.fromJson(jsonSecretAch,Achievement.class);
        secretAch2 = gson.fromJson(jsonSecretAch2,Achievement.class);
        getAllAch = gson.fromJson(jsonGetAllAch,Achievement.class);
    }

    private void populateAchievementList() {
        achievementList.add(startAppAch);
        achievementList.add(clawAch1);
        achievementList.add(clawAch2);
        achievementList.add(clawAch3);
        achievementList.add(biteAch1);
        achievementList.add(biteAch2);
        achievementList.add(biteAch3);
        achievementList.add(speedAch1);
        achievementList.add(speedAch2);
        achievementList.add(speedAch3);
        achievementList.add(smellAch1);
        achievementList.add(smellAch2);
        achievementList.add(smellAch3);
        achievementList.add(jumpAch1);
        achievementList.add(jumpAch2);
        achievementList.add(jumpAch3);
        achievementList.add(acidAch1);
        achievementList.add(acidAch2);
        achievementList.add(acidAch3);
        achievementList.add(explodeAch1);
        achievementList.add(explodeAch2);
        achievementList.add(explodeAch3);
        achievementList.add(pounceAch1);
        achievementList.add(pounceAch2);
        achievementList.add(pounceAch3);
        achievementList.add(chargeAch1);
        achievementList.add(chargeAch2);
        achievementList.add(chargeAch3);
        achievementList.add(bulletAch1);
        achievementList.add(bulletAch2);
        achievementList.add(bulletAch3);
        achievementList.add(gangUpAch1);
        achievementList.add(gangUpAch2);
        achievementList.add(gangUpAch3);
        achievementList.add(stealthAch1);
        achievementList.add(stealthAch2);
        achievementList.add(stealthAch3);
        achievementList.add(hordeAch1);
        achievementList.add(hordeAch2);
        achievementList.add(hordeAch3);
        achievementList.add(clicksOnEarthAch1);
        achievementList.add(clicksOnEarthAch2);
        achievementList.add(clicksOnEarthAch3);
        achievementList.add(totalZombieAch1);
        achievementList.add(totalZombieAch2);
        achievementList.add(totalZombieAch3);
        achievementList.add(totalZombieAllTimeAch1);
        achievementList.add(totalZombieAllTimeAch2);
        achievementList.add(totalZombieAllTimeAch3);
        achievementList.add(totalZpsAch1);
        achievementList.add(totalZpsAch2);
        achievementList.add(totalZpsAch3);
        achievementList.add(totalPerClickCountAch);
        achievementList.add(totalUpgradeCountAch1);
        achievementList.add(totalUpgradeCountAch2);
        achievementList.add(totalUpgradeCountAch3);
        achievementList.add(readCreditsAch);
        achievementList.add(secretAch);
        achievementList.add(secretAch2);
        achievementList.add(getAllAch);
    }

    private void populateListView() {
        ArrayAdapter<Achievement> arrayAdapter = new MyListAdapter();
        ListView achievementListView = (ListView) findViewById(R.id.achListViewId);
        achievementListView.setAdapter(arrayAdapter);
    }

    private class MyListAdapter extends ArrayAdapter<Achievement> {

        public MyListAdapter() {
            super(AchievementView.this,R.layout.activity_achievement_view,achievementList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.activity_achievement_view,parent,false);
            }

            Achievement currentAchievement = achievementList.get(position);

            ImageView invisImageView = (ImageView) view.findViewById(R.id.invisCheckId);
            invisImageView.setImageResource(currentAchievement.getCheckPic());
            if(currentAchievement.isVisible() == false) {
                invisImageView.setVisibility(View.INVISIBLE);
            } else {
                invisImageView.setVisibility(View.VISIBLE);
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.achImageView);
            imageView.setImageResource(currentAchievement.getAchievementPic());

            TextView nameTextView = (TextView) view.findViewById(R.id.achieveNameId);
            nameTextView.setText(currentAchievement.getAchievementName());
            nameTextView.setTypeface(EasyFonts.walkwayBold(getContext()));

            TextView descrTextView = (TextView) view.findViewById(R.id.achieveDescripId);
            descrTextView.setText(currentAchievement.getAchievementDescription());

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
