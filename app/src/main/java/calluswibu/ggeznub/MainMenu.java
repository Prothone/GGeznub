package calluswibu.ggeznub;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    ImageView whiteBG;
    AnimationDrawable whiteSplatAnim;
    AnimatorSet startbtnanim;
    ImageView btnStart;
    LinearLayout layout;
    ImageView loadLeft;
    ImageView loadRight;
    ValueAnimator loadLeftAnim;
    ValueAnimator loadRightAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);

        whiteBG = (ImageView) findViewById(R.id.whiteBG);
        whiteBG.setBackgroundResource(R.drawable.whitesplat);
        whiteSplatAnim = (AnimationDrawable) whiteBG.getBackground();

        whiteSplatAnim.start();

        startbtnanim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.startbtnidle);
        btnStart = (ImageView) findViewById(R.id.btnStart);
        startbtnanim.setTarget(btnStart);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnStart.setY(520);
                btnStart.setImageResource(R.drawable.start);
                startbtnanim.start();
            }
        }, 2000);

//        loadLeft = (ImageView) findViewById(R.id.loadLeft);
//        loadLeft.setImageResource(R.drawable.load);
//        loadRight = (ImageView) findViewById(R.id.loadRight);
//        loadRight.setImageResource(R.drawable.load);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        int screenWidth = displayMetrics.widthPixels;
//        int screenHeight = displayMetrics.heightPixels;
//
//        loadLeftAnim = ObjectAnimator.ofFloat(findViewById(R.id.loadLeft),"x", 0,screenWidth);
//        loadLeftAnim.setDuration(10000);
//        loadLeftAnim.setRepeatCount(ValueAnimator.INFINITE);
//        loadLeftAnim.setRepeatMode(ValueAnimator.REVERSE);

        layout = (LinearLayout) findViewById(R.id.screen);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "GO", Toast.LENGTH_SHORT).show();
//                loadLeftAnim.start();
            }
        });
    }
}
