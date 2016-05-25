package calluswibu.ggeznub;

import android.content.Intent;
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
    TextView Title;
    TextView stageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Title = (TextView)findViewById(R.id.txtTitle);
        stageTitle = (TextView)findViewById(R.id.txtStage);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
        }

        stageTitle.setText("Stage " + stage);

        switch (stage){
            case 1:
                Title.setText("Break Your Finger!");
                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
        }

        i = new Intent(this, Attention.class);
        i.putExtra("stage", stage);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
