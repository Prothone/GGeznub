package calluswibu.ggeznub;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Attention extends AppCompatActivity {
    LinearLayout Attention;
    Bundle receivedBundle;
    int stage;
    int RedScore;
    int BlueScore;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Attention = (LinearLayout) findViewById(R.id.AttentionScreen);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        switch (stage){
            case 1:
                i = new Intent (this, Tutor1.class);
                break;
            case 2:
                i = new Intent (this, Tutor2.class);
                break;
//            case 3:
//                i = new Intent (this, Tutor3.class);
//                break;
//            case 4:
//                i = new Intent (this, Tutor4.class);
//                break;
//            case 5:
//                i = new Intent (this, Tutor5.class);
//                break;
        }

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
        }, 500);
    }
}
