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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Tutor3 extends AppCompatActivity {
    long nextMili;
    boolean active;
    int red;
    int blue;
    Random rnd;
    ImageView rodBlue;
    ImageView rodRed;
    Button btnRed;
    Button btnBlue;
    TextView txtRed;
    TextView txtBlue;
    TextView redClick;
    TextView blueClick;
    AnimationDrawable blueToggle;
    AnimationDrawable redToggle;
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
        setContentView(R.layout.activity_tutor3);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        rodBlue = (ImageView) findViewById(R.id.rodBlue);
        rodRed = (ImageView) findViewById(R.id.rodRed);
        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnRed = (Button) findViewById(R.id.btnRed);
        txtBlue = (TextView) findViewById(R.id.blueScore);
        txtRed = (TextView) findViewById(R.id.redScore);
        redClick = (TextView) findViewById(R.id.redClick);
        blueClick = (TextView) findViewById(R.id.blueClick);

        rodRed.setBackgroundResource(R.drawable.baitget);
        redToggle = (AnimationDrawable) rodRed.getBackground();
        rodBlue.setBackgroundResource(R.drawable.baitget);
        blueToggle = (AnimationDrawable) rodBlue.getBackground();

        rnd = new Random();
        nextMili = 4500;
        active = false;
        red = 0;
        blue = 0;

        i = new Intent(this, Stage3.class);
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
                    blueClick.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            blueClick.setVisibility(View.INVISIBLE);
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
                    redClick.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            redClick.setVisibility(View.INVISIBLE);
                            rodRed.setBackgroundResource(R.drawable.baitget);
                            redToggle = (AnimationDrawable) rodRed.getBackground();
                        }
                    }, 1000);
                }
            }
        });

        new CountDownTimer(8500, 100) {
            public void onTick(long mili) {
                if(mili <= nextMili && !active){
                    nextMili = mili - (3000);

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
                    }, 2000);

                    Handler handlerE = new Handler();
                    handlerE.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(rnd.nextBoolean()){
                                btnBlue.performClick();
                            }
                            else {
                                btnRed.performClick();
                            }
                        }
                    }, 1000);
                }
            }

            public void onFinish() {
                active = false;
                startActivity(i);
                finish();
            }
        }.start();
    }
}
