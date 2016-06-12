package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Intro1 extends AppCompatActivity {
    Intent i;
    Bundle receivedBundle;
    int stage;
    int RedScore;
    int BlueScore;
    TextView[] Title;
    TextView Title2;
    TextView Title3;
    TextView Title4;
    AnimatorSet[] Line;
    AnimatorSet Line2;
    AnimatorSet Line3;
    AnimatorSet Line4;


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

//        Title1 = (TextView)findViewById(R.id.Title1);
//        Title2 = (TextView)findViewById(R.id.Title2);
//        Title3 = (TextView)findViewById(R.id.Title3);
//        Title4 = (TextView)findViewById(R.id.Title4);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        Title[0].setText("Stage " + stage);

        Line = new AnimatorSet[4];
        Line[0]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[0].setTarget(Title[0]);
        Line[1]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[1].setTarget(Title[1]);
        Line[2]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[2].setTarget(Title[2]);
        Line[3]= (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
        Line[3].setTarget(Title[3]);

//        Line1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
//        Line1.setTarget(Title1);
//        Line2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
//        Line2.setTarget(Title2);
//        Line3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
//        Line3.setTarget(Title3);
//        Line4 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.overscale);
//        Line4.setTarget(Title4);


        switch (stage){
            case 1:
                Title[1].setText("Break");
                Title[3].setText("Finger!");
                break;
            case 2:
                Title[1].setText("Catch");
                Title[3].setText("Food!");
                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
        }

        new CountDownTimer(5000, 1000) {

            public void onTick(long mili) {
                if(mili < 5000 && mili > 800) {
                    Title[4 - Math.round(mili / 1000)].setAlpha(1);
                    Line[4 - Math.round(mili / 1000)].start();
                }
//                Line2.start();
//                Line3.start();
//                Line4.start();
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
        }, 8000);
    }
}
