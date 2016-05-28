package calluswibu.ggeznub;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {
    Bundle receivedBundle;
    int stage;
    int RedScore;
    int BlueScore;
    TextView stageView;
    TextView redView;
    TextView blueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score_page);

        receivedBundle = getIntent().getExtras();
        if (!receivedBundle.isEmpty()) {
            stage = receivedBundle.getInt("stage");
            RedScore = receivedBundle.getInt("RedScore");
            BlueScore = receivedBundle.getInt("BlueScore");
        }

        stageView = (TextView) findViewById(R.id.stage);
        redView = (TextView) findViewById(R.id.txtRed);
        blueView = (TextView) findViewById(R.id.txtBlue);

        stageView.setText("Stage "+stage);
        redView.setText(""+RedScore);
        blueView.setText(""+BlueScore);
    }
}
