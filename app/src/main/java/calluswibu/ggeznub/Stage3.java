package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import java.util.Random;

public class Stage3 extends AppCompatActivity {
    long nextMili;
    boolean active;
    int red;
    int blue;
    Random rnd;
    ImageView rodBlue;
    ImageView rodRed;;
    ImageView cloud;
    Button btnRed;
    Button btnBlue;
    TextView txtRed;
    TextView txtBlue;
    TextView txtTime;
    AnimationDrawable blueToggle;
    AnimationDrawable redToggle;
    ValueAnimator cloudclear;
    MediaPlayer whistle;
    MediaPlayer BG;
    MediaPlayer clink;
    Intent i;
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
        setContentView(R.layout.activity_stage3);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        rodBlue = (ImageView) findViewById(R.id.rodBlue);
        rodRed = (ImageView) findViewById(R.id.rodRed);
        cloud = (ImageView) findViewById(R.id.cloud);
        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnRed = (Button) findViewById(R.id.btnRed);
        txtBlue = (TextView) findViewById(R.id.blueScore);
        txtRed = (TextView) findViewById(R.id.redScore);
        txtTime = (TextView) findViewById(R.id.txtTime);

        rodRed.setBackgroundResource(R.drawable.baitget);
        redToggle = (AnimationDrawable) rodRed.getBackground();
        rodBlue.setBackgroundResource(R.drawable.baitget);
        blueToggle = (AnimationDrawable) rodBlue.getBackground();

        rnd = new Random();
        nextMili = 28000;
        active = false;
        red = 0;
        blue = 0;

        BG = MediaPlayer.create(this, R.raw.snowbash);
        whistle = MediaPlayer.create(this, R.raw.whistle);
        clink = MediaPlayer.create(this, R.raw.clink);

        i = new Intent(this, ScorePage.class);
        i.putExtra("stage",stage);
        i.putExtra("RedScore",RedScore);
        i.putExtra("BlueScore",BlueScore);

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(active){
                    active = false;
                    rodBlue.setBackgroundResource(R.drawable.rodcaught);
                    blue++;
                    txtBlue.setText(String.valueOf(blue));
                    redToggle.stop();
                    clink.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rodBlue.setBackgroundResource(R.drawable.baitget);
                            blueToggle = (AnimationDrawable) rodBlue.getBackground();
                        }
                    }, 1000);
                }
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(active){
                    active = false;
                    rodRed.setBackgroundResource(R.drawable.rodcaught);
                    red++;
                    txtRed.setText(String.valueOf(red));
                    blueToggle.stop();
                    clink.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rodRed.setBackgroundResource(R.drawable.baitget);
                            redToggle = (AnimationDrawable) rodRed.getBackground();
                        }
                    }, 1000);
                }
            }
        });

        new CountDownTimer(4000, 100) {
            public void onTick(long mili) {
                txtTime.setText("" + (Math.round(mili / 1000)));
            }

            public void onFinish() {
                BG.start();
                txtTime.setText("GO!");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtTime.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
                new CountDownTimer(30000, 100) {
                    public void onTick(long mili) {
                        if(mili <= nextMili && !active){
                            nextMili = mili - (rnd.nextInt(8000) + 4000);

                            redToggle.start();
                            blueToggle.start();
                            active = true;

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    redToggle.stop();
                                    blueToggle.stop();
                                    active = false;
                                }
                            }, 3000);
                        }
                    }

                    public void onFinish() {
                        active = false;
                        txtTime.setText("TIME'S UP!");
                        cloudclear = ObjectAnimator.ofFloat(cloud, "y" , 460, 800);
                        cloudclear.setDuration(2000);
                        cloudclear.start();
                        txtTime.setVisibility(View.VISIBLE);
                        whistle.start();
                        i.putExtra("RedScoreThis",red);
                        i.putExtra("BlueScoreThis",blue);
                        Handler handlerK = new Handler();
                        handlerK.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(i);
                                finish();
                            }
                        }, 3000);
                    }
                }.start();
            }
        }.start();
    }
}
