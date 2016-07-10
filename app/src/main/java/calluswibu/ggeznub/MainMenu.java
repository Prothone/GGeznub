package calluswibu.ggeznub;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity {
    ImageView whiteBG;
    ImageView splatBG;
    AnimationDrawable whiteSplatAnim;
    AnimatorSet startbtnanim;
    ImageView btnStart;
    LinearLayout layout;
    ImageView loadLeft;
    ImageView loadRight;
    ImageView loadUpper;
    ValueAnimator loadLeftAnim;
    ValueAnimator loadRightAnim;
    ValueAnimator loadUpperAnim;
    MediaPlayer mainBG;
    MediaPlayer stroke;
    MediaPlayer drumroll;
    MediaPlayer drumrollend;

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
        splatBG = (ImageView) findViewById(R.id.splatBG);

        mainBG = MediaPlayer.create(this, R.raw.main);
        stroke = MediaPlayer.create(this, R.raw.stroke);
        drumroll = MediaPlayer.create(this, R.raw.drumroll);
        drumrollend = MediaPlayer.create(this, R.raw.drumrollend);

        splatBG.setBackgroundResource(R.drawable.whitesplat);
        whiteSplatAnim = (AnimationDrawable) splatBG.getBackground();
        whiteSplatAnim.start();

        stroke.start();

        startbtnanim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.startbtnidle);
        btnStart = (ImageView) findViewById(R.id.btnStart);
        startbtnanim.setTarget(btnStart);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                whiteBG.setBackgroundResource(R.drawable.a26);
                btnStart.setY(520);
                btnStart.setImageResource(R.drawable.start);
                startbtnanim.start();
                ((FrameLayout)splatBG.getParent()).removeView(splatBG);
                whiteSplatAnim = null;
                stroke.stop();
                mainBG.start();
            }
        }, 1450);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        loadLeft = (ImageView) findViewById(R.id.loadLeft);
        if (loadLeft != null) {
            loadLeft.setX(0-screenWidth);
        }
        loadRight = (ImageView) findViewById(R.id.loadRight);
        if (loadRight != null) {
            loadRight.setX(screenWidth);
        }
        loadUpper = (ImageView) findViewById(R.id.loadUpper);
        if (loadUpper != null) {
            loadUpper.setY(0-screenHeight);
        }

        loadLeftAnim = ObjectAnimator.ofFloat(loadLeft,"x", (0-screenWidth), 0);
        loadLeftAnim.setDuration(3000);
        loadLeftAnim.setInterpolator(new OvershootInterpolator());
        loadRightAnim = ObjectAnimator.ofFloat(loadRight,"x", screenWidth, 0);
        loadRightAnim.setDuration(3000);
        loadRightAnim.setInterpolator(new OvershootInterpolator());
        loadUpperAnim = ObjectAnimator.ofFloat(loadUpper,"y", (0-screenHeight), 0);
        loadUpperAnim.setDuration(2000);
        loadUpperAnim.setInterpolator(new LinearInterpolator());

        final Intent i;
        i = new Intent(this, Ready.class);
        i.putExtra("stage", 1);
        i.putExtra("RedScore",0);
        i.putExtra("BlueScore",0);
        layout = (LinearLayout) findViewById(R.id.screen);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLeftAnim.start();
                loadRightAnim.start();
                layout.setEnabled(false);
                mainBG.stop();
                drumroll.start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadUpperAnim.start();
                    }
                }, 1200);
                Handler anotherHandler = new Handler();
                anotherHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drumroll.stop();
                        drumrollend.start();
                        startActivity(i);
                        finish();
                    }
                }, 3000);
            }
        });
    }
}
