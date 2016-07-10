package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Ending extends AppCompatActivity {
    Intent i;
    Bundle receivedBundle;
    int RedScore;
    int BlueScore;
    FrameLayout BG;
    ImageView HM;
    ImageView endglow;
    TextView txt;
    ImageView BG2;
    TextView credit;
    AnimatorSet BGfade;
    AnimatorSet creditfade;
    MediaPlayer BGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ending);

        BG = (FrameLayout) findViewById(R.id.BG);
        HM = (ImageView) findViewById(R.id.HM);
        endglow = (ImageView) findViewById(R.id.endglow);
        txt = (TextView) findViewById(R.id.txt);
        BG2 = (ImageView) findViewById(R.id.BG2);
        credit = (TextView) findViewById(R.id.credit);
        i = new Intent(this, MainMenu.class);

        BGM = MediaPlayer.create(this, R.raw.credit);
        BGM.start();

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        BGfade = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fadein);
        BGfade.setTarget(BG2);
        creditfade = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fadein);
        creditfade.setTarget(credit);

        if(RedScore > BlueScore){
            BG.setBackgroundColor(Color.parseColor("#FF3131"));
            HM.setImageResource(R.drawable.hmred);
            endglow.setImageResource(R.drawable.endglowred);
            txt.setText("THE WINNER IS RED!");
        }
        else {
            BG.setBackgroundColor(Color.parseColor("#3136FF"));
            HM.setImageResource(R.drawable.hmblue);
            endglow.setImageResource(R.drawable.endglowblue);
            txt.setText("THE WINNER IS BLUE!");
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BGfade.start();
            }
        }, 5000);

        Handler handlerC = new Handler();
        handlerC.postDelayed(new Runnable() {
            @Override
            public void run() {
                creditfade.start();
            }
        }, 6000);

        Handler handlerI = new Handler();
        handlerI.postDelayed(new Runnable() {
            @Override
            public void run() {
                BGM.stop();
                startActivity(i);
                finish();
            }
        }, 10000);
    }
}
