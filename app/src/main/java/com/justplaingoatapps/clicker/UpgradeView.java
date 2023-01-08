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


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UpgradeView extends Activity {

    private String TAG = "TAG";

    public static final int DEFAULT = 0;

    private List<Upgrade> upgradeList = new ArrayList<Upgrade>();

    private ImageButton closeButton;

    private TextView title;

    private double totalZombies = 0;
    private double totalZps = 0;
    private int countPerClick = 0;
    private int totalUpgrades = 0;
    private double totalZombiesAllTime = 0;

    public static Upgrade claw;
    public static Upgrade bite;
    public static Upgrade speedIncrease;
    public static Upgrade enhanceSmell;
    public static Upgrade jumpIncrease;
    public static Upgrade acid;
    public static Upgrade pounce;
    public static Upgrade bulletProof;
    public static Upgrade explode;
    public static Upgrade charge;
    public static Upgrade gangUp;
    public static Upgrade stealth;

    public static boolean checkUpgrade = false;

    //All Audio
    private SoundPool soundPool;
    private int soundButtonClickID;
    private int soundExitClickID;
    private float actVolume, maxVolume, volume;
    private AudioManager audioManager;

    private boolean sound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_list_view);

        title = (TextView) findViewById(R.id.upgradeName);
        title.setTypeface(EasyFonts.captureIt(this));

        load();
        setupAudio();
        runAlways();

        onCloseButtonClick();
        populateUpgradeList();
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
        soundButtonClickID = soundPool.load(this,R.raw.buttonclick,1);
        soundExitClickID = soundPool.load(this,R.raw.exitsound,1);
    }

    private void playButtonSoundClick() {
        soundPool.play(soundButtonClickID,volume,volume,1,0,1f);
    }

    private void playExitClickSound() {
        soundPool.play(soundExitClickID, volume, volume, 1, 0, 1f);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    private void runAlways() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new MyAsyncTask().execute(totalZombies);
            }
        }, 0, 1000);
    }

    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String jsonClaw = gson.toJson(claw);
        String jsonBite = gson.toJson(bite);
        String jsonSpeed = gson.toJson(speedIncrease);
        String jsonSmell = gson.toJson(enhanceSmell);
        String jsonJump = gson.toJson(jumpIncrease);
        String jsonAcid = gson.toJson(acid);
        String jsonPounce = gson.toJson(pounce);
        String jsonBulletProof = gson.toJson(bulletProof);
        String jsonExplode = gson.toJson(explode);
        String jsonCharge = gson.toJson(charge);
        String jsonGangUp = gson.toJson(gangUp);
        String jsonStealth = gson.toJson(stealth);

        editor.putString("clawObject", jsonClaw);
        editor.putString("biteObject", jsonBite);
        editor.putString("speedObject", jsonSpeed);
        editor.putString("smellObject", jsonSmell);
        editor.putString("jumpObject", jsonJump);
        editor.putString("acidObject", jsonAcid);
        editor.putString("bulletProofObject", jsonBulletProof);
        editor.putString("pounceObject", jsonPounce);
        editor.putString("explodeObject", jsonExplode);
        editor.putString("chargeObject", jsonCharge);
        editor.putString("gangUpObject", jsonGangUp);
        editor.putString("stealthObject", jsonStealth);

        MainActivity.putDouble(editor, "totalZombies", totalZombies);
        MainActivity.putDouble(editor, "totalZPS", totalZps);
        MainActivity.putDouble(editor, "totalZombiesAllTime", totalZombiesAllTime);
        editor.putInt("countPerClick", countPerClick);
        editor.putInt("totalUpgrades", totalUpgrades);

        editor.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences != null) {
            totalZombies = MainActivity.getDouble(sharedPreferences, "totalZombies",DEFAULT);
            totalZps = MainActivity.getDouble(sharedPreferences, "totalZPS",DEFAULT);
            totalZombiesAllTime = MainActivity.getDouble(sharedPreferences, "totalZombiesAllTime", DEFAULT);
            countPerClick = sharedPreferences.getInt("countPerClick", 1);
            totalUpgrades = sharedPreferences.getInt("totalUpgrades", DEFAULT);
            sound = sharedPreferences.getBoolean("soundBut", false);

            Gson gson = new Gson();

            String jsonClaw = sharedPreferences.getString("clawObject", "");
            String jsonBite = sharedPreferences.getString("biteObject", "");
            String jsonSpeed = sharedPreferences.getString("speedObject", "");
            String jsonSmell = sharedPreferences.getString("smellObject", "");
            String jsonJump = sharedPreferences.getString("jumpObject", "");
            String jsonAcid = sharedPreferences.getString("acidObject", "");
            String jsonBulletProof = sharedPreferences.getString("bulletProofObject", "");
            String jsonPounce = sharedPreferences.getString("pounceObject", "");
            String jsonExplode = sharedPreferences.getString("explodeObject", "");
            String jsonCharge = sharedPreferences.getString("chargeObject", "");
            String jsonGangUp = sharedPreferences.getString("gangUpObject", "");
            String jsonStealth = sharedPreferences.getString("stealthObject", "");

            claw = gson.fromJson(jsonClaw, Upgrade.class);
            bite = gson.fromJson(jsonBite, Upgrade.class);
            speedIncrease = gson.fromJson(jsonSpeed, Upgrade.class);
            enhanceSmell = gson.fromJson(jsonSmell, Upgrade.class);
            bulletProof = gson.fromJson(jsonBulletProof, Upgrade.class);
            jumpIncrease = gson.fromJson(jsonJump, Upgrade.class);
            acid = gson.fromJson(jsonAcid, Upgrade.class);
            pounce = gson.fromJson(jsonPounce, Upgrade.class);
            explode = gson.fromJson(jsonExplode, Upgrade.class);
            charge = gson.fromJson(jsonCharge, Upgrade.class);
            gangUp = gson.fromJson(jsonGangUp, Upgrade.class);
            stealth = gson.fromJson(jsonStealth, Upgrade.class);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    private void onCloseButtonClick() {
        closeButton = (ImageButton) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound == false) {
                    playExitClickSound();
                }
                Intent intent = new Intent(UpgradeView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateUpgradeList() {
        upgradeList.add(claw);
        upgradeList.add(bite);
        upgradeList.add(jumpIncrease);
        upgradeList.add(speedIncrease);
        upgradeList.add(enhanceSmell);
        upgradeList.add(acid);
        upgradeList.add(pounce);
        upgradeList.add(explode);
        upgradeList.add(charge);
        upgradeList.add(bulletProof);
        upgradeList.add(gangUp);
        upgradeList.add(stealth);

        checkUpgrade = true;
    }

    private void populateListView() {
        ArrayAdapter<Upgrade> adapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void subtractUpgradeFromTotal(double cost) {
        totalZombies = totalZombies - cost;
    }

    private void addZPSTotal(double zps) {
        totalZps = totalZps + zps;
    }

    private void addClickTotal(int clicks) {
        countPerClick = countPerClick + clicks;
    }

    private void upgradeData(Upgrade upgrade, int clicks, double cost) {
        if(upgrade.getUpgradeName().equals("Claw")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Bite")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Super Speed")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Strong Smell")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("High Jump")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Acidic Acid")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Pounce")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Bullet-Proof")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Exploding Limbs")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Charge")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else if(upgrade.getUpgradeName().equals("Gang Up")) {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        } else {
            upgrade.setNumberOfClicksOnUpgrade(clicks);
            upgrade.setCostOfUpgrade(cost);
        }
    }

    private class MyListAdapter extends ArrayAdapter<Upgrade> {

        public MyListAdapter() {
            super(UpgradeView.this,R.layout.activity_upgrade_view,upgradeList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.activity_upgrade_view,parent,false);
            }

            final Upgrade currentUpgrade = upgradeList.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.testImageView);
            imageView.setImageResource(currentUpgrade.getUpgradePicId());

            TextView textView = (TextView) itemView.findViewById(R.id.upgradeName);
            textView.setText(currentUpgrade.getUpgradeName());
            textView.setTypeface(EasyFonts.walkwayBold(getContext()));

            final TextView textView2 = (TextView) itemView.findViewById(R.id.upgradeCost);
            String stringNum = MainActivity.df.format(currentUpgrade.getCostOfUpgrade());
            double doubleNum = Double.valueOf(stringNum);
            textView2.setText(NumberFormat.getInstance().format(doubleNum));

            final TextView textView3 = (TextView) itemView.findViewById(R.id.upgradeClick);
            textView3.setText(MainActivity.df.format(currentUpgrade.getNumberOfClicksOnUpgrade()));

            TextView textView4 = (TextView) itemView.findViewById(R.id.zpsId);
            String stringNumZps = MainActivity.df.format(currentUpgrade.getZombiesPerSecond());
            double doubleNumZps = Double.valueOf(stringNumZps);
            textView4.setText("+" + NumberFormat.getInstance().format(doubleNumZps) + "/sec");

            TextView textView5 = (TextView) itemView.findViewById(R.id.clickAddId);
            if(currentUpgrade.getUpgradeName().equals("Claw")) {
                textView5.setText("");
            } else {
                textView5.setText("+" + MainActivity.df.format(currentUpgrade.getClickTotal()) + "/click");
            }

            ImageButton plusButton = (ImageButton) itemView.findViewById(R.id.plusButton);
            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (currentUpgrade.getCostOfUpgrade() <= totalZombies) {
                       if(sound == false) {
                           playButtonSoundClick();
                       }

                        totalUpgrades++;

                        int num = currentUpgrade.getNumberOfClicksOnUpgrade() + 1;
                        currentUpgrade.setNumberOfClicksOnUpgrade(num);
                        textView3.setText(MainActivity.df.format(num));

                        double oldCost = currentUpgrade.getCostOfUpgrade();
                        double newCost = currentUpgrade.calculateNewCostOfUpgrade(oldCost);
                        currentUpgrade.setCostOfUpgrade(newCost);
                        String stringNum = MainActivity.df.format(currentUpgrade.getCostOfUpgrade());
                        double doubleNum = Double.valueOf(stringNum);
                        textView2.setText(NumberFormat.getInstance().format(doubleNum));

                        addZPSTotal(currentUpgrade.getZombiesPerSecond());
                        subtractUpgradeFromTotal(oldCost);

                        addClickTotal(currentUpgrade.getClickTotal());

                        upgradeData(currentUpgrade,num,newCost);
                   }
                }
            });

            return itemView;
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