package com.justplaingoatapps.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.justplaingoatapps.hordeclicker.R;
import com.vstechlab.easyfonts.EasyFonts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends Activity {

    private String TAG = "TAG";

    public static DecimalFormat df = new DecimalFormat("#.#");

    private Random random = new Random();

    private int x;
    private int y;

    private ImageButton imageButton;
    private ImageButton biggerEarth;

    private ImageButton eye;
    private ImageButton eye2;
    private ImageButton eye3;
    private ImageButton eye4;
    private ImageButton eye5;

    private ArrayList<ImageButton> eyeBallList = new ArrayList<ImageButton>();

    private TextView textView;
    private TextView zpsTextView;
    private TextView zombieTitle;
    private TextView clickView;

    private TextView tip;
    private TextView tip2;
    private TextView tip3;
    private TextView tip4;
    private TextView tip5;
    private TextView tip6;

    private boolean tipDisplayed = false;

    private ImageButton soundButton;
    private ImageButton backButton;
    private ImageButton upgradeButton;
    private ImageButton achievementButton;
    private ImageButton statButton;

    private ImageView pop;
    private ImageView pop2;
    private ImageView pop3;
    private ImageView pop4;
    private ImageView pop5;
    private ImageView pop6;
    private ImageView pop7;
    private ImageView pop8;
    private ImageView pop9;
    private ImageView pop10;
    private ImageView pop11;
    private ImageView pop12;
    private ImageView pop13;
    private ImageView pop14;
    private ImageView pop15;
    private ImageView pop16;
    private ImageView pop17;
    private ImageView pop18;

    private ImageView bloodDrop;
    private ImageView bloodDrop2;
    private ImageView bloodDrop3;
    private ImageView bloodDrop4;
    private ImageView bloodDrop5;
    private ImageView bloodDrop6;

    private ImageView hordeDrop;
    private ImageView hordeDrop2;
    private ImageView hordeDrop3;
    private ImageView hordeDrop4;
    private ImageView hordeDrop5;
    private ImageView hordeDrop6;
    private ImageView hordeDrop7;
    private ImageView hordeDrop8;
    private ImageView hordeDrop9;
    private ImageView hordeDrop10;
    private ImageView hordeDrop11;
    private ImageView hordeDrop12;
    private ImageView hordeDrop13;
    private ImageView hordeDrop14;
    private ImageView hordeDrop15;
    private ImageView hordeDrop16;
    private ImageView hordeDrop17;
    private ImageView hordeDrop18;
    private ImageView hordeDrop19;
    private ImageView hordeDrop20;
    private ImageView hordeDrop21;
    private ImageView hordeDrop22;
    private ImageView hordeDrop23;
    private ImageView hordeDrop24;
    private ImageView hordeDrop25;

    private ImageView transparentEye;

    private double totalZombies = 0;
    private double totalZPS = 0;

    private int upgradeCount = 0;

    private int eyeClicks = 0;

    //When clicking on the eyeball
    private double changeZps = 0;

    //For stats
    private double totalZombiesAllTime = 0;

    private double handMadeZombies = 0;

    private int countPerClick = 1;

    private int changeCountPerClick = 0;

    //Counts how many times the button has been clicked
    private int totalClicks = 0;

    //Counts how many achievements have been accomplished
    private int achievementCount = 0;

    private boolean noReset = false;

    public static Achievement clawAch1;
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

    private boolean creditRead = false;

    private Upgrade claw;
    private Upgrade bite;
    private Upgrade speedIncrease;
    private Upgrade enhanceSmell;
    private Upgrade jumpIncrease;
    private Upgrade acid;
    private Upgrade pounce;
    private Upgrade explode;
    private Upgrade charge;
    private Upgrade bulletProof;
    private Upgrade gangUp;
    private Upgrade stealth;

    private boolean longTouch = false;

    private ArrayList<TextView> tipList = new ArrayList<TextView>();
    private TextView tempTip = null;
    private int tipMessageMs = 0;
    private int removeTipMessageMs = 0;
    private int pauseTipMessageMs = 0;
    private boolean tipInPause = false;

    private ArrayList<ImageView> popUpList = new ArrayList<ImageView>();
    private Rect rt;
    private int count = 0;
    private ImageView temp = null;

    //All Audio
    private SoundPool soundPool;
    private int soundZombieID;
    private int soundButtonClickID;
    private int soundHordeClickID;
    private float actVolume, maxVolume, volume;
    private AudioManager audioManager;

    private boolean mute = false;

    //Determines what time to start eye timer
    private int eyeMilliseconds = 0;

    private int removeEye = 0;

    private boolean eyeClicked = false;
    private boolean eyeDisplayed = false;

    private ImageButton imageEye;
    private ImageView hordeMessage;

    private boolean startHordeBlood = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findDimensions();

        textView = (TextView) findViewById(R.id.clickTextViewId);
        textView.setTypeface(EasyFonts.captureIt(this));

        zpsTextView = (TextView) findViewById(R.id.zpsId);
        zpsTextView.setTypeface(EasyFonts.captureIt(this));

        clickView = (TextView) findViewById(R.id.countPerClickId);
        clickView.setTypeface(EasyFonts.captureIt(this));

        zombieTitle = (TextView) findViewById(R.id.zombieTitleId);
        zombieTitle.setTypeface(EasyFonts.captureIt(this));

        tip = (TextView) findViewById(R.id.tipId);
        tip.setText("An Eyeball Will Pop Up Randomly, Clicking On It Will Create A Horde!");
        tip.setTypeface(EasyFonts.walkwayUltraBold(this));
        tip2 = (TextView) findViewById(R.id.tip2Id);
        tip2.setText("The Earth Will Change The More Zombies You Have, Keep Clicking!");
        tip2.setTypeface(EasyFonts.walkwayUltraBold(this));
        tip3 = (TextView) findViewById(R.id.tip3Id);
        tip3.setText("If You Enjoy The Game Please Rate And Share!");
        tip3.setTypeface(EasyFonts.walkwayUltraBold(this));
        tip4 = (TextView) findViewById(R.id.tip4Id);
        tip4.setText("Check Your Stats To See How Many Zombies You Have Ever Made!");
        tip4.setTypeface(EasyFonts.walkwayUltraBold(this));
        tip5 = (TextView) findViewById(R.id.tip5Id);
        tip5.setText("You Can Reset The Game From The Main Menu!");
        tip5.setTypeface(EasyFonts.walkwayUltraBold(this));
        tip6 = (TextView) findViewById(R.id.tip6Id);
        tip6.setText("The Game Will Automatically Save For You At Every Exit!");
        tip6.setTypeface(EasyFonts.walkwayUltraBold(this));


        populateTipList();

        eye = (ImageButton) findViewById(R.id.eyeId);
        eye2 = (ImageButton) findViewById(R.id.eye2Id);
        eye3 = (ImageButton) findViewById(R.id.eye3Id);
        eye4 = (ImageButton) findViewById(R.id.eye4Id);
        eye5 = (ImageButton) findViewById(R.id.eye5Id);

        populateEyeBallList();

        soundButton = (ImageButton) findViewById(R.id.soundButtonId);
        backButton = (ImageButton) findViewById(R.id.backButtonId);
        upgradeButton = (ImageButton) findViewById(R.id.upgradeButton);

        imageButton = (ImageButton) findViewById(R.id.earthPictureId);
        biggerEarth = (ImageButton) findViewById(R.id.earthPicture2Id);
        biggerEarth.setVisibility(View.INVISIBLE);

        achievementButton = (ImageButton) findViewById(R.id.achievementButton);

        statButton = (ImageButton) findViewById(R.id.statButton);

        pop = (ImageView) findViewById(R.id.popId);
        pop2 = (ImageView) findViewById(R.id.pop2Id);
        pop3 = (ImageView) findViewById(R.id.pop3Id);
        pop4 = (ImageView) findViewById(R.id.pop4Id);
        pop5 = (ImageView) findViewById(R.id.pop5Id);
        pop6 = (ImageView) findViewById(R.id.pop6Id);
        pop7 = (ImageView) findViewById(R.id.pop7Id);
        pop8 = (ImageView) findViewById(R.id.pop8Id);
        pop9 = (ImageView) findViewById(R.id.pop9Id);
        pop10 = (ImageView) findViewById(R.id.pop10Id);
        pop11 = (ImageView) findViewById(R.id.pop11Id);
        pop12 = (ImageView) findViewById(R.id.pop12Id);
        pop13 = (ImageView) findViewById(R.id.pop13Id);
        pop14 = (ImageView) findViewById(R.id.pop14Id);
        pop15 = (ImageView) findViewById(R.id.pop15Id);
        pop16 = (ImageView) findViewById(R.id.pop16Id);
        pop17 = (ImageView) findViewById(R.id.pop17Id);
        pop18 = (ImageView) findViewById(R.id.pop18Id);
        rt = pop.getDrawable().getBounds();

        populateList();

        bloodDrop = (ImageView) findViewById(R.id.bloodDropImageId);
        bloodDrop2 = (ImageView) findViewById(R.id.bloodDropImageTwoId);
        bloodDrop3 = (ImageView) findViewById(R.id.bloodDropImageThreeId);
        bloodDrop4 = (ImageView) findViewById(R.id.bloodDropImageFourId);
        bloodDrop5 = (ImageView) findViewById(R.id.bloodDropImageFiveId);
        bloodDrop6 = (ImageView) findViewById(R.id.bloodDropImageSixId);

        hordeDrop = (ImageView) findViewById(R.id.hordeBloodId);
        hordeDrop2 = (ImageView) findViewById(R.id.hordeBlood2Id);
        hordeDrop3 = (ImageView) findViewById(R.id.hordeBlood3Id);
        hordeDrop4 = (ImageView) findViewById(R.id.hordeBlood4Id);
        hordeDrop5 = (ImageView) findViewById(R.id.hordeBlood5Id);
        hordeDrop6 = (ImageView) findViewById(R.id.hordeBlood6Id);
        hordeDrop7 = (ImageView) findViewById(R.id.hordeBlood7Id);
        hordeDrop8 = (ImageView) findViewById(R.id.hordeBlood8Id);
        hordeDrop9 = (ImageView) findViewById(R.id.hordeBlood9Id);
        hordeDrop10 = (ImageView) findViewById(R.id.hordeBlood10Id);
        hordeDrop11 = (ImageView) findViewById(R.id.hordeBlood11Id);
        hordeDrop12 = (ImageView) findViewById(R.id.hordeBlood12Id);
        hordeDrop13 = (ImageView) findViewById(R.id.hordeBlood13Id);
        hordeDrop14 = (ImageView) findViewById(R.id.hordeBlood14Id);
        hordeDrop15 = (ImageView) findViewById(R.id.hordeBlood15Id);
        hordeDrop16 = (ImageView) findViewById(R.id.hordeBlood16Id);
        hordeDrop17 = (ImageView) findViewById(R.id.hordeBlood17Id);
        hordeDrop18 = (ImageView) findViewById(R.id.hordeBlood18Id);
        hordeDrop19 = (ImageView) findViewById(R.id.hordeBlood19Id);
        hordeDrop20 = (ImageView) findViewById(R.id.hordeBlood20Id);
        hordeDrop21 = (ImageView) findViewById(R.id.hordeBlood21Id);
        hordeDrop22 = (ImageView) findViewById(R.id.hordeBlood22Id);
        hordeDrop23 = (ImageView) findViewById(R.id.hordeBlood23Id);
        hordeDrop24 = (ImageView) findViewById(R.id.hordeBlood24Id);
        hordeDrop25 = (ImageView) findViewById(R.id.hordeBlood25Id);

        transparentEye = (ImageView) findViewById(R.id.transparentEyeId);
        hordeMessage = (ImageView) findViewById(R.id.hordeMessageId);

        load();

        String stringNum = df.format(totalZombies);
        double doubleNum = Double.valueOf(stringNum);

        textView.setText(NumberFormat.getInstance().format(doubleNum));

        String stringNumZps = df.format(totalZPS);
        double doubleNumZps = Double.valueOf(stringNumZps);

        zpsTextView.setText("Per Second: " + NumberFormat.getInstance().format(doubleNumZps));

        String stringNumClick = df.format(countPerClick);
        double doubleNumClick = Double.valueOf(stringNumClick);

        clickView.setText("Per Click: " + NumberFormat.getInstance().format(doubleNumClick));

        checkAchievements();
        runAlways();
        startBloodDropAnimation();

        biggerEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        biggerEarth.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        setSoundDisplay();
        setupAudio();

        changeEarth();
        displayCroutons();

        onSoundButtonClick();
        onStatButtonClick();
        onBackButtonClick();
        onAchievementButtonClick();
        onUpgradeButtonClick();
        onImageButtonLongClick();
        onImageButtonClick();
    }

    private void setupAudio() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundZombieID = soundPool.load(this, R.raw.zombie, 1);
        soundButtonClickID = soundPool.load(this,R.raw.buttonclick,1);
        soundHordeClickID = soundPool.load(this,R.raw.hordesound,1);
    }

    private void playZombieSound() {
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        soundPool.play(soundZombieID, volume, volume, 1, 0, 1f);
    }

    private void playButtonClickSound() {
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        soundPool.play(soundButtonClickID,volume,volume,1,0,1f);
    }

    private void playHordeSound() {
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        soundPool.play(soundHordeClickID,volume,volume,1,0,1f);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    private void populateEyeBallList() {
        eyeBallList.add(eye);
        eyeBallList.add(eye2);
        eyeBallList.add(eye3);
        eyeBallList.add(eye4);
        eyeBallList.add(eye5);
    }

    private void populateList() {
        popUpList.add(pop);
        popUpList.add(pop2);
        popUpList.add(pop3);
        popUpList.add(pop4);
        popUpList.add(pop5);
        popUpList.add(pop6);
        popUpList.add(pop7);
        popUpList.add(pop8);
        popUpList.add(pop9);
        popUpList.add(pop10);
        popUpList.add(pop11);
        popUpList.add(pop12);
        popUpList.add(pop12);
        popUpList.add(pop13);
        popUpList.add(pop14);
        popUpList.add(pop15);
        popUpList.add(pop16);
        popUpList.add(pop17);
        popUpList.add(pop18);
    }

    private void populateTipList() {
        tipList.add(tip);
        tipList.add(tip2);
        tipList.add(tip3);
        tipList.add(tip4);
        tipList.add(tip5);
        tipList.add(tip6);
    }

    public static SharedPreferences.Editor putDouble(final SharedPreferences.Editor edit, final String key, final double value) {
        return edit.putLong(key, Double.doubleToRawLongBits(value));
    }

    public static double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }

    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //Achievement Save
        String jsonClawAch1 = gson.toJson(clawAch1);
        String jsonClawAch2 = gson.toJson(clawAch2);
        String jsonClawAch3 = gson.toJson(clawAch3);
        String jsonBiteAch1 = gson.toJson(biteAch1);
        String jsonBiteAch2 = gson.toJson(biteAch2);
        String jsonBiteAch3 = gson.toJson(biteAch3);
        String jsonSpeedAch1 = gson.toJson(speedAch1);
        String jsonSpeedAch2 = gson.toJson(speedAch2);
        String jsonSpeedAch3 = gson.toJson(speedAch3);
        String jsonSmellAch1 = gson.toJson(smellAch1);
        String jsonSmellAch2 = gson.toJson(smellAch2);
        String jsonSmellAch3 = gson.toJson(smellAch3);
        String jsonJumpAch1 = gson.toJson(jumpAch1);
        String jsonJumpAch2 = gson.toJson(jumpAch2);
        String jsonJumpAch3 = gson.toJson(jumpAch3);
        String jsonAcidAch1 = gson.toJson(acidAch1);
        String jsonAcidAch2 = gson.toJson(acidAch2);
        String jsonAcidAch3 = gson.toJson(acidAch3);
        String jsonExplodeAch1 = gson.toJson(explodeAch1);
        String jsonExplodeAch2 = gson.toJson(explodeAch2);
        String jsonExplodeAch3 = gson.toJson(explodeAch3);
        String jsonBulletAch1 = gson.toJson(bulletAch1);
        String jsonBulletAch2 = gson.toJson(bulletAch2);
        String jsonBulletAch3 = gson.toJson(bulletAch3);
        String jsonPounceAch1 = gson.toJson(pounceAch1);
        String jsonPounceAch2 = gson.toJson(pounceAch2);
        String jsonPounceAch3 = gson.toJson(pounceAch3);
        String jsonChargeAch1 = gson.toJson(chargeAch1);
        String jsonChargeAch2 = gson.toJson(chargeAch2);
        String jsonChargeAch3 = gson.toJson(chargeAch3);
        String jsonGangUpAch1 = gson.toJson(gangUpAch1);
        String jsonGangUpAch2 = gson.toJson(gangUpAch2);
        String jsonGangUpAch3 = gson.toJson(gangUpAch3);
        String jsonStealthAch1 = gson.toJson(stealthAch1);
        String jsonStealthAch2 = gson.toJson(stealthAch2);
        String jsonStealthAch3 = gson.toJson(stealthAch3);
        String jsonClicksOnEarthAch1 = gson.toJson(clicksOnEarthAch1);
        String jsonClicksOnEarthAch2 = gson.toJson(clicksOnEarthAch2);
        String jsonClicksOnEarthAch3 = gson.toJson(clicksOnEarthAch3);
        String jsonTotalZombieAch1 = gson.toJson(totalZombieAch1);
        String jsonTotalZombieAch2 = gson.toJson(totalZombieAch2);
        String jsonTotalZombieAch3 = gson.toJson(totalZombieAch3);
        String jsonTotalZombieAllTimeAch1 = gson.toJson(totalZombieAllTimeAch1);
        String jsonTotalZombieAllTimeAch2 = gson.toJson(totalZombieAllTimeAch2);
        String jsonTotalZombieAllTimeAch3 = gson.toJson(totalZombieAllTimeAch3);
        String jsonTotalZpsAch1 = gson.toJson(totalZpsAch1);
        String jsonTotalZpsAch2 = gson.toJson(totalZpsAch2);
        String jsonTotalZpsAch3 = gson.toJson(totalZpsAch3);
        String jsonTotalPerClickCountAch = gson.toJson(totalPerClickCountAch);
        String jsonTotalUpgradeCountAch1 = gson.toJson(totalUpgradeCountAch1);
        String jsonTotalUpgradeCountAch2 = gson.toJson(totalUpgradeCountAch2);
        String jsonTotalUpgradeCountAch3 = gson.toJson(totalUpgradeCountAch3);
        String jsonReadCreditsAch = gson.toJson(readCreditsAch);
        String jsonHordeAch1 = gson.toJson(hordeAch1);
        String jsonHordeAch2 = gson.toJson(hordeAch2);
        String jsonHordeAch3 = gson.toJson(hordeAch3);
        String jsonStartAppAch = gson.toJson(startAppAch);
        String jsonSecretAch = gson.toJson(secretAch);
        String jsonSecretAch2 = gson.toJson(secretAch2);
        String jsonGetAllAch = gson.toJson(getAllAch);

        editor.putString("clawAch1",jsonClawAch1);
        editor.putString("clawAch2",jsonClawAch2);
        editor.putString("clawAch3",jsonClawAch3);
        editor.putString("biteAch1",jsonBiteAch1);
        editor.putString("biteAch2",jsonBiteAch2);
        editor.putString("biteAch3",jsonBiteAch3);
        editor.putString("speedAch1",jsonSpeedAch1);
        editor.putString("speedAch2",jsonSpeedAch2);
        editor.putString("speedAch3",jsonSpeedAch3);
        editor.putString("smellAch1",jsonSmellAch1);
        editor.putString("smellAch2",jsonSmellAch2);
        editor.putString("smellAch3",jsonSmellAch3);
        editor.putString("jumpAch1",jsonJumpAch1);
        editor.putString("jumpAch2",jsonJumpAch2);
        editor.putString("jumpAch3",jsonJumpAch3);
        editor.putString("acidAch1",jsonAcidAch1);
        editor.putString("acidAch2",jsonAcidAch2);
        editor.putString("acidAch3",jsonAcidAch3);
        editor.putString("explodeAch1",jsonExplodeAch1);
        editor.putString("explodeAch2",jsonExplodeAch2);
        editor.putString("explodeAch3",jsonExplodeAch3);
        editor.putString("bulletAch1",jsonBulletAch1);
        editor.putString("bulletAch2",jsonBulletAch2);
        editor.putString("bulletAch3",jsonBulletAch3);
        editor.putString("pounceAch1",jsonPounceAch1);
        editor.putString("pounceAch2",jsonPounceAch2);
        editor.putString("pounceAch3",jsonPounceAch3);
        editor.putString("chargeAch1",jsonChargeAch1);
        editor.putString("chargeAch2",jsonChargeAch2);
        editor.putString("chargeAch3",jsonChargeAch3);
        editor.putString("gangUpAch1",jsonGangUpAch1);
        editor.putString("gangUpAch2",jsonGangUpAch2);
        editor.putString("gangUpAch3",jsonGangUpAch3);
        editor.putString("stealthAch1",jsonStealthAch1);
        editor.putString("stealthAch2",jsonStealthAch2);
        editor.putString("stealthAch3",jsonStealthAch3);
        editor.putString("clicksOnEarthAch1",jsonClicksOnEarthAch1);
        editor.putString("clicksOnEarthAch2",jsonClicksOnEarthAch2);
        editor.putString("clicksOnEarthAch3",jsonClicksOnEarthAch3);
        editor.putString("totalZombieAch1",jsonTotalZombieAch1);
        editor.putString("totalZombieAch2",jsonTotalZombieAch2);
        editor.putString("totalZombieAch3",jsonTotalZombieAch3);
        editor.putString("totalZombieAllTimeAch1",jsonTotalZombieAllTimeAch1);
        editor.putString("totalZombieAllTimeAch2",jsonTotalZombieAllTimeAch2);
        editor.putString("totalZombieAllTimeAch3",jsonTotalZombieAllTimeAch3);
        editor.putString("totalZpsAch1",jsonTotalZpsAch1);
        editor.putString("totalZpsAch2",jsonTotalZpsAch2);
        editor.putString("totalZpsAch3",jsonTotalZpsAch3);
        editor.putString("totalPerClickCountAch",jsonTotalPerClickCountAch);
        editor.putString("totalUpgradeCountAch1",jsonTotalUpgradeCountAch1);
        editor.putString("totalUpgradeCountAch2",jsonTotalUpgradeCountAch2);
        editor.putString("totalUpgradeCountAch3",jsonTotalUpgradeCountAch3);
        editor.putString("readCreditsAch",jsonReadCreditsAch);
        editor.putString("hordeAch1",jsonHordeAch1);
        editor.putString("hordeAch2",jsonHordeAch2);
        editor.putString("hordeAch3",jsonHordeAch3);
        editor.putString("startAppAch",jsonStartAppAch);
        editor.putString("secretAch",jsonSecretAch);
        editor.putString("secretAch2",jsonSecretAch2);
        editor.putString("getAllAch",jsonGetAllAch);


        //Upgrade Save
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
        editor.putString("pounceObject", jsonPounce);
        editor.putString("bulletProofObject", jsonBulletProof);
        editor.putString("explodeObject", jsonExplode);
        editor.putString("chargeObject", jsonCharge);
        editor.putString("gangUpObject", jsonGangUp);
        editor.putString("stealthObject", jsonStealth);

        //Save Values
        putDouble(editor,"totalZombies",totalZombies);
        putDouble(editor,"totalZPS",totalZPS);
        putDouble(editor,"totalZombiesAllTime",totalZombiesAllTime);
        editor.putInt("totalClicks", totalClicks);
        editor.putInt("achCount", achievementCount);
        putDouble(editor, "handMadeZombies", handMadeZombies);
        editor.putBoolean("soundBut", mute);
        editor.putInt("eyeTime", eyeMilliseconds);
        editor.putInt("removeEyeTime", removeEye);
        editor.putInt("eyeClicks", eyeClicks);
        editor.putInt("tipMessage", tipMessageMs);
        editor.putInt("removeTip", removeTipMessageMs);
        editor.putInt("pauseTip", pauseTipMessageMs);

        editor.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences != null) {
            totalZombies = getDouble(sharedPreferences,"totalZombies",UpgradeView.DEFAULT);
            totalZPS = getDouble(sharedPreferences,"totalZPS",UpgradeView.DEFAULT);
            totalZombiesAllTime = getDouble(sharedPreferences,"totalZombiesAllTime",UpgradeView.DEFAULT);
            countPerClick = sharedPreferences.getInt("countPerClick", 1);
            totalClicks = sharedPreferences.getInt("totalClicks", UpgradeView.DEFAULT);
            achievementCount = sharedPreferences.getInt("achCount", UpgradeView.DEFAULT);
            upgradeCount = sharedPreferences.getInt("totalUpgrades", UpgradeView.DEFAULT);
            handMadeZombies = getDouble(sharedPreferences, "handMadeZombies", UpgradeView.DEFAULT);
            creditRead = sharedPreferences.getBoolean("creditRead", false);
            noReset = sharedPreferences.getBoolean("noReset", false);
            mute = sharedPreferences.getBoolean("soundBut", false);
            eyeMilliseconds = sharedPreferences.getInt("eyeTime", UpgradeView.DEFAULT);
            removeEye = sharedPreferences.getInt("removeEyeTime", UpgradeView.DEFAULT);
            eyeClicks = sharedPreferences.getInt("eyeClicks", UpgradeView.DEFAULT);
            tipMessageMs = sharedPreferences.getInt("tipMessage", UpgradeView.DEFAULT);
            removeTipMessageMs = sharedPreferences.getInt("removeTip", UpgradeView.DEFAULT);
            pauseTipMessageMs = sharedPreferences.getInt("pauseTip", UpgradeView.DEFAULT);

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

            if(clawAch1 == null) {
                createNewAchievements();
            }

            String jsonClaw = sharedPreferences.getString("clawObject", "");
            String jsonBite = sharedPreferences.getString("biteObject", "");
            String jsonSpeed = sharedPreferences.getString("speedObject", "");
            String jsonSmell = sharedPreferences.getString("smellObject", "");
            String jsonJump = sharedPreferences.getString("jumpObject", "");
            String jsonAcid = sharedPreferences.getString("acidObject", "");
            String jsonPounce = sharedPreferences.getString("pounceObject", "");
            String jsonBulletProof = sharedPreferences.getString("bulletProofObject", "");
            String jsonExplode = sharedPreferences.getString("explodeObject", "");
            String jsonCharge = sharedPreferences.getString("chargeObject", "");
            String jsonGangUp = sharedPreferences.getString("gangUpObject", "");
            String jsonStealth = sharedPreferences.getString("stealthObject", "");

            claw = gson.fromJson(jsonClaw, Upgrade.class);
            bite = gson.fromJson(jsonBite, Upgrade.class);
            speedIncrease = gson.fromJson(jsonSpeed, Upgrade.class);
            enhanceSmell = gson.fromJson(jsonSmell, Upgrade.class);
            jumpIncrease = gson.fromJson(jsonJump, Upgrade.class);
            acid = gson.fromJson(jsonAcid, Upgrade.class);
            pounce = gson.fromJson(jsonPounce, Upgrade.class);
            bulletProof = gson.fromJson(jsonBulletProof, Upgrade.class);
            explode = gson.fromJson(jsonExplode, Upgrade.class);
            charge = gson.fromJson(jsonCharge, Upgrade.class);
            gangUp = gson.fromJson(jsonGangUp, Upgrade.class);
            stealth = gson.fromJson(jsonStealth, Upgrade.class);

            if(claw == null) {
                createNewUpgrades();
            }
        }
    }

    private void createNewAchievements() {
        Log.e(TAG,"Creating new Achievements");
        startAppAch = new Achievement("It's A Start!",R.drawable.number1,"Play The Game",R.drawable.check,false,false);
        clawAch1 = new Achievement("Tis' But A Scratch",R.drawable.number2,"50 Claw Abilities",R.drawable.check,false,false);
        clawAch2 = new Achievement("Very Ouch",R.drawable.number3,"65 Claw Abilities",R.drawable.check,false,false);
        clawAch3 = new Achievement("Stitches Are Needed",R.drawable.number4,"100 Claw Abilities",R.drawable.check,false,false);
        biteAch1 = new Achievement("Human Jaw",R.drawable.number5,"40 Bite Abilities",R.drawable.check,false,false);
        biteAch2 = new Achievement("Tiger Jaw",R.drawable.number6,"50 Bite Abilities",R.drawable.check,false,false);
        biteAch3 = new Achievement("Alligator Jaw",R.drawable.number7,"60 Bite Abilities",R.drawable.check,false,false);
        speedAch1 = new Achievement("Average Joe",R.drawable.number8,"25 Speed Abilities",R.drawable.check,false,false);
        speedAch2 = new Achievement("Athlete",R.drawable.number9,"30 Speed Abilities",R.drawable.check,false,false);
        speedAch3 = new Achievement("Usain Bolt",R.drawable.number10,"40 Speed Abilities",R.drawable.check,false,false);
        smellAch1 = new Achievement("I Smell You",R.drawable.number11,"15 Smell Abilities",R.drawable.check,false,false);
        smellAch2 = new Achievement("I Smell You More",R.drawable.number12,"25 Smell Abilities",R.drawable.check,false,false);
        smellAch3 = new Achievement("I Smell You The Most",R.drawable.number13,"35 Smell Abilities",R.drawable.check,false,false);
        jumpAch1 = new Achievement("6 Feet",R.drawable.number14,"35 Jump Abilities",R.drawable.check,false,false);
        jumpAch2 = new Achievement("7 Feet",R.drawable.number15,"40 Jump Abilities",R.drawable.check,false,false);
        jumpAch3 = new Achievement("8 Feet",R.drawable.number16,"50 Jump Abilities",R.drawable.check,false,false);
        acidAch1 = new Achievement("That's Gross",R.drawable.number17,"10 Acid Abilities",R.drawable.check,false,false);
        acidAch2 = new Achievement("This Is Insanitary",R.drawable.number18,"15 Acid Abilities",R.drawable.check,false,false);
        acidAch3 = new Achievement("I Need A Shower",R.drawable.number19,"20 Acid Abilities",R.drawable.check,false,false);
        explodeAch1 = new Achievement("BOOM!",R.drawable.number20,"5 Exploding Limbs Abilities",R.drawable.check,false,false);
        explodeAch2 = new Achievement("KABOOM!",R.drawable.number21,"10 Exploding Limbs Abilities",R.drawable.check,false,false);
        explodeAch3 = new Achievement("BOOM! KABOOM!",R.drawable.number22,"15 Exploding Limbs Abilities",R.drawable.check,false,false);
        pounceAch1 = new Achievement("The Ground Is Cold",R.drawable.number23,"10 Pounce Abilities",R.drawable.check,false,false);
        pounceAch2 = new Achievement("Heavy Weight",R.drawable.number24,"15 Pounce Abilities",R.drawable.check,false,false);
        pounceAch3 = new Achievement("Pounce Master",R.drawable.number25,"20 Pounce Abilities",R.drawable.check,false,false);
        chargeAch1 = new Achievement("Bone Crusher",R.drawable.number26,"5 Charge Abilities",R.drawable.check,false,false);
        chargeAch2 = new Achievement("Severed Impact",R.drawable.number27,"10 Charge Abilities",R.drawable.check,false,false);
        chargeAch3 = new Achievement("Football Star",R.drawable.number28,"20 Charge Abilities",R.drawable.check,false,false);
        bulletAch1 = new Achievement("Bullet Sponge",R.drawable.number29,"15 Bullet-Proof Abilities",R.drawable.check,false,false);
        bulletAch2 = new Achievement("I Can Take It",R.drawable.number30,"20 Bullet-Proof Abilities",R.drawable.check,false,false);
        bulletAch3 = new Achievement("I Am The One",R.drawable.number31,"25 Bullet-Proof Abilities",R.drawable.check,false,false);
        gangUpAch1 = new Achievement("Help, Please?",R.drawable.number32,"10 Duplicate Abilities",R.drawable.check,false,false);
        gangUpAch2 = new Achievement("Is This Unfair?",R.drawable.number33,"15 Duplicate Abilities",R.drawable.check,false,false);
        gangUpAch3 = new Achievement("Seriously?",R.drawable.number34,"20 Duplicate Abilities",R.drawable.check,false,false);
        stealthAch1 = new Achievement("Sneaky",R.drawable.number35,"5 Stealth Abilities",R.drawable.check,false,false);
        stealthAch2 = new Achievement("Behind You!",R.drawable.number36,"10 Stealth Abilities",R.drawable.check,false,false);
        stealthAch3 = new Achievement("Ninja Status",R.drawable.number37,"15 Stealth Abilities",R.drawable.check,false,false);
        hordeAch1 = new Achievement("Newbie Horde",R.drawable.number38,"Start 5 Hordes",R.drawable.check,false,false);
        hordeAch2 = new Achievement("Pro Horde",R.drawable.number39,"Start 20 Hordes",R.drawable.check,false,false);
        hordeAch3 = new Achievement("God Horde",R.drawable.number40,"Start 75 Hordes",R.drawable.check,false,false);
        clicksOnEarthAch1 = new Achievement("Poke.",R.drawable.number41,"500 Clicks On Earth",R.drawable.check,false,false);
        clicksOnEarthAch2 = new Achievement("I Like Simple Tasks",R.drawable.number42,"5000 Clicks On Earth",R.drawable.check,false,false);
        clicksOnEarthAch3 = new Achievement("Is Your Finger Tired?",R.drawable.number43,"10000 Clicks On Earth",R.drawable.check,false,false);
        totalZombieAch1 = new Achievement("Saving Up",R.drawable.number44,"500 Million Total Zombies(Current)",R.drawable.check,false,false);
        totalZombieAch2 = new Achievement("Almost Enough",R.drawable.number45,"50 Billion Total Zombies(Current)",R.drawable.check,false,false);
        totalZombieAch3 = new Achievement("Buy Abilities Already",R.drawable.number46,"1 Trillion Total Zombies(Current)",R.drawable.check,false,false);
        totalZombieAllTimeAch1 = new Achievement("Some Left",R.drawable.number47,"150 Billion Total Zombies(Ever)",R.drawable.check,false,false);
        totalZombieAllTimeAch2 = new Achievement("Few Left",R.drawable.number48,"20 Trillion Total Zombies(Ever)",R.drawable.check,false,false);
        totalZombieAllTimeAch3 = new Achievement("Nobody Left",R.drawable.number49,"250 Trillion Total Zombies(Ever)",R.drawable.check,false,false);
        totalZpsAch1 = new Achievement("This Is Fun",R.drawable.number50,"1 Million Total Zombies/Sec",R.drawable.check,false,false);
        totalZpsAch2 = new Achievement("I'm Not Playing",R.drawable.number51,"100 Million Total Zombies/Sec",R.drawable.check,false,false);
        totalZpsAch3 = new Achievement("I Like Numbers",R.drawable.number52,"10 Billion Total Zombies/Sec",R.drawable.check,false,false);
        totalPerClickCountAch = new Achievement("Strong Finger",R.drawable.number53,"300,000 Zombies Per Click",R.drawable.check,false,false);
        totalUpgradeCountAch1 = new Achievement("Beginner Zombies",R.drawable.number54,"50 Total Abilities",R.drawable.check,false,false);
        totalUpgradeCountAch2 = new Achievement("Novice Zombies",R.drawable.number55,"150 Total Abilities",R.drawable.check,false,false);
        totalUpgradeCountAch3 = new Achievement("Pro Zombies",R.drawable.number56,"425 Total Abilities",R.drawable.check,false,false);
        readCreditsAch = new Achievement("For The Achievement",R.drawable.number57,"Read The CreditView",R.drawable.check,false,false);
        secretAch = new Achievement("It's A Secret",R.drawable.number58,"?",R.drawable.check,false,false);
        secretAch2 = new Achievement("Another Secret",R.drawable.number59,"?",R.drawable.check,false,false);
        getAllAch = new Achievement("MVP",R.drawable.number60,"Complete All Achievements",R.drawable.check,false,false);
    }

    private void createNewUpgrades() {
        claw = new Upgrade("Claw",10.0,R.drawable.claw,0,1,0);
        bite = new Upgrade("Bite",100.0,R.drawable.bite,0,10,1);
        jumpIncrease = new Upgrade("High Jump",1000.0,R.drawable.jump,0,50,3);
        speedIncrease = new Upgrade("Super Speed",9998.0,R.drawable.speed,0,400,5);
        enhanceSmell = new Upgrade("Strong Smell",99997.0,R.drawable.nose,0,2000,10);
        acid = new Upgrade("Acidic Spit",999998.0,R.drawable.acid,0,10000,20);
        pounce = new Upgrade("Pounce",9999998.0,R.drawable.pounce,0,50000,40);
        explode = new Upgrade("Exploding Limbs",99999997.0,R.drawable.explosion,0,150000,100);
        charge = new Upgrade("Charge",499000123.0,R.drawable.charge,0,750000,400);
        bulletProof = new Upgrade("Bullet-Proof",1000000000.0,R.drawable.bullet,0,3000000,800);
        gangUp = new Upgrade("Duplicate",9000000009.0,R.drawable.clone,0,30000000,1500);
        stealth = new Upgrade("Stealth",98765432100.0,R.drawable.stealth,0,315000000,25000);
    }

    private void findDimensions() {
        //Gets the phones screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        x = size.x - 20;
        y = size.y;
    }

    private void startBloodDropAnimation() {
        //Calculate new X value for blood drop animation
        float drop1 = random.nextInt(x);
        float drop2 = random.nextInt(x);
        float drop3 = random.nextInt(x);
        float drop4 = random.nextInt(x);
        float drop5 = random.nextInt(x);
        float drop6 = random.nextInt(x);

        int negDrop1 = random.nextInt(2);
        if(negDrop1 == 1) {
            drop1 = -(drop1) + 20;
        }
        int negDrop2 = random.nextInt(2);
        if(negDrop2 == 1) {
            drop2 = -(drop2) + 20;
        }
        int negDrop3 = random.nextInt(2);
        if(negDrop3 == 1) {
            drop3 = -(drop3) + 20;
        }
        int negDrop4 = random.nextInt(2);
        if(negDrop4 == 1) {
            drop4 = -(drop4) + 20;
        }
        int negDrop5 = random.nextInt(2);
        if(negDrop5 == 1) {
            drop5 = -(drop5) + 20;
        }
        int negDrop6 = random.nextInt(2);
        if(negDrop6 == 1) {
            drop6 = -(drop6) + 20;
        }

        TranslateAnimation animation1 = new TranslateAnimation(drop1, drop1, 0.0f, y);
        TranslateAnimation animation2 = new TranslateAnimation(drop2, drop2, 0.0f, y);
        TranslateAnimation animation3 = new TranslateAnimation(drop3, drop3, 0.0f, y);
        TranslateAnimation animation4 = new TranslateAnimation(drop4, drop4, 0.0f, y);
        TranslateAnimation animation5 = new TranslateAnimation(drop5, drop5, 0.0f, y);
        TranslateAnimation animation6 = new TranslateAnimation(drop6, drop6, 0.0f, y);

        //Determines how fast the blood drop will move
        //1000 MS == 1 Second
        animation1.setDuration(1000);
        animation1.setRepeatCount(0);
        animation1.setFillAfter(true);
        bloodDrop.startAnimation(animation1);

        animation2.setDuration(915);
        animation2.setRepeatCount(0);
        animation2.setFillAfter(true);
        bloodDrop2.startAnimation(animation2);

        animation3.setDuration(1150);
        animation3.setRepeatCount(0);
        animation3.setFillAfter(true);
        bloodDrop3.startAnimation(animation3);

        animation4.setDuration(850);
        animation4.setRepeatCount(0);
        animation4.setFillAfter(true);
        bloodDrop4.startAnimation(animation4);

        animation5.setDuration(975);
        animation5.setRepeatCount(0);
        animation5.setFillAfter(true);
        bloodDrop5.startAnimation(animation5);

        animation6.setDuration(990);
        animation6.setRepeatCount(0);
        animation6.setFillAfter(true);
        bloodDrop6.startAnimation(animation6);
    }

    private void startHordeBloodAnimation() {
        //Calculate new X value for blood drop animation
        float drop1 = random.nextInt(x);
        float drop2 = random.nextInt(x);
        float drop3 = random.nextInt(x);
        float drop4 = random.nextInt(x);
        float drop5 = random.nextInt(x);
        float drop6 = random.nextInt(x);
        float drop7 = random.nextInt(x);
        float drop8 = random.nextInt(x);
        float drop9 = random.nextInt(x);
        float drop10 = random.nextInt(x);
        float drop11 = random.nextInt(x);
        float drop12 = random.nextInt(x);
        float drop13 = random.nextInt(x);
        float drop14 = random.nextInt(x);
        float drop15 = random.nextInt(x);
        float drop16 = random.nextInt(x);
        float drop17 = random.nextInt(x);
        float drop18 = random.nextInt(x);
        float drop19 = random.nextInt(x);
        float drop20 = random.nextInt(x);
        float drop21 = random.nextInt(x);
        float drop22 = random.nextInt(x);
        float drop23 = random.nextInt(x);
        float drop24 = random.nextInt(x);
        float drop25 = random.nextInt(x);

        int negDrop1 = random.nextInt(2);
        if(negDrop1 == 1) {
            drop1 = -(drop1) + 20;
        }
        int negDrop2 = random.nextInt(2);
        if(negDrop2 == 1) {
            drop2 = -(drop2) + 20;
        }
        int negDrop3 = random.nextInt(2);
        if(negDrop3 == 1) {
            drop3 = -(drop3) + 20;
        }
        int negDrop4 = random.nextInt(2);
        if(negDrop4 == 1) {
            drop4 = -(drop4) + 20;
        }
        int negDrop5 = random.nextInt(2);
        if(negDrop5 == 1) {
            drop5 = -(drop5) + 20;
        }
        int negDrop6 = random.nextInt(2);
        if(negDrop6 == 1) {
            drop6 = -(drop6) + 20;
        }
        int negDrop7 = random.nextInt(2);
        if(negDrop7 == 1) {
            drop7 = -(drop7) + 20;
        }
        int negDrop8 = random.nextInt(2);
        if(negDrop8 == 1) {
            drop8 = -(drop8) + 20;
        }
        int negDrop9 = random.nextInt(2);
        if(negDrop9 == 1) {
            drop9 = -(drop9) + 20;
        }
        int negDrop10 = random.nextInt(2);
        if(negDrop10 == 1) {
            drop10 = -(drop10) + 20;
        }
        int negDrop11 = random.nextInt(2);
        if(negDrop11 == 1) {
            drop11 = -(drop11) + 20;
        }
        int negDrop12 = random.nextInt(2);
        if(negDrop12 == 1) {
            drop12 = -(drop12) + 20;
        }
        int negDrop13 = random.nextInt(2);
        if(negDrop13 == 1) {
            drop13 = -(drop13) + 20;
        }
        int negDrop14 = random.nextInt(2);
        if(negDrop14 == 1) {
            drop14 = -(drop14) + 20;
        }
        int negDrop15 = random.nextInt(2);
        if(negDrop15 == 1) {
            drop15 = -(drop15) + 20;
        }
        int negDrop16 = random.nextInt(2);
        if(negDrop16 == 1) {
            drop16 = -(drop16) + 20;
        }
        int negDrop17 = random.nextInt(2);
        if(negDrop17 == 1) {
            drop17 = -(drop17) + 20;
        }
        int negDrop18 = random.nextInt(2);
        if(negDrop18 == 1) {
            drop18= -(drop18) + 20;
        }
        int negDrop19 = random.nextInt(2);
        if(negDrop19 == 19) {
            drop19 = -(drop19) + 20;
        }
        int negDrop20 = random.nextInt(2);
        if(negDrop20 == 1) {
            drop20 = -(drop20) + 20;
        }
        int negDrop21 = random.nextInt(2);
        if(negDrop21 == 1) {
            drop21 = -(drop21) + 20;
        }
        int negDrop22 = random.nextInt(2);
        if(negDrop22 == 1) {
            drop22 = -(drop22) + 20;
        }
        int negDrop23 = random.nextInt(2);
        if(negDrop23 == 1) {
            drop23 = -(drop23) + 20;
        }
        int negDrop24 = random.nextInt(2);
        if(negDrop24 == 1) {
            drop24 = -(drop24) + 20;
        }
        int negDrop25 = random.nextInt(2);
        if(negDrop25 == 1) {
            drop25 = -(drop25) + 20;
        }

        TranslateAnimation animation1 = new TranslateAnimation(drop1, drop1, 0.0f, y);
        TranslateAnimation animation2 = new TranslateAnimation(drop2, drop2, 0.0f, y);
        TranslateAnimation animation3 = new TranslateAnimation(drop3, drop3, 0.0f, y);
        TranslateAnimation animation4 = new TranslateAnimation(drop4, drop4, 0.0f, y);
        TranslateAnimation animation5 = new TranslateAnimation(drop5, drop5, 0.0f, y);
        TranslateAnimation animation6 = new TranslateAnimation(drop6, drop6, 0.0f, y);
        TranslateAnimation animation7 = new TranslateAnimation(drop7, drop7, 0.0f, y);
        TranslateAnimation animation8 = new TranslateAnimation(drop8, drop8, 0.0f, y);
        TranslateAnimation animation9 = new TranslateAnimation(drop9, drop9, 0.0f, y);
        TranslateAnimation animation10 = new TranslateAnimation(drop10, drop10, 0.0f, y);
        TranslateAnimation animation11 = new TranslateAnimation(drop11, drop11, 0.0f, y);
        TranslateAnimation animation12 = new TranslateAnimation(drop12, drop12, 0.0f, y);
        TranslateAnimation animation13 = new TranslateAnimation(drop13, drop13, 0.0f, y);
        TranslateAnimation animation14 = new TranslateAnimation(drop14, drop14, 0.0f, y);
        TranslateAnimation animation15 = new TranslateAnimation(drop15, drop15, 0.0f, y);
        TranslateAnimation animation16 = new TranslateAnimation(drop16, drop16, 0.0f, y);
        TranslateAnimation animation17 = new TranslateAnimation(drop17, drop17, 0.0f, y);
        TranslateAnimation animation18 = new TranslateAnimation(drop18, drop18, 0.0f, y);
        TranslateAnimation animation19 = new TranslateAnimation(drop19, drop19, 0.0f, y);
        TranslateAnimation animation20 = new TranslateAnimation(drop20, drop20, 0.0f, y);
        TranslateAnimation animation21 = new TranslateAnimation(drop21, drop21, 0.0f, y);
        TranslateAnimation animation22 = new TranslateAnimation(drop22, drop22, 0.0f, y);
        TranslateAnimation animation23 = new TranslateAnimation(drop23, drop23, 0.0f, y);
        TranslateAnimation animation24 = new TranslateAnimation(drop24, drop24, 0.0f, y);
        TranslateAnimation animation25 = new TranslateAnimation(drop25, drop25, 0.0f, y);

        //Determines how fast the blood drop will move
        //1000 MS == 1 Second
        animation1.setDuration(600);
        animation1.setRepeatCount(0);
        animation1.setFillAfter(true);
        hordeDrop.startAnimation(animation1);

        animation2.setDuration(500);
        animation2.setRepeatCount(0);
        animation2.setFillAfter(true);
        hordeDrop2.startAnimation(animation2);

        animation3.setDuration(650);
        animation3.setRepeatCount(0);
        animation3.setFillAfter(true);
        hordeDrop3.startAnimation(animation3);

        animation4.setDuration(550);
        animation4.setRepeatCount(0);
        animation4.setFillAfter(true);
        hordeDrop4.startAnimation(animation4);

        animation5.setDuration(625);
        animation5.setRepeatCount(0);
        animation5.setFillAfter(true);
        hordeDrop5.startAnimation(animation5);

        animation6.setDuration(1200);
        animation6.setRepeatCount(0);
        animation6.setFillAfter(true);
        hordeDrop6.startAnimation(animation6);

        animation7.setDuration(790);
        animation7.setRepeatCount(0);
        animation7.setFillAfter(true);
        hordeDrop7.startAnimation(animation7);

        animation8.setDuration(550);
        animation8.setRepeatCount(0);
        animation8.setFillAfter(true);
        hordeDrop8.startAnimation(animation8);

        animation9.setDuration(500);
        animation9.setRepeatCount(0);
        animation9.setFillAfter(true);
        hordeDrop9.startAnimation(animation9);

        animation10.setDuration(430);
        animation10.setRepeatCount(0);
        animation10.setFillAfter(true);
        hordeDrop10.startAnimation(animation10);

        animation11.setDuration(560);
        animation11.setRepeatCount(0);
        animation11.setFillAfter(true);
        hordeDrop11.startAnimation(animation11);

        animation12.setDuration(700);
        animation12.setRepeatCount(0);
        animation12.setFillAfter(true);
        hordeDrop12.startAnimation(animation12);

        animation13.setDuration(750);
        animation13.setRepeatCount(0);
        animation13.setFillAfter(true);
        hordeDrop13.startAnimation(animation13);

        animation14.setDuration(725);
        animation14.setRepeatCount(0);
        animation14.setFillAfter(true);
        hordeDrop14.startAnimation(animation14);

        animation15.setDuration(900);
        animation15.setRepeatCount(0);
        animation15.setFillAfter(true);
        hordeDrop15.startAnimation(animation15);

        animation16.setDuration(990);
        animation16.setRepeatCount(0);
        animation16.setFillAfter(true);
        hordeDrop16.startAnimation(animation16);

        animation17.setDuration(750);
        animation17.setRepeatCount(0);
        animation17.setFillAfter(true);
        hordeDrop17.startAnimation(animation17);

        animation18.setDuration(1100);
        animation18.setRepeatCount(0);
        animation18.setFillAfter(true);
        hordeDrop18.startAnimation(animation18);

        animation19.setDuration(925);
        animation19.setRepeatCount(0);
        animation19.setFillAfter(true);
        hordeDrop19.startAnimation(animation19);

        animation20.setDuration(850);
        animation20.setRepeatCount(0);
        animation20.setFillAfter(true);
        hordeDrop20.startAnimation(animation20);

        animation21.setDuration(700);
        animation21.setRepeatCount(0);
        animation21.setFillAfter(true);
        hordeDrop21.startAnimation(animation21);

        animation22.setDuration(690);
        animation22.setRepeatCount(0);
        animation22.setFillAfter(true);
        hordeDrop22.startAnimation(animation22);

        animation23.setDuration(800);
        animation23.setRepeatCount(0);
        animation23.setFillAfter(true);
        hordeDrop23.startAnimation(animation23);

        animation24.setDuration(900);
        animation24.setRepeatCount(0);
        animation24.setFillAfter(true);
        hordeDrop24.startAnimation(animation24);

        animation25.setDuration(1000);
        animation25.setRepeatCount(0);
        animation25.setFillAfter(true);
        hordeDrop25.startAnimation(animation25);
    }

    private void startPopAnimation() {
        int left = rt.left;
        int top = rt.top;

        int posOrNeg = 1;
        int xDist;
        int yDist;

        int determineIfNegative = random.nextInt(2);
        if(determineIfNegative == 1) {
            posOrNeg = -1;
        }

        int determineXDist = random.nextInt(3);
        if(determineXDist == 1) {
            xDist = 5;
        } else if(determineXDist == 2) {
            xDist = 6;
        } else {
            xDist = 7;
        }

        int determineYDist = random.nextInt(3);
        if(determineYDist == 1) {
            yDist = 9;
        } else if(determineYDist == 2) {
            yDist = 10;
        } else {
            yDist = 11;
        }

        if(count == popUpList.size() - 1) {
            count = 0;
        }
        temp = popUpList.get(count);
        count++;

        temp.setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(left,posOrNeg * (x/xDist),top,-y/yDist);

        animation.setDuration(475);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);
        temp.startAnimation(animation);

        int toXDist;

        int determineToXDist = random.nextInt(5);
        if(determineToXDist == 1) {
            toXDist = 2;
        } else if(determineToXDist == 2) {
            toXDist = 3;
        } else if(determineToXDist == 3) {
            toXDist = 4;
        } else if(determineToXDist == 4) {
            toXDist = 5;
        } else {
            toXDist = 6;
        }

        if(posOrNeg == -1) {
            toXDist = -(toXDist);
        }

        final ImageView finalPop = temp;
        final int finalPosOrNeg = posOrNeg;
        final int finalXDist = xDist;
        final int finalYDist = yDist;
        final int finalToXDist = toXDist;

        Log.e(TAG,String.valueOf(toXDist));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                TranslateAnimation animationPart2 = new TranslateAnimation(finalPosOrNeg * (x/finalXDist), x/finalToXDist, -y/finalYDist, y);
                animationPart2.setDuration(700);
                animationPart2.setRepeatCount(0);
                animationPart2.setFillAfter(true);
                finalPop.startAnimation(animationPart2);
            }
        }, animation.getDuration());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "Saving from OnPause");
        Crouton.clearCroutonsForActivity(MainActivity.this);
        save();
    }

    private void displayCroutons() {
        if(clawAch1.isVisible() && clawAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 50 Claw Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clawAch1.setCroutonDisplayed(true);
        }
        if(clawAch2.isVisible() && clawAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 65 Claw Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clawAch2.setCroutonDisplayed(true);
        }
        if(clawAch3.isVisible() && clawAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 100 Claw Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clawAch3.setCroutonDisplayed(true);
        }
        if(biteAch1.isVisible() && biteAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 40 Bite Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            biteAch1.setCroutonDisplayed(true);
        }
        if(biteAch2.isVisible() && biteAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 50 Bite Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            biteAch2.setCroutonDisplayed(true);
        }
        if(biteAch3.isVisible() && biteAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 60 Bite Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            biteAch3.setCroutonDisplayed(true);
        }
        if(jumpAch1.isVisible() && jumpAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 35 Jump Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            jumpAch1.setCroutonDisplayed(true);
        }
        if(jumpAch2.isVisible() && jumpAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 40 Jump Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            jumpAch2.setCroutonDisplayed(true);
        }
        if(jumpAch3.isVisible() && jumpAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 50 Jump Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            jumpAch3.setCroutonDisplayed(true);
        }
        if(speedAch1.isVisible() && speedAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 25 Speed Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            speedAch1.setCroutonDisplayed(true);
        }
        if(speedAch2.isVisible() && speedAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 30 Speed Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            speedAch2.setCroutonDisplayed(true);
        }
        if(speedAch3.isVisible() && speedAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 40 Speed Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            speedAch3.setCroutonDisplayed(true);
        }
        if(smellAch1.isVisible() && smellAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Smell Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            smellAch1.setCroutonDisplayed(true);
        }
        if(smellAch2.isVisible() && smellAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 25 Smell Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            smellAch2.setCroutonDisplayed(true);
        }
        if(smellAch3.isVisible() && smellAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 35 Smell Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            smellAch3.setCroutonDisplayed(true);
        }
        if(acidAch1.isVisible() && acidAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 10 Spit Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            acidAch1.setCroutonDisplayed(true);
        }
        if(acidAch2.isVisible() && acidAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Spit Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            acidAch2.setCroutonDisplayed(true);
        }
        if(acidAch3.isVisible() && acidAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 20 Spit Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            acidAch3.setCroutonDisplayed(true);
        }
        if(pounceAch1.isVisible() && pounceAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 10 Pounce Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            pounceAch1.setCroutonDisplayed(true);
        }
        if(pounceAch2.isVisible() && pounceAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Pounce Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            pounceAch2.setCroutonDisplayed(true);
        }
        if(pounceAch3.isVisible() && pounceAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 20 Pounce Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            pounceAch3.setCroutonDisplayed(true);
        }
        if(explodeAch1.isVisible() && explodeAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 5 Explode Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            explodeAch1.setCroutonDisplayed(true);
        }
        if(explodeAch2.isVisible() && explodeAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 10 Explode Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            explodeAch2.setCroutonDisplayed(true);
        }
        if(explodeAch3.isVisible() && explodeAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Explode Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            explodeAch3.setCroutonDisplayed(true);
        }
        if(chargeAch1.isVisible() && chargeAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 5 Charge Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            chargeAch1.setCroutonDisplayed(true);
        }
        if(chargeAch2.isVisible() && chargeAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 10 Charge Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            chargeAch2.setCroutonDisplayed(true);
        }
        if(chargeAch3.isVisible() && chargeAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 20 Charge Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            chargeAch3.setCroutonDisplayed(true);
        }
        if(bulletAch1.isVisible() && bulletAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Bullet-Proof Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            bulletAch1.setCroutonDisplayed(true);
        }
        if(bulletAch2.isVisible() && bulletAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 20 Bullet-Proof Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            bulletAch2.setCroutonDisplayed(true);
        }
        if(bulletAch3.isVisible() && bulletAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 25 Bullet-Proof Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            bulletAch3.setCroutonDisplayed(true);
        }
        if(gangUpAch1.isVisible() && gangUpAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 10 Duplicate Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            gangUpAch1.setCroutonDisplayed(true);
        }
        if(gangUpAch2.isVisible() && gangUpAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Duplicate Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            gangUpAch2.setCroutonDisplayed(true);
        }
        if(gangUpAch3.isVisible() && gangUpAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 20 Duplicate Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            gangUpAch3.setCroutonDisplayed(true);
        }
        if(stealthAch1.isVisible() && stealthAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 5 Stealth Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            stealthAch1.setCroutonDisplayed(true);
        }
        if(stealthAch2.isVisible() && stealthAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 10 Stealth Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            stealthAch2.setCroutonDisplayed(true);
        }
        if(stealthAch3.isVisible() && stealthAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 15 Stealth Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            stealthAch3.setCroutonDisplayed(true);
        }
        if(startAppAch.isVisible() && startAppAch.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Play The Game!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            startAppAch.setCroutonDisplayed(true);
        }
        if(hordeAch1.isVisible() && hordeAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Start 5 Hordes!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            hordeAch1.setCroutonDisplayed(true);
        }
        if(hordeAch2.isVisible() && hordeAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Start 20 Hordes!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            hordeAch2.setCroutonDisplayed(true);
        }
        if(hordeAch3.isVisible() && hordeAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Start 75 Hordes!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            hordeAch3.setCroutonDisplayed(true);
        }
        if(clicksOnEarthAch1.isVisible() && clicksOnEarthAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Click 500 Times!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clicksOnEarthAch1.setCroutonDisplayed(true);
        }
        if(clicksOnEarthAch2.isVisible() && clicksOnEarthAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Click 5,000 Times!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clicksOnEarthAch2.setCroutonDisplayed(true);
        }
        if(clicksOnEarthAch3.isVisible() && clicksOnEarthAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Click 10,000 Times!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clicksOnEarthAch3.setCroutonDisplayed(true);
        }
        if(totalZombieAch1.isVisible() && totalZombieAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 500 Million Total Zombies (Current)!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZombieAch1.setCroutonDisplayed(true);
        }
        if(totalZombieAch2.isVisible() && totalZombieAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 50 Billion Total Zombies (Current)!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZombieAch2.setCroutonDisplayed(true);
        }
        if(totalZombieAch3.isVisible() && totalZombieAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 1 Trillion Total Zombies (Current)!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZombieAch3.setCroutonDisplayed(true);
        }
        if(totalZombieAllTimeAch1.isVisible() && totalZombieAllTimeAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 150 Billion Total Zombies (Ever)!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZombieAllTimeAch1.setCroutonDisplayed(true);
        }
        if(totalZombieAllTimeAch2.isVisible() && totalZombieAllTimeAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 20 Trillion Total Zombies(Ever)!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZombieAllTimeAch2.setCroutonDisplayed(true);
        }
        if(totalZombieAllTimeAch3.isVisible() && totalZombieAllTimeAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 250 Trillion Total Zombies(Ever)!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZombieAllTimeAch3.setCroutonDisplayed(true);
        }
        if(totalZpsAch1.isVisible() && totalZpsAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Reach 1 Million Zombies/Sec!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZpsAch1.setCroutonDisplayed(true);
        }
        if(totalZpsAch2.isVisible() && totalZpsAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Reach 100 Million Zombies/Sec!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZpsAch2.setCroutonDisplayed(true);
        }
        if(totalZpsAch3.isVisible() && totalZpsAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Reach 10 Billion Zombies/Sec!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalZpsAch3.setCroutonDisplayed(true);
        }
        if(totalPerClickCountAch.isVisible() && totalPerClickCountAch.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Reach 300,000 Zombies Per Click!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalPerClickCountAch.setCroutonDisplayed(true);
        }
        if(totalUpgradeCountAch1.isVisible() && totalUpgradeCountAch1.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 50 Total Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalUpgradeCountAch1.setCroutonDisplayed(true);
        }
        if(totalUpgradeCountAch2.isVisible() && totalUpgradeCountAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 150 Total Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalUpgradeCountAch2.setCroutonDisplayed(true);
        }
        if(totalUpgradeCountAch3.isVisible() && totalUpgradeCountAch3.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Have 425 Total Abilities!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            totalUpgradeCountAch3.setCroutonDisplayed(true);
        }
        if(readCreditsAch.isVisible() && readCreditsAch.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Read The Credits!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            readCreditsAch.setCroutonDisplayed(true);
        }
        if(secretAch.isVisible() && secretAch.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Secret Achievement Achieved: Hold The Earth!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            secretAch.setCroutonDisplayed(true);
        }
        if(secretAch2.isVisible() && secretAch2.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Secret Achievement Achieved: Press 'No' To Resetting The Game!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            secretAch2.setCroutonDisplayed(true);
        }
        if(getAllAch.isVisible() && getAllAch.isCroutonDisplayed() == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Complete All Achievements!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            getAllAch.setCroutonDisplayed(true);
        }
    }

    private void checkAchievements() {
        //Check upgrade achievements
       if(UpgradeView.checkUpgrade == true) {
            if (UpgradeView.claw.getNumberOfClicksOnUpgrade() >= 50 && clawAch1.isVisible() == false) {
                achievementCount++;
                clawAch1.setVisibility(true);
            }
            if(UpgradeView.claw.getNumberOfClicksOnUpgrade() >= 65 && clawAch2.isVisible() == false) {
                achievementCount++;
                clawAch2.setVisibility(true);
            }
            if(UpgradeView.claw.getNumberOfClicksOnUpgrade() >= 100 && clawAch3.isVisible() == false) {
                achievementCount++;
                clawAch3.setVisibility(true);
            }
            if(UpgradeView.bite.getNumberOfClicksOnUpgrade() >= 40 && biteAch1.isVisible() == false) {
                achievementCount++;
                biteAch1.setVisibility(true);
            }
            if(UpgradeView.bite.getNumberOfClicksOnUpgrade() >= 50 && biteAch2.isVisible() == false) {
                achievementCount++;
                biteAch2.setVisibility(true);
            }
            if(UpgradeView.bite.getNumberOfClicksOnUpgrade() >= 60 && biteAch3.isVisible() == false) {
                achievementCount++;
                biteAch3.setVisibility(true);
            }
            if(UpgradeView.speedIncrease.getNumberOfClicksOnUpgrade() >= 25 && speedAch1.isVisible() == false) {
                achievementCount++;
                speedAch1.setVisibility(true);
            }
            if(UpgradeView.speedIncrease.getNumberOfClicksOnUpgrade() >= 30 && speedAch2.isVisible() == false) {
                achievementCount++;
                speedAch2.setVisibility(true);
            }
            if(UpgradeView.speedIncrease.getNumberOfClicksOnUpgrade() >= 40 && speedAch3.isVisible() == false) {
                achievementCount++;
                speedAch3.setVisibility(true);
            }
            if(UpgradeView.enhanceSmell.getNumberOfClicksOnUpgrade() >= 15 && smellAch1.isVisible() == false) {
                achievementCount++;
                smellAch1.setVisibility(true);
            }
            if(UpgradeView.enhanceSmell.getNumberOfClicksOnUpgrade() >= 25 && smellAch2.isVisible() == false) {
                achievementCount++;
                smellAch2.setVisibility(true);
            }
            if(UpgradeView.enhanceSmell.getNumberOfClicksOnUpgrade() >= 35 && smellAch3.isVisible() == false) {
                achievementCount++;
                smellAch3.setVisibility(true);
            }
            if(UpgradeView.jumpIncrease.getNumberOfClicksOnUpgrade() >= 35 && jumpAch1.isVisible() == false) {
                achievementCount++;
                jumpAch1.setVisibility(true);
            }
            if(UpgradeView.jumpIncrease.getNumberOfClicksOnUpgrade() >= 40 && jumpAch2.isVisible() == false) {
                achievementCount++;
                jumpAch2.setVisibility(true);
            }
            if(UpgradeView.jumpIncrease.getNumberOfClicksOnUpgrade() >= 50 && jumpAch3.isVisible() == false) {
                achievementCount++;
                jumpAch3.setVisibility(true);
            }
            if(UpgradeView.acid.getNumberOfClicksOnUpgrade() >= 10 && acidAch1.isVisible() == false) {
                achievementCount++;
                acidAch1.setVisibility(true);
            }
            if(UpgradeView.acid.getNumberOfClicksOnUpgrade() >= 15 && acidAch2.isVisible() == false) {
                achievementCount++;
                acidAch2.setVisibility(true);
            }
            if(UpgradeView.acid.getNumberOfClicksOnUpgrade() >= 20 && acidAch3.isVisible() == false) {
                achievementCount++;
                acidAch3.setVisibility(true);
            }
            if(UpgradeView.explode.getNumberOfClicksOnUpgrade() >= 5 && explodeAch1.isVisible() == false) {
                achievementCount++;
                explodeAch1.setVisibility(true);
            }
           if(UpgradeView.explode.getNumberOfClicksOnUpgrade() >= 10 && explodeAch2.isVisible() == false) {
               achievementCount++;
               explodeAch2.setVisibility(true);
           }
           if(UpgradeView.explode.getNumberOfClicksOnUpgrade() >= 15 && explodeAch3.isVisible() == false) {
               achievementCount++;
               explodeAch3.setVisibility(true);
           }
           if(UpgradeView.pounce.getNumberOfClicksOnUpgrade() >= 10 && pounceAch1.isVisible() == false) {
                achievementCount++;
                pounceAch1.setVisibility(true);
           }
           if(UpgradeView.pounce.getNumberOfClicksOnUpgrade() >= 15 && pounceAch2.isVisible() == false) {
                achievementCount++;
                pounceAch2.setVisibility(true);
           }
           if(UpgradeView.pounce.getNumberOfClicksOnUpgrade() >= 20 && pounceAch3.isVisible() == false) {
                achievementCount++;
                pounceAch3.setVisibility(true);
           }
           if(UpgradeView.charge.getNumberOfClicksOnUpgrade() >= 5 && chargeAch1.isVisible() == false) {
                achievementCount++;
                chargeAch1.setVisibility(true);
           }
           if(UpgradeView.charge.getNumberOfClicksOnUpgrade() >= 10 && chargeAch2.isVisible() == false) {
                achievementCount++;
                chargeAch2.setVisibility(true);
           }
           if(UpgradeView.charge.getNumberOfClicksOnUpgrade() >= 20 && chargeAch3.isVisible() == false) {
                achievementCount++;
                chargeAch3.setVisibility(true);
           }
           if(UpgradeView.bulletProof.getNumberOfClicksOnUpgrade() >= 15 && bulletAch1.isVisible() == false) {
               achievementCount++;
               bulletAch1.setVisibility(true);
           }
           if(UpgradeView.bulletProof.getNumberOfClicksOnUpgrade() >= 20 && bulletAch2.isVisible() == false) {
               achievementCount++;
               bulletAch2.setVisibility(true);
           }
           if(UpgradeView.bulletProof.getNumberOfClicksOnUpgrade() >= 25 && bulletAch3.isVisible() == false) {
               achievementCount++;
               bulletAch3.setVisibility(true);
           }
           if(UpgradeView.gangUp.getNumberOfClicksOnUpgrade() >= 10 && gangUpAch1.isVisible() == false) {
                achievementCount++;
                gangUpAch1.setVisibility(true);
           }
           if(UpgradeView.gangUp.getNumberOfClicksOnUpgrade() >= 15 && gangUpAch2.isVisible() == false) {
                achievementCount++;
                gangUpAch2.setVisibility(true);
           }
           if(UpgradeView.gangUp.getNumberOfClicksOnUpgrade() >= 20 && gangUpAch3.isVisible() == false) {
                achievementCount++;
                gangUpAch3.setVisibility(true);
           }
           if(UpgradeView.stealth.getNumberOfClicksOnUpgrade() >= 5 && stealthAch1.isVisible() == false) {
                achievementCount++;
                stealthAch1.setVisibility(true);
           }
           if(UpgradeView.stealth.getNumberOfClicksOnUpgrade() >= 10 && stealthAch2.isVisible() == false) {
                achievementCount++;
                stealthAch2.setVisibility(true);
           }
           if(UpgradeView.stealth.getNumberOfClicksOnUpgrade() >= 15 && stealthAch3.isVisible() == false) {
                achievementCount++;
                stealthAch3.setVisibility(true);
           }
        }
        //Always check other achievements
        if(eyeClicks >= 5 && hordeAch1.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Start 5 Hordes!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            hordeAch1.setCroutonDisplayed(true);
            hordeAch1.setVisibility(true);
        }
        if(eyeClicks >= 20 && hordeAch2.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Start 20 Hordes!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            hordeAch2.setCroutonDisplayed(true);
            hordeAch2.setVisibility(true);
        }
        if(eyeClicks >= 75 && hordeAch3.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Start 75 Hordes!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            hordeAch3.setCroutonDisplayed(true);
            hordeAch3.setVisibility(true);
        }
        if(totalClicks >= 500 && clicksOnEarthAch1.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Click 500 Times!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clicksOnEarthAch1.setCroutonDisplayed(true);
            clicksOnEarthAch1.setVisibility(true);
        }
        if(totalClicks >= 5000 && clicksOnEarthAch2.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Click 5,000 Times!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clicksOnEarthAch2.setCroutonDisplayed(true);
            clicksOnEarthAch2.setVisibility(true);
        }
        if(totalClicks >= 10000 && clicksOnEarthAch3.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Achievement Achieved: Click 10,000 Times!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            clicksOnEarthAch3.setCroutonDisplayed(true);
            clicksOnEarthAch3.setVisibility(true);
        }
        if(totalZombies >= 500000000 && totalZombieAch1.isVisible() == false) {
            achievementCount++;
            totalZombieAch1.setVisibility(true);
        }
        if(totalZombies >= 50000000000.0 && totalZombieAch2.isVisible() == false) {
            achievementCount++;
            totalZombieAch2.setVisibility(true);
        }
        if(totalZombies >= 1000000000000.0 && totalZombieAch3.isVisible() == false) {
            achievementCount++;
            totalZombieAch3.setVisibility(true);
        }
        if(totalZombiesAllTime >= 150000000000.0 && totalZombieAllTimeAch1.isVisible() == false) {
            achievementCount++;
            totalZombieAllTimeAch1.setVisibility(true);
        }
        if(totalZombiesAllTime >= 20000000000000.0 && totalZombieAllTimeAch2.isVisible() == false) {
            achievementCount++;
            totalZombieAllTimeAch2.setVisibility(true);
        }
        if(totalZombiesAllTime >= 250000000000000.0 && totalZombieAllTimeAch3.isVisible() == false) {
            achievementCount++;
            totalZombieAllTimeAch3.setVisibility(true);
        }
        if(totalZPS >= 1000000 && eyeClicked == false && totalZpsAch1.isVisible() == false) {
            achievementCount++;
            totalZpsAch1.setVisibility(true);
        }
        if(totalZPS >= 100000000 && eyeClicked == false && totalZpsAch2.isVisible() == false) {
            achievementCount++;
            totalZpsAch2.setVisibility(true);
        }
        if(totalZPS >= 10000000000.0 && eyeClicked == false && totalZpsAch3.isVisible() == false) {
            achievementCount++;
            totalZpsAch3.setVisibility(true);
        }
        if(countPerClick >= 300000 && eyeClicked == false && totalPerClickCountAch.isVisible() == false) {
            achievementCount++;
            totalPerClickCountAch.setVisibility(true);
        }

        //Stat - Total Upgrades
        if(upgradeCount >= 50 && totalUpgradeCountAch1.isVisible() == false) {
            achievementCount++;
            totalUpgradeCountAch1.setVisibility(true);
        }
        if(upgradeCount >= 150 && totalUpgradeCountAch2.isVisible() == false) {
            achievementCount++;
            totalUpgradeCountAch2.setVisibility(true);
        }
        if(upgradeCount >= 425 && totalUpgradeCountAch3.isVisible() == false) {
            achievementCount++;
            totalUpgradeCountAch3.setVisibility(true);
        }

        //Credit Ach
        if(creditRead == true && readCreditsAch.isVisible() == false) {
            achievementCount++;
            readCreditsAch.setVisibility(true);
        }

        if(startAppAch.isVisible() == false) {
            achievementCount++;
            startAppAch.setVisibility(true);
        }

        //Secret Achievement
        if(longTouch == true && secretAch.isVisible() == false) {
            achievementCount++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Crouton crouton = Crouton.makeText(MainActivity.this,"Secret Achievement Achieved: Hold The Earth!",Style.CONFIRM);
                    crouton.setConfiguration(new Configuration.Builder().setDuration(
                            Configuration.DURATION_LONG).build());
                    crouton.show();
                }
            });
            secretAch.setCroutonDisplayed(true);
            secretAch.setVisibility(true);
        }
        //Secret Achievement 2
        if(noReset == true && secretAch2.isVisible() == false) {
            achievementCount++;
            secretAch2.setVisibility(true);
            secretAch2.setAchievementDescription("Press 'NO' To Resetting!");
        }
        //All Achievements
        if(achievementCount == 59 && getAllAch.isVisible() == false) {
            achievementCount++;
            getAllAch.setVisibility(true);
        }
    }

    private void onEyeClick(final ImageButton randomEye) {
        randomEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipDisplayed == true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tempTip.setVisibility(View.INVISIBLE);
                        }
                    });
                }

                eyeClicks++;
                if(mute == false) {
                    playHordeSound();
                }
                transparentEye.setVisibility(View.VISIBLE);
                hordeMessage.setVisibility(View.VISIBLE);

                Vibrator vibrateObj = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                int vibrateTime = 70000 - eyeMilliseconds;
                vibrateObj.vibrate(vibrateTime);

                startHordeBlood = true;
                eyeClicked = true;

                changeZps = Math.floor(totalZombies * .1);
                totalZPS += changeZps;

                double doubleNum = Double.valueOf(totalZPS);
                zpsTextView.setText("Per Second: " + NumberFormat.getInstance().format(doubleNum));

                changeCountPerClick = countPerClick * 10;
                countPerClick += changeCountPerClick;

                String stringNumClick = df.format(countPerClick);
                double doubleNumClick = Double.valueOf(stringNumClick);

                clickView.setText("Per Click: " + NumberFormat.getInstance().format(doubleNumClick));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        randomEye.setVisibility(View.INVISIBLE);
                    }
                });
                backButton.setClickable(false);
                achievementButton.setClickable(false);
                upgradeButton.setClickable(false);
                statButton.setClickable(false);
            }
        });
    }

    private ImageButton getRandomNumberEye() {
        ImageButton tempEye = null;
        int ran = random.nextInt(5);
        if (ran == 1) {
            tempEye = eye;
        } else if (ran == 2) {
            tempEye = eye2;
        } else if (ran == 3) {
            tempEye = eye3;
        } else if (ran == 4) {
            tempEye = eye4;
        } else {
            tempEye = eye5;
        }
        return tempEye;
    }

    private void checkMilliseconds() {
        eyeMilliseconds = eyeMilliseconds + 1000;
        removeEye = removeEye + 1000;
        if(eyeMilliseconds >= 60000) {
            if (eyeClicked == false && eyeDisplayed == false) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageEye = getRandomNumberEye();
                        eyeDisplayed = true;
                        imageEye.setVisibility(View.VISIBLE);
                        onEyeClick(imageEye);
                    }
                });
            }
        }
    }

    private void checkRemoveEyeMilliseconds() {
        if(removeEye >= 70000) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageEye.setVisibility(View.INVISIBLE);
                    transparentEye.setVisibility(View.INVISIBLE);
                    hordeMessage.setVisibility(View.INVISIBLE);
                    if (eyeClicked == true) {
                        totalZPS = totalZPS - changeZps;
                        changeZps = 0;

                        countPerClick = countPerClick - changeCountPerClick;
                        changeCountPerClick = 0;
                    }
                    double doubleNum = Double.valueOf(totalZPS);
                    zpsTextView.setText("Per Second: " + NumberFormat.getInstance().format(doubleNum));

                    String stringNumClick = df.format(countPerClick);
                    double doubleNumClick = Double.valueOf(stringNumClick);

                    clickView.setText("Per Click: " + NumberFormat.getInstance().format(doubleNumClick));

                    backButton.setClickable(true);
                    achievementButton.setClickable(true);
                    upgradeButton.setClickable(true);
                    statButton.setClickable(true);

                    removeEye = 0;
                    eyeMilliseconds = 0;
                    startHordeBlood = false;
                    eyeDisplayed = false;
                    eyeClicked = false;
                }
            });
        }
    }

    private void checkTipMessage() {
        tipMessageMs = tipMessageMs + 1000;
        removeTipMessageMs = removeTipMessageMs + 1000;
        pauseTipMessageMs = pauseTipMessageMs + 1000;
        if(tipMessageMs >= 10000 && tipInPause == false) {
            if(eyeClicked == false && tipDisplayed == false && eyeDisplayed == false) {
                int ran = random.nextInt(6);
                tempTip = tipList.get(ran);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tempTip.setVisibility(View.VISIBLE);
                        tipDisplayed = true;
                    }
                });
            }
        }
    }

    private void checkRemoveTipMessage() {
        if(removeTipMessageMs >= 20000 && tipInPause == false) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tempTip.setVisibility(View.INVISIBLE);
                    tipDisplayed = false;
                }
            });
        }
    }

    private void checkInPauseTip() {
        if(pauseTipMessageMs >= 35000) {
            tipMessageMs = 0;
            removeTipMessageMs = 0;
            pauseTipMessageMs = 0;
        }
    }

    private void checkResetAllEyesAndTips() {
        int eyeBallVisibleCount = 0;
        int tipListVisibleCount = 0;
        for(int i = 0; i < eyeBallList.size(); ++i) {
           if(eyeBallList.get(i).getVisibility() == View.VISIBLE) {
               eyeBallVisibleCount++;
            }
        }
        for(int i = 0; i < tipList.size(); ++i) {
            if(tipList.get(i).getVisibility() == View.VISIBLE) {
                tipListVisibleCount++;
            }
        }
        if(tipListVisibleCount > 1 || eyeBallVisibleCount > 1) {
            Log.e(TAG,"RESETTING");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    eye.setVisibility(View.INVISIBLE);
                    eye2.setVisibility(View.INVISIBLE);
                    eye3.setVisibility(View.INVISIBLE);
                    eye4.setVisibility(View.INVISIBLE);
                    eye5.setVisibility(View.INVISIBLE);
                    tip.setVisibility(View.INVISIBLE);
                    tip2.setVisibility(View.INVISIBLE);
                    tip3.setVisibility(View.INVISIBLE);
                    tip4.setVisibility(View.INVISIBLE);
                    tip5.setVisibility(View.INVISIBLE);
                    tip6.setVisibility(View.INVISIBLE);

                    removeEye = 0;
                    eyeMilliseconds = 0;
                    startHordeBlood = false;
                    eyeDisplayed = false;
                    eyeClicked = false;

                    tipMessageMs = 0;
                    removeTipMessageMs = 0;
                    pauseTipMessageMs = 0;
                }
            });
        }
    }

    private void runAlways() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAchievements();
                checkResetAllEyesAndTips();
                checkMilliseconds();
                checkRemoveEyeMilliseconds();
                checkTipMessage();
                checkRemoveTipMessage();
                checkInPauseTip();
                new MyAsyncTask().execute(totalZombies);
            }
        }, 0, 1000);
    }

    private void onStatButtonClick() {
        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mute == false) {
                    playButtonClickSound();
                }
                Intent statIntent = new Intent(MainActivity.this, StatView.class);
                startActivity(statIntent);
            }
        });
    }

    private void setSoundDisplay() {
        if(mute == false) {
            soundButton.setImageResource(R.drawable.notmute);
        } else if(mute == true) {
            soundButton.setImageResource(R.drawable.mute);
        }
    }

    private void onSoundButtonClick() {
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mute == false) {
                    mute = true;
                    soundButton.setImageResource(R.drawable.mute);
                } else if(mute == true) {
                    mute = false;
                    soundButton.setImageResource(R.drawable.notmute);
                }
            }
        });
    }

    private void onImageButtonClick() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mute == false) {
                    playZombieSound();
                }

                biggerEarth.setVisibility(View.VISIBLE);
                startPopAnimation();

                totalClicks++;
                totalZombies = totalZombies + countPerClick;
                handMadeZombies = handMadeZombies + countPerClick;
                totalZombiesAllTime = totalZombiesAllTime + countPerClick;

                String stringNum = df.format(totalZombies);
                double doubleNum = Double.valueOf(stringNum);

                textView.setText(NumberFormat.getInstance().format(doubleNum));

                biggerEarth.post(new Runnable() {
                    @Override
                    public void run() {
                        new Timer().scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        biggerEarth.performClick();
                                    }
                                });
                            }
                        }, 0, 5000);
                    }
                });
                changeEarth();
            }
        });
    }

    private void changeEarth() {
        //Reach 1 mil
        if(totalZombies >= 1000000) {
            imageButton.setImageResource(R.drawable.earthokay);
            biggerEarth.setImageResource(R.drawable.bigearthokay);
        }
        //Reach 100 mil
        if(totalZombies >= 100000000) {
            imageButton.setImageResource(R.drawable.earthbad);
            biggerEarth.setImageResource(R.drawable.bigearthbad);
        }
    }

    private void onImageButtonLongClick() {
        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                secretAch.setAchievementDescription("Hold The Earth!");
                longTouch = true;
                return false;
            }
        });
    }


    private void onBackButtonClick() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mute == false) {
                    playButtonClickSound();
                }
                Intent intent = new Intent(MainActivity.this,Start.class);
                startActivity(intent);
            }
        });
    }

    private void onAchievementButtonClick() {
        achievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mute == false) {
                    playButtonClickSound();
                }
                Intent achievementIntent = new Intent(MainActivity.this,AchievementView.class);
                startActivity(achievementIntent);
            }
        });
    }

    private void onUpgradeButtonClick() {
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mute == false) {
                    playButtonClickSound();
                }
                Intent intent = new Intent(MainActivity.this,UpgradeView.class);
                startActivity(intent);
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Double,Double,Double> {

        @Override
        protected Double doInBackground(Double... params) {
            totalZombies = totalZombies + totalZPS;
            totalZombiesAllTime = totalZombiesAllTime + totalZPS;
            return totalZombies;
        }

        @Override
        protected void onPostExecute(Double dub) {
            startBloodDropAnimation();
            if(startHordeBlood == true) {
                startHordeBloodAnimation();
            } else {
                hordeDrop.setVisibility(View.INVISIBLE);
                hordeDrop2.setVisibility(View.INVISIBLE);
                hordeDrop3.setVisibility(View.INVISIBLE);
                hordeDrop4.setVisibility(View.INVISIBLE);
                hordeDrop5.setVisibility(View.INVISIBLE);
                hordeDrop6.setVisibility(View.INVISIBLE);
                hordeDrop7.setVisibility(View.INVISIBLE);
                hordeDrop8.setVisibility(View.INVISIBLE);
                hordeDrop9.setVisibility(View.INVISIBLE);
                hordeDrop10.setVisibility(View.INVISIBLE);
                hordeDrop11.setVisibility(View.INVISIBLE);
                hordeDrop12.setVisibility(View.INVISIBLE);
                hordeDrop13.setVisibility(View.INVISIBLE);
                hordeDrop14.setVisibility(View.INVISIBLE);
                hordeDrop15.setVisibility(View.INVISIBLE);
                hordeDrop16.setVisibility(View.INVISIBLE);
                hordeDrop17.setVisibility(View.INVISIBLE);
                hordeDrop18.setVisibility(View.INVISIBLE);
                hordeDrop19.setVisibility(View.INVISIBLE);
                hordeDrop20.setVisibility(View.INVISIBLE);
                hordeDrop21.setVisibility(View.INVISIBLE);
                hordeDrop22.setVisibility(View.INVISIBLE);
                hordeDrop23.setVisibility(View.INVISIBLE);
                hordeDrop24.setVisibility(View.INVISIBLE);
                hordeDrop25.setVisibility(View.INVISIBLE);
            }

            String stringNum = df.format(totalZombies);
            double doubleNum = Double.valueOf(stringNum);

            textView.setText(NumberFormat.getInstance().format(doubleNum));
            super.onPostExecute(dub);
        }

    }
}
