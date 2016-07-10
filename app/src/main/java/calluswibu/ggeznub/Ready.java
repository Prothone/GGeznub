package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Ready extends AppCompatActivity {
    ValueAnimator fadeInRed;
    ValueAnimator fadeInBlue;
    ImageView RedBtn;
    ImageView BlueBtn;
    ImageView RedPoint;
    ImageView BluePoint;
    TextView RedReady;
    TextView BlueReady;
    AnimatorSet redPress;
    AnimatorSet bluePress;
    boolean statusRed;
    boolean statusBlue;
    Intent i;
    MediaPlayer BG;
    MediaPlayer OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ready);

        RedBtn = (ImageView) findViewById(R.id.btnRed);
        BlueBtn = (ImageView) findViewById(R.id.btnBlue);
        RedPoint = (ImageView) findViewById(R.id.pointRed);
        BluePoint = (ImageView) findViewById(R.id.pointBlue);
        RedReady = (TextView) findViewById(R.id.readyRed);
        BlueReady = (TextView) findViewById(R.id.readyBlue);
        BG = MediaPlayer.create(this, R.raw.ready);
        OK = MediaPlayer.create(this, R.raw.readyok);
        BG.start();

        redPress = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.redready);
        redPress.setTarget(RedPoint);
        bluePress = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.blueready);
        bluePress.setTarget(BluePoint);

        new CountDownTimer(100000, 1000) {
            public void onTick(long mili) {
                redPress.end();
                bluePress.end();
                redPress.start();
                bluePress.start();
            }

            public void onFinish() {

            }
        }.start();

        statusBlue = false;
        statusRed = false;

        fadeInRed = ObjectAnimator.ofFloat(RedReady, "alpha", 0, 1);
        fadeInRed.setDuration(1500);
        fadeInBlue = ObjectAnimator.ofFloat(BlueReady, "alpha", 0, 1);
        fadeInBlue.setDuration(1500);

        i = new Intent(this, Intro1.class);
        i.putExtra("stage", 1);
        i.putExtra("RedScore", 0);
        i.putExtra("BlueScore", 0);

        RedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeInRed.start();
                (findViewById(R.id.tapRed)).setVisibility(View.INVISIBLE);
                RedPoint.setVisibility(View.INVISIBLE);
                statusRed = true;
                OK.start();
                RedBtn.setEnabled(false);
                if (statusRed && statusBlue) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BG.stop();
                            startActivity(i);
                            finish();
                        }
                    }, 2000);
                }
            }
        });
        BlueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeInBlue.start();
                (findViewById(R.id.tapBlue)).setVisibility(View.INVISIBLE);
                BluePoint.setVisibility(View.INVISIBLE);
                OK.start();
                statusBlue = true;
                BlueBtn.setEnabled(false);
                if (statusRed && statusBlue) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BG.stop();
                            startActivity(i);
                            finish();
                        }
                    }, 2000);
                }
            }
        });


    }
}