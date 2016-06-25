package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Tutor2 extends AppCompatActivity {
    boolean redState;
    boolean blueState;
    Button redRight;
    Button redLeft;
    Button blueRight;
    Button blueLeft;
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
    Bundle receivedBundle;
    int stage;
    int BlueScore;
    int RedScore;
    Intent i;
    ImageView redPoint;
    ImageView bluePoint;
    AnimatorSet redPressRight;
    AnimatorSet bluePressRight;
    AnimatorSet redPressLeft;
    AnimatorSet bluePressLeft;
    boolean redLast;
    boolean blueLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutor2);
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
        rnd = new Random();
        redPoint=(ImageView) findViewById(R.id.tutorHandRed);
        bluePoint=(ImageView) findViewById(R.id.tutorHandBlue);
        redPressRight = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.redtutor);
        redPressRight.setTarget(redPoint);
        bluePressRight = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.bluetutor);
        bluePressRight.setTarget(bluePoint);
        redPressLeft = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.redtutorleft);
        redPressLeft.setTarget(redPoint);
        bluePressLeft = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.bluetutorleft);
        bluePressLeft.setTarget(bluePoint);

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

        redRight.setEnabled(false);
        redLeft.setEnabled(false);
        blueRight.setEnabled(false);
        blueLeft.setEnabled(false);

        i = new Intent(this, Stage2.class);
        i.putExtra("stage", stage);
        i.putExtra("RedScore",RedScore);
        i.putExtra("BlueScore",BlueScore);


        new CountDownTimer(8500, 800) {
            public void onTick(long mili) {
                loc = rnd.nextBoolean();
                final int x;
                if(loc){x=1;}else{x=0;}
                foodBlue.setX(foodSpawn[0][x+1]);
                foodDropBlue = ObjectAnimator.ofFloat(foodBlue,"y",foodSpawn[0][0],foodSpawn[0][0]+700);
                foodDropBlue.setDuration(1200);
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
                        }
                    }
                }, 800);

                boolean loc2 = rnd.nextBoolean();
                int x2;
                if(loc2){x2=1;}else{x2=0;}
                foodRed.setX(foodSpawn[1][2-x2]);
                foodDropRed = ObjectAnimator.ofFloat(foodRed,"y",foodSpawn[1][0],foodSpawn[1][0]-700);
                foodDropRed.setDuration(1200);
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
                        }
                    }
                }, 800);
            }
            public void onFinish() {
                startActivity(i);
                finish();
            }
        }.start();


        new CountDownTimer(15000, 400) {
            public void onTick(long mili) {
                boolean X = rnd.nextBoolean();
                if(X && !blueLast){
                    blueLeft.performClick();
                    bluePressLeft.end();
                    bluePressLeft.start();
                }
                else if(!X && blueLast){
                    blueRight.performClick();
                    bluePressRight.end();
                    bluePressRight.start();
                }
                blueLast = X;
            }
            public void onFinish() {
            }
        }.start();

        new CountDownTimer(15000, 300) {
            public void onTick(long mili) {
                boolean Y = rnd.nextBoolean();
                if(Y & !redLast){
                    redLeft.performClick();
                    redPressLeft.end();
                    redPressLeft.start();
                }
                else if(!Y && redLast){
                    redRight.performClick();
                    redPressRight.end();
                    redPressRight.start();
                }
                redLast = Y;
            }
            public void onFinish() {
            }
        }.start();
    }
}
