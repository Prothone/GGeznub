package calluswibu.ggeznub;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tutor1 extends AppCompatActivity {
    ImageView redBtn;
    ImageView blueBtn;
    ImageView redClick;
    ImageView blueClick;
    AnimationDrawable redClickAnim;
    AnimationDrawable blueClickAnim;
    int redCount;
    int blueCount;
    TextView redScore;
    TextView blueScore;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutor1);

        redClick = (ImageView) findViewById(R.id.redClick);
        blueClick = (ImageView) findViewById(R.id.blueClick);
        redBtn = (ImageView) findViewById(R.id.btnRed);
        blueBtn = (ImageView) findViewById(R.id.btnBlue);
        redScore = (TextView) findViewById(R.id.redScore);
        blueScore = (TextView) findViewById(R.id.blueScore);

        redClick.setBackgroundResource(R.drawable.mecpenclick);
        redClickAnim = (AnimationDrawable) redClick.getBackground();

        blueClick.setBackgroundResource(R.drawable.mecpenclick);
        blueClickAnim = (AnimationDrawable) blueClick.getBackground();

        redCount=0;
        blueCount=0;

        i = new Intent(this, Stage1.class);

        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redClickAnim.stop();
                redClickAnim.start();
                redCount++;
                if(redCount%4==0){
                    redScore.setText(String.valueOf((int) Math.floor(redCount/4)));
                }
            }
        });

        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueClickAnim.stop();
                blueClickAnim.start();
                blueCount++;
                if(blueCount%4==0){
                    blueScore.setText(String.valueOf((int) Math.floor(blueCount/4)));
                }
            }
        });

        new CountDownTimer(5000, 1000) {

            public void onTick(long mili) {
                redBtn.performClick();
            }

            public void onFinish() {

            }
        }.start();

        new CountDownTimer(6000, 1200) {

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
        }, 5000);

    }
}
