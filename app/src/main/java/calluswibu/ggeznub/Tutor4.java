package calluswibu.ggeznub;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Tutor4 extends AppCompatActivity {
    Button btn;
    TextView txt;
    boolean can;
    int time;
    int last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor4);
        btn = (Button)findViewById(R.id.button7);
        txt = (TextView) findViewById(R.id.textView4);
        can = false;
        time = 0;
        last = 0;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                can = true;
            }
        });

        new CountDownTimer(4000, 1000) {
            public void onTick(long mili) {
                txt.setText("" + (Math.round(mili / 1000)));
                time++;
            }

            public void onFinish() {
                while(!can) {
                    last = time - 11;
                    if( time >= last+10){
                        new CountDownTimer(15000, 100) {
                            public void onTick(long mili) {
                                time++;
                                txt.setText("" + (Math.round(mili / 1000)));
                            }
                            public void onFinish() {

                            }
                        }.start();
                    }
                }
                finish();
            }
        }.start();
    }
}
