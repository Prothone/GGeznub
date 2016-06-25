package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ScorePage extends AppCompatActivity {
    Bundle receivedBundle;
    int stage;
    int RedScore;
    int BlueScore;
    int RedScoreThis;
    int BlueScoreThis;
    TextView stageView;
    TextView redView;
    TextView blueView;
    TextView redThisView;
    TextView blueThisView;
    ImageView redMedal;
    ImageView redNub;
    ImageView blueMedal;
    ImageView blueNub;
    AnimatorSet Medal;
    AnimatorSet Nub;
    Intent i;
    MediaPlayer medal;
    MediaPlayer nub;
    MediaPlayer BG;
    MediaPlayer scoreadd;
    boolean tiebreaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score_page);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore") ;
            BlueScore = receivedBundle.getInt("BlueScore");
            RedScoreThis = receivedBundle.getInt("RedScoreThis");
            BlueScoreThis = receivedBundle.getInt("BlueScoreThis");
        }
//        stage = 1;
//        RedScore = 0;
//        BlueScore = 0;
//        RedScoreThis = 86;
//        BlueScoreThis = 43;

        stageView = (TextView) findViewById(R.id.stage);
        redView = (TextView) findViewById(R.id.txtRed);
        blueView = (TextView) findViewById(R.id.txtBlue);
        redThisView = (TextView) findViewById(R.id.txtRedThis);
        blueThisView = (TextView) findViewById(R.id.txtBlueThis);

        redMedal = (ImageView) findViewById(R.id.redMedal);
        redNub = (ImageView) findViewById(R.id.redNub);
        blueMedal = (ImageView) findViewById(R.id.blueMedal);
        blueNub = (ImageView) findViewById(R.id.blueNub);
        medal = MediaPlayer.create(this, R.raw.medal);
        nub = MediaPlayer.create(this, R.raw.boo);
        scoreadd = MediaPlayer.create(this, R.raw.scoreadd);
        BG = MediaPlayer.create(this, R.raw.result);
        BG.start();

        stageView.setText("Stage "+stage);
        redThisView.setText("Score :\n"+RedScoreThis);
        blueThisView.setText("Score :\n"+BlueScoreThis);
        redView.setText(""+RedScore);
        blueView.setText(""+BlueScore);

        if(stage == 3){
            i = new Intent(this,Ending.class);
        }
        else {
            i = new Intent(this,Intro1.class);
        }

        Medal = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Nub = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);

        if(RedScoreThis > BlueScoreThis){
            Medal.setTarget(redMedal);
            Nub.setTarget(blueNub);
//            RedScore++;
        }
        else if(RedScoreThis < BlueScoreThis){
            Medal.setTarget(blueMedal);
            Nub.setTarget(redNub);
//            BlueScore++;
        }
        else{
            Random rnd = new Random();
            tiebreaker = rnd.nextBoolean();
            if(tiebreaker){
                Medal.setTarget(redMedal);
                Nub.setTarget(blueNub);
            }
            else{
                Medal.setTarget(blueMedal);
                Nub.setTarget(redNub);
            }
        }

        Handler handlerS = new Handler();
        handlerS.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(RedScoreThis > BlueScoreThis){
                    redMedal.setVisibility(View.VISIBLE);
                    redThisView.setVisibility(View.INVISIBLE);
                }
                else if(RedScoreThis < BlueScoreThis){
                    blueMedal.setVisibility(View.VISIBLE);
                    blueThisView.setVisibility(View.INVISIBLE);
                }
                else{
                    if(tiebreaker){
                        redMedal.setVisibility(View.VISIBLE);
                        redThisView.setVisibility(View.INVISIBLE);
                    }
                    else{
                        blueMedal.setVisibility(View.VISIBLE);
                        blueThisView.setVisibility(View.INVISIBLE);
                    }
                }
                Medal.start();
                medal.start();
            }
        }, 2000);

        Handler handlerM = new Handler();
        handlerM.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(RedScoreThis > BlueScoreThis){
                    blueNub.setVisibility(View.VISIBLE);
                    blueThisView.setVisibility(View.INVISIBLE);
                }
                else if(RedScoreThis < BlueScoreThis){
                    redNub.setVisibility(View.VISIBLE);
                    redThisView.setVisibility(View.INVISIBLE);
                }
                else{
                    if(tiebreaker){
                        blueNub.setVisibility(View.VISIBLE);
                        blueThisView.setVisibility(View.INVISIBLE);
                    }
                    else{
                        redNub.setVisibility(View.VISIBLE);
                        redThisView.setVisibility(View.INVISIBLE);
                    }
                }
                Nub.start();
                nub.start();
            }
        }, 4000);

        Handler handlerN = new Handler();
        handlerN.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(RedScoreThis > BlueScoreThis){
                    Medal.setTarget(redView);
                    redView.setText(String.valueOf(++RedScore));
                }
                else if(RedScoreThis < BlueScoreThis){
                    Medal.setTarget(blueView);
                    blueView.setText(String.valueOf(++BlueScore));
                }
                else{
                    if(tiebreaker){
                        Medal.setTarget(redView);
                        redView.setText(String.valueOf(++RedScore));
                    }
                    else{
                        Medal.setTarget(blueView);
                        blueView.setText(String.valueOf(++BlueScore));
                    }
                }
                i.putExtra("stage", ++stage);
                i.putExtra("RedScore",RedScore);
                i.putExtra("BlueScore",BlueScore);
                Medal.start();
                scoreadd.start();
            }
        }, 7000);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BG.stop();
                startActivity(i);
                finish();
            }
        }, 12000);
    }
}
