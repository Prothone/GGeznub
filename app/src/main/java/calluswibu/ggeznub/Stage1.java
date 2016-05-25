package calluswibu.ggeznub;

import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Stage1 extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stage1);

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

//        new CountDownTimer(5000, 1000) {
//
//            public void onTick(long mili) {
//                Time.setText("" + ((mili / 1000) - 1));
//            }
//
//            public void onFinish() {
//                Red.setEnabled(true);
//                Blue.setEnabled(true);
//                new CountDownTimer(11000, 1000) {
//
//                    public void onTick(long mili) {
//                        Time.setText("" + mili / 1000);
//                    }
//
//                    public void onFinish() {
//                        Time.setTextColor(getResources().getColor(R.color.Black));
//                        Red.setEnabled(false);
//                        Blue.setEnabled(false);
//                        Time.setText("END!");
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        if(Integer.parseInt(redScore.getText().toString()) < Integer.parseInt(blueScore.getText().toString())){
//                            Time.setText("BLUE WINS!");
//                        }
//                        else if(Integer.parseInt(redScore.getText().toString()) > Integer.parseInt(blueScore.getText().toString())){
//                            Time.setText("RED WINS!");
//                        }
//                        else{
//                            Time.setText("TIED!");
//                        }
//                    }
//                }.start();
//            }
//        }.start();
    }
}
