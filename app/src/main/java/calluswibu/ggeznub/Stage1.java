package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Stage1 extends AppCompatActivity {
    ImageView redBtn;
    ImageView blueBtn;
    ImageView redCore;
    ImageView blueCore;
    ImageView redClick;
    ImageView blueClick;
    ImageView Time;
    AnimationDrawable redClickAnim;
    AnimationDrawable blueClickAnim;
    ValueAnimator redClickMove;
    ValueAnimator blueClickMove;
    ValueAnimator redCoreMove;
    ValueAnimator blueCoreMove;
    int redCount;
    int blueCount;
    TextView redScore;
    TextView blueScore;
    TextView cdRed;
    TextView cdBlue;
    Intent i;
    int[] handPos;
    int[][] corePos;
    int[] tmp;
    int TimeLength;
    Bundle receivedBundle;
    int stage;
    int RedScore;
    int BlueScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stage1);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        redClick = (ImageView) findViewById(R.id.redClick);
        blueClick = (ImageView) findViewById(R.id.blueClick);
        redBtn = (ImageView) findViewById(R.id.btnRed);
        blueBtn = (ImageView) findViewById(R.id.btnBlue);
        redCore = (ImageView) findViewById(R.id.redCore);
        blueCore = (ImageView) findViewById(R.id.blueCore);
        redScore = (TextView) findViewById(R.id.redScore);
        blueScore = (TextView) findViewById(R.id.blueScore);
        cdRed = (TextView) findViewById(R.id.cdRed);
        cdBlue = (TextView) findViewById(R.id.cdBlue);
        Time = (ImageView) findViewById(R.id.Time);

//        ViewGroup.LayoutParams params= Time.getLayoutParams();
        TimeLength = 720 ;
