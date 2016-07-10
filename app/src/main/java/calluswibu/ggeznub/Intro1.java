package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Intro1 extends AppCompatActivity {
    Intent i;
    Bundle receivedBundle;
    int stage;
    int RedScore;
    int BlueScore;
    TextView[] Title;
    AnimatorSet[] Line;
    TextView[] Title2;
    AnimatorSet[] Line2;
    MediaPlayer BG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro1);

        Title = new TextView[4];
        Title[0] = (TextView)findViewById(R.id.Title1);
        Title[1] = (TextView)findViewById(R.id.Title2);
        Title[2] = (TextView)findViewById(R.id.Title3);
        Title[3] = (TextView)findViewById(R.id.Title4);

        Title2 = new TextView[4];
        Title2[0] = (TextView)findViewById(R.id.Title5);
        Title2[1] = (TextView)findViewById(R.id.Title6);
        Title2[2] = (TextView)findViewById(R.id.Title7);
        Title2[3] = (TextView)findViewById(R.id.Title8);

        BG = MediaPlayer.create(this, R.raw.tutor2);
        BG.start();

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        Title[0].setText("Stage " + stage);
        Title2[0].setText("Stage " + stage);

        Line = new AnimatorSet[4];
        Line[0]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[0].setTarget(Title[0]);
        Line[1]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[1].setTarget(Title[1]);
        Line[2]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[2].setTarget(Title[2]);
        Line[3]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[3].setTarget(Title[3]);

        Line2 = new AnimatorSet[4];
        Line2[0]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line2[0].setTarget(Title2[0]);
        Line2[1]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line2[1].setTarget(Title2[1]);
        Line2[2]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line2[2].setTarget(Title2[2]);
        Line2[3]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line2[3].setTarget(Title2[3]);

        switch (stage){
            case 1:
                Title[1].setText("Break");
                Title[3].setText("Finger!");
                Title2[1].setText("Break");
                Title2[3].setText("Finger!");
                break;
            case 2:
                Title[1].setText("Catch");
                Title[3].setText("Food!");
                Title2[1].setText("Catch");
                Title2[3].setText("Food!");
                break;
            case 3:
                Title[1].setText("Fish");
                Title[3].setText("\"Fish\"?");
                Title2[1].setText("Fish");
                Title2[3].setText("\"Fish\"?");
                break;
        }

        new CountDownTimer(5000, 1000) {

            public void onTick(long mili) {
                if(mili < 5000 && mili > 800) {
                    Title[4 - Math.round(mili / 1000)].setAlpha(1);
                    Line[4 - Math.round(mili / 1000)].start();
                    Title2[4 - Math.round(mili / 1000)].setAlpha(1);
                    Line2[4 - Math.round(mili / 1000)].start();
                }
            }

            public void onFinish() {

            }
        }.start();

        i = new Intent(this, Attention.class);
        i.putExtra("stage", stage);
        i.putExtra("RedScore",RedScore);
        i.putExtra("BlueScore",BlueScore);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 9500);
    }
}
