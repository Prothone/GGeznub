package calluswibu.ggeznub;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.renderscript.Sampler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
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
    boolean statusRed;
    boolean statusBlue;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ready);

        RedBtn = (ImageView) findViewById(R.id.btnRed);
        BlueBtn = (ImageView) findViewById(R.id.btnBlue);
        RedPoint = (ImageView) findViewById(R.id.pointRed);
        BluePoint = (ImageView) findViewById(R.id.pointBlue);
        RedReady = (TextView) findViewById(R.id.readyRed);
        BlueReady = (TextView) findViewById(R.id.readyBlue);

        statusBlue = false;
        statusRed = false;

        fadeInRed = ObjectAnimator.ofFloat(RedReady,"alpha", 0, 1);
        fadeInRed.setDuration(1500);
        fadeInBlue = ObjectAnimator.ofFloat(BlueReady,"alpha", 0, 1);
        fadeInBlue.setDuration(1500);

        i = new Intent(this, Intro1.class);
        i.putExtra("stage",1);

        RedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeInRed.start();
                (findViewById(R.id.tapRed)).setVisibility(View.INVISIBLE);
                RedPoint.setVisibility(View.INVISIBLE);
                statusRed = true;
                RedBtn.setEnabled(false);
                if(statusRed && statusBlue){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Countdown();
                        }
                    }, 1000);
                }
            }
        });
        BlueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeInBlue.start();
                (findViewById(R.id.tapBlue)).setVisibility(View.INVISIBLE);
                BluePoint.setVisibility(View.INVISIBLE);
                statusBlue = true;
                BlueBtn.setEnabled(false);
                if(statusRed && statusBlue){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Countdown();
                        }
                    }, 1000);
                }
            }
        });


    }

    void Countdown(){
        new CountDownTimer(1000, 1000) {

            public void onTick(long mili) {
//                RedReady.setText("" + mili / (double) 1000);
//                BlueReady.setText("" + mili / (double) 1000);{
                RedReady.setText(String.valueOf(Math.round(mili * 0.001f)));
                BlueReady.setText(String.valueOf(Math.round(mili * 0.001f)));
            }

            public void onFinish() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(i);
                        finish();
                    }
                }, 200);
            }
        }.start();
    }
}
