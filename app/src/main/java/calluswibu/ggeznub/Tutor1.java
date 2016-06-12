package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tutor1 extends AppCompatActivity {
    ImageView redBtn;
    ImageView blueBtn;
    ImageView redCore;
    ImageView blueCore;
    ImageView redClick;
    ImageView blueClick;
    ImageView redPoint;
    ImageView bluePoint;
    AnimationDrawable redClickAnim;
    AnimationDrawable blueClickAnim;
    ValueAnimator redClickMove;
    ValueAnimator blueClickMove;
    ValueAnimator redCoreMove;
    ValueAnimator blueCoreMove;
    AnimatorSet redPress;
    AnimatorSet bluePress;
    int redCount;
    int blueCount;
    TextView redScore;
    TextView blueScore;
    Intent i;
    int[] handPos;
    int[][] corePos;
    int[] tmp;
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
        setContentView(R.layout.activity_tutor1);

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

        redClick.setBackgroundResource(R.drawable.mecpenclick);
        redClickAnim = (AnimationDrawable) redClick.getBackground();

        blueClick.setBackgroundResource(R.drawable.mecpenclick);
        blueClickAnim = (AnimationDrawable) blueClick.getBackground();

        redCount=0;
        blueCount=0;

        i = new Intent(this, Stage1.class);
        i.putExtra("stage", stage);
        i.putExtra("RedScore",RedScore);
        i.putExtra("BlueScore",BlueScore);

        handPos = new int[2];
        tmp = new int[2];
        corePos = new int[8][];
        for(int i = 0; i<8;i++){
            corePos[i] = new int[2];
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

//        redClick.getLocationOnScreen(tmp);
//        handPos[0] = tmp[1];
//        blueClick.getLocationOnScreen(tmp);
//        handPos[1] = tmp[1];
//        handPos[0] = redClick.getTop() + ((View) redClick.getParent()).getTop();
//        handPos[1] = blueClick.getTop() + ((View) blueClick.getParent()).getTop();
//        handPos[0] = Math.round(redClick.getY());
//        handPos[1] = Math.round(blueClick.getY());

        redClickMove = ObjectAnimator.ofFloat(redClick,"y",118,138);
//        redClickMove = ObjectAnimator.ofFloat(redClick,"y",handPos[0]-20,handPos[0]);
        redClickMove.setDuration(150);
        blueClickMove = ObjectAnimator.ofFloat(blueClick,"y",744,724);
//        blueClickMove = ObjectAnimator.ofFloat(blueClick,"y",handPos[1]+20,handPos[1]);
        blueClickMove.setDuration(150);
        redCoreMove = ObjectAnimator.ofFloat(redCore,"y",corePos[(blueCount%4)][1]+20,corePos[(blueCount%4)][1]);
        blueCoreMove = ObjectAnimator.ofFloat(blueCore,"y",corePos[(blueCount%4)+4][1]+20,corePos[(blueCount%4)+4][1]);
        redCoreMove.setDuration(150);
        blueCoreMove.setDuration(150);

        redPoint=(ImageView) findViewById(R.id.tutorHandRed);
        bluePoint=(ImageView) findViewById(R.id.tutorHandBlue);
        redPress = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.redtutor);
        redPress.setTarget(redPoint);
        bluePress = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.bluetutor);
        bluePress.setTarget(bluePoint);

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
                redPress.end();
                redPress.start();
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
                bluePress.end();
                bluePress.start();
//                blueCore.setY(corePos[(blueCount%4)+4][1]);
                if(blueCount%4==0){
                    blueScore.setText(String.valueOf((int) Math.floor(blueCount/4)));
                    blueCore.setX(110);
                    blueCoreMove = ObjectAnimator.ofFloat(blueCore,"y",1196,1500);
                    blueCoreMove.end();
                    blueCoreMove.start();
                }
            }
        });

        new CountDownTimer(5600, 700) {

            public void onTick(long mili) {
                redBtn.performClick();
            }

            public void onFinish() {

            }
        }.start();

        new CountDownTimer(5600, 800) {

            public void onTick(long mili) {
                blueBtn.performClick();
            }

            public void onFinish() {

            }
        }.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 8000);

    }
}