//        params.width = TimeLength;
//        Time.setLayoutParams(params);

        redClick.setBackgroundResource(R.drawable.mecpenclick);
        redClickAnim = (AnimationDrawable) redClick.getBackground();

        blueClick.setBackgroundResource(R.drawable.mecpenclick);
        blueClickAnim = (AnimationDrawable) blueClick.getBackground();

        redCount=0;
        blueCount=0;

        i = new Intent(this, ScorePage.class);
        i.putExtra("stage",stage);

        handPos = new int[2];
        tmp = new int[2];
        corePos = new int[8][];
        for(int j = 0; j<8;j++){
            corePos[j] = new int[2];
        }

        corePos[0][0] = 2 * 179;
        corePos[0][1] = 2 * 126;
        corePos[1][0] = 2 * 187;
        corePos[1][1] = 2 * 101;
        corePos[2][0] = 2 * 196;
        corePos[2][1] = 2 * 75;
        corePos[3][0] = 2 * 205;
        corePos[3][1] = 2 * 50;
        corePos[4][0] = 2 * 90;
        corePos[4][1] = 2 * 506;
        corePos[5][0] = 2 * 80;
        corePos[5][1] = 2 * 530;
        corePos[6][0] = 2 * 74;
        corePos[6][1] = 2 * 550;
        corePos[7][0] = 2 * 67;
        corePos[7][1] = 2 * 569;

        redClickMove = ObjectAnimator.ofFloat(redClick,"y",118,138);
        redClickMove.setDuration(150);
        blueClickMove = ObjectAnimator.ofFloat(blueClick,"y",744,724);
        blueClickMove.setDuration(150);
        redCoreMove = ObjectAnimator.ofFloat(redCore,"y",corePos[(blueCount%4)][1]+20,corePos[(blueCount%4)][1]);
        blueCoreMove = ObjectAnimator.ofFloat(blueCore,"y",corePos[(blueCount%4)+4][1]+20,corePos[(blueCount%4)+4][1]);
        redCoreMove.setDuration(150);
        blueCoreMove.setDuration(150);

        redBtn.setEnabled(false);
        blueBtn.setEnabled(false);

        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redClickAnim.stop();
                redClickAnim.start();
                redCount++;
                redCoreMove = ObjectAnimator.ofFloat(redCore,"y",corePos[redCount%4][1]-20,corePos[redCount%4][1]);
                redCoreMove.end();
                redClickMove.end();
                redCoreMove.start();
                redClickMove.start();
                redCore.setX(corePos[(redCount%4)][0]);
                if(redCount%4==0){
                    redScore.setText(String.valueOf((int) Math.floor(redCount/4)));
                    redCore.setX(426);
                    redCoreMove = ObjectAnimator.ofFloat(redCore,"y",30,-100);
                    redCoreMove.end();
                    redCoreMove.start();
                }
            }
        });

        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueClickAnim.stop();
                blueClickAnim.start();
                blueCount++;
                blueCoreMove = ObjectAnimator.ofFloat(blueCore,"y",corePos[(blueCount%4)+4][1]+20,corePos[(blueCount%4)+4][1]);
                blueCoreMove.end();
                blueClickMove.end();
                blueCoreMove.start();
                blueClickMove.start();
                blueCore.setX(corePos[(blueCount%4)+4][0]);
                if(blueCount%4==0){
                    blueScore.setText(String.valueOf((int) Math.floor(blueCount/4)));
                    blueCore.setX(110);
                    blueCoreMove = ObjectAnimator.ofFloat(blueCore,"y",1196,1500);
                    blueCoreMove.end();
                    blueCoreMove.start();
                }
            }
        });

        new CountDownTimer(4000, 1000) {
            public void onTick(long mili) {
                cdRed.setText("" + (Math.round(mili / 1000)));
                cdBlue.setText("" + (Math.round(mili / 1000)));
            }

            public void onFinish() {
                cdRed.setText("GO!");
                cdBlue.setText("GO!");
                blueBtn.setEnabled(true);
                redBtn.setEnabled(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cdRed.setVisibility(View.INVISIBLE);
                        cdBlue.setVisibility(View.INVISIBLE);
                    }
                }, 1500);
                new CountDownTimer(15000, 100) {

                    public void onTick(long mili) {
                        ViewGroup.LayoutParams params= Time.getLayoutParams();
                        TimeLength -= 5;
                        params.width = TimeLength;
                        Time.setLayoutParams(params);

                        if(mili < 5100){
                            cdRed.setVisibility(View.VISIBLE);
                            cdBlue.setVisibility(View.VISIBLE);
                            cdRed.setText("" + (Math.round(mili / 1000)));
                            cdBlue.setText("" + (Math.round(mili / 1000)));
                        }
                    }
//
                    public void onFinish() {
                        redBtn.setEnabled(false);
                        blueBtn.setEnabled(false);
//                        Time.setTextColor(getResources().getColor(R.color.Black));
//                        Red.setEnabled(false);
//                        Blue.setEnabled(false);
//                        Time.setText("END!");
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

//                        if(redCount < blueCount){
//                            cdRed.setText("BLUE WINS, NUB!");
//                            cdBlue.setText("BLUE WINS! GG!");
//                            i.putExtra("RedScore",0);
//                            i.putExtra("BlueScore",0);
//                        }
//                        else if(redCount > blueCount){
//                            cdRed.setText("RED WINS! GG!");
//                            cdBlue.setText("RED WINS, NUB!");
//                            i.putExtra("RedScore",0);
//                            i.putExtra("BlueScore",0);
//                        }
//                        else{
//                            cdRed.setText("TIED!");
//                            cdBlue.setText("TIED!");
//                        }

//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
                        cdRed.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);
                        cdBlue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);
                                cdRed.setText("GAME SET!");
                                cdBlue.setText("GAME SET!");
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(redCount < blueCount){
                                        cdRed.setText("BLUE WINS, NUB!");
                                        cdBlue.setText("BLUE WINS! GG!");
                                        i.putExtra("RedScore",RedScore);
                                        i.putExtra("BlueScore",BlueScore+1);
                                    }
                                    else if(redCount > blueCount){
                                        cdRed.setText("RED WINS! GG!");
                                        cdBlue.setText("RED WINS, NUB!");
                                        i.putExtra("RedScore",RedScore+1);
                                        i.putExtra("BlueScore",BlueScore);
                                    }
                                    else{
                                        cdRed.setText("TIED!");
                                        cdBlue.setText("TIED!");
                                        i.putExtra("RedScore",RedScore);
                                        i.putExtra("BlueScore",BlueScore);
                                    }
                                    }
                                }, 5000);
//                            }
//                        }, 5000);

                        Handler handlerK = new Handler();
                        handlerK.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(i);
                                finish();
                            }
                        }, 8000);
                    }
                }.start();
            }
        }.start();
    }
}
