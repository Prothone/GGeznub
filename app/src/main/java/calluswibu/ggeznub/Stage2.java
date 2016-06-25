package calluswibu.ggeznub;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Stage2 extends AppCompatActivity{
    boolean redState;
    boolean blueState;
    Button redRight;
    Button redLeft;
    Button blueRight;
    Button blueLeft;
    TextView cdRed;
    TextView cdBlue;
    AnimationDrawable redFlipToggle;
    AnimationDrawable blueFlipToggle;
    ImageView catcherRed;
    ImageView catcherBlue;
    ImageView foodRed;
    ImageView foodBlue;
    int[][] foodSpawn;
    ValueAnimator foodDropRed;
    ValueAnimator foodDropBlue;
    Random rnd;
    int redCount;
    int blueCount;
    TextView redScoreView;
    TextView blueScoreView;
    boolean loc;
    ImageView redWin;
    ImageView blueWin;
    Bundle receivedBundle;
    int stage;
    int BlueScore;
    int RedScore;
    Intent i;
    MediaPlayer BG;
    MediaPlayer freefall;
    MediaPlayer clink;
    MediaPlayer whistle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stage2);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        redState = true;
        blueState = true;
        redCount = 0;
        blueCount = 0;

        foodSpawn = new int[2][];
        for(int x = 0; x < 2; x++){
            foodSpawn[x] = new int[3];
        }
        foodSpawn[0][0] = 2 * 320;  //pos Y
        foodSpawn[0][1] = 2 * 60;   //pos X left
        foodSpawn[0][2] = 2 * 207;  //pos X right
        foodSpawn[1][0] = 2 * 220;
        foodSpawn[1][1] = 2 * 60;
        foodSpawn[1][2] = 2 * 207;

        foodDropBlue = ObjectAnimator.ofFloat(foodBlue,"y",foodSpawn[0][1],foodSpawn[0][1]+400);
        foodDropBlue.setDuration(5000);
        foodDropRed = ObjectAnimator.ofFloat(foodRed,"y",foodSpawn[0][1],foodSpawn[0][1]+400);
        foodDropRed.setDuration(5000);

        redRight = (Button) findViewById(R.id.redRight);
        redLeft = (Button) findViewById(R.id.redLeft);
        blueRight = (Button) findViewById(R.id.blueRight);
        blueLeft = (Button) findViewById(R.id.blueLeft);
        catcherRed = (ImageView) findViewById(R.id.catcherRed);
        catcherBlue = (ImageView) findViewById(R.id.catcherBlue);
        foodRed = (ImageView) findViewById(R.id.foodRed);
        foodBlue = (ImageView) findViewById(R.id.foodBlue);
        redScoreView = (TextView)findViewById(R.id.redScoreView) ;
        blueScoreView = (TextView)findViewById(R.id.blueScoreView);
        redWin = (ImageView) findViewById(R.id.redWin);
        blueWin = (ImageView) findViewById(R.id.blueWin);
        rnd = new Random();

        BG = MediaPlayer.create(this, R.raw.meltpanic);
        freefall = MediaPlayer.create(this, R.raw.freefall);
        clink = MediaPlayer.create(this, R.raw.clink);
        whistle = MediaPlayer.create(this, R.raw.whistle);
        BG.start();

        catcherRed.setBackgroundResource(R.drawable.catcherredflipleft);
        redFlipToggle = (AnimationDrawable) catcherRed.getBackground();
        catcherBlue.setBackgroundResource(R.drawable.catcherflipleft);
        blueFlipToggle = (AnimationDrawable) catcherBlue.getBackground();

        redRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!redState){
                    catcherRed.setBackgroundResource(R.drawable.catcherredflipright);
                    redFlipToggle = (AnimationDrawable) catcherRed.getBackground();
                    redState = true;
                    redFlipToggle.start();
                }
            }
        });
        redLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(redState){
                    catcherRed.setBackgroundResource(R.drawable.catcherredflipleft);
                    redFlipToggle = (AnimationDrawable) catcherRed.getBackground();
                    redState = false;
                    redFlipToggle.start();
                }
            }
        });
        blueRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!blueState){
                    catcherBlue.setBackgroundResource(R.drawable.catcherflipright);
                    blueFlipToggle = (AnimationDrawable) catcherBlue.getBackground();
                    blueState = true;
                    blueFlipToggle.start();
                }
            }
        });
        blueLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(blueState){
                    catcherBlue.setBackgroundResource(R.drawable.catcherflipleft);
                    blueFlipToggle = (AnimationDrawable) catcherBlue.getBackground();
                    blueState = false;
                    blueFlipToggle.start();
                }
            }
        });

        cdRed = (TextView) findViewById(R.id.cdRed);
        cdBlue = (TextView) findViewById(R.id.cdBlue);

        redRight.setEnabled(false);
        redLeft.setEnabled(false);
        blueRight.setEnabled(false);
        blueLeft.setEnabled(false);

        i = new Intent(this, ScorePage.class);
        i.putExtra("stage", stage);
        i.putExtra("RedScore",RedScore);
        i.putExtra("BlueScore",BlueScore);

        new CountDownTimer(4000, 1000) {
            public void onTick(long mili) {
                cdRed.setText("" + (Math.round(mili / 1000)));
                cdBlue.setText("" + (Math.round(mili / 1000)));
            }

            public void onFinish() {
                cdRed.setText("GO!");
                cdBlue.setText("GO!");
                redRight.setEnabled(true);
                redLeft.setEnabled(true);
                blueRight.setEnabled(true);
                blueLeft.setEnabled(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cdRed.setVisibility(View.INVISIBLE);
                        cdBlue.setVisibility(View.INVISIBLE);
                    }
                }, 1500);
                new CountDownTimer(15000, 800) {

                    public void onTick(long mili) {
                        freefall.start();
                        loc = rnd.nextBoolean();
                        final int x;
                        if(loc){x=1;}else{x=0;}
                        foodBlue.setX(foodSpawn[0][x+1]);
                        foodDropBlue = ObjectAnimator.ofFloat(foodBlue,"y",foodSpawn[0][0],foodSpawn[0][0]+700);
                        foodDropBlue.setDuration(600);
                        foodBlue.setVisibility(View.VISIBLE);
                        foodDropBlue.start();

                        Handler handlerB = new Handler();
                        handlerB.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(blueState == loc){
                                    blueCount++;
                                    blueScoreView.setText(""+blueCount);
                                    foodBlue.setVisibility(View.INVISIBLE);
                                    clink.start();
                                }
                            }
                        }, 400);

                        boolean loc2 = rnd.nextBoolean();
                        int x2;
                        if(loc2){x2=1;}else{x2=0;}
                        foodRed.setX(foodSpawn[1][2-x2]);
                        foodDropRed = ObjectAnimator.ofFloat(foodRed,"y",foodSpawn[1][0],foodSpawn[1][0]-700);
                        foodDropRed.setDuration(600);
                        foodRed.setVisibility(View.VISIBLE);
                        foodDropRed.start();

                        Handler handlerR = new Handler();
                        handlerR.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(redState == loc){
                                    redCount++;
                                    redScoreView.setText(""+redCount);
                                    foodRed.setVisibility(View.INVISIBLE);
                                    clink.start();
                                }
                            }
                        }, 400);

                        if(mili < 5100){
                            cdRed.setVisibility(View.VISIBLE);
                            cdBlue.setVisibility(View.VISIBLE);
                            cdRed.setText("" + (Math.round(mili / 1000)));
                            cdBlue.setText("" + (Math.round(mili / 1000)));
                        }
                    }
                    public void onFinish() {
                        whistle.start();
                        redRight.setEnabled(false);
                        redLeft.setEnabled(false);
                        blueRight.setEnabled(false);
                        blueLeft.setEnabled(false);
                        cdRed.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);
                        cdBlue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);
                        cdRed.setText("GAME SET!");
                        cdBlue.setText("GAME SET!");
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(redCount < blueCount){
                                    blueWin.setVisibility(View.VISIBLE);
                                    cdRed.setText("LOSER");
                                    cdBlue.setTextColor(Color.parseColor("#FFEC00"));
                                    cdBlue.setAlpha(1);
                                    cdBlue.setText("KING!");
                                }
                                else if(redCount > blueCount){
                                    redWin.setVisibility(View.VISIBLE);
                                    cdRed.setTextColor(Color.parseColor("#FFEC00"));
                                    cdRed.setAlpha(1);
                                    cdRed.setText("KING!");
                                    cdBlue.setText("LOSER");
                                }
                                else{
                                    blueWin.setVisibility(View.VISIBLE);
                                    cdBlue.setTextColor(Color.parseColor("#FFEC00"));
                                    cdBlue.setAlpha(1);
                                    cdBlue.setText("KING!");
                                    redWin.setVisibility(View.VISIBLE);
                                    cdRed.setTextColor(Color.parseColor("#FFEC00"));
                                    cdRed.setAlpha(1);
                                    cdRed.setText("KING!");
                                }
                                i.putExtra("RedScoreThis", redCount);
                                i.putExtra("BlueScoreThis", blueCount);
                            }
                        }, 5000);

                        Handler handlerK = new Handler();
                        handlerK.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(i);
                                finish();
                            }
                        }, 9000);
                    }
                }.start();
            }
        }.start();
    }
}

