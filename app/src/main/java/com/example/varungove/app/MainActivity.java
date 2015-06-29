package com.example.varungove.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
    boolean user_bats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

    }

    public void toss(View v) {

        Intent myIntent = new Intent(MainActivity.this, TossActivity.class);
        startActivity(myIntent);

    }

    public void rules (View v) {
        Intent myIntent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(myIntent);

    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (hasFocus) {
            ImageView back = (ImageView) findViewById(R.id.splash_image);
            back.setBackgroundResource(R.drawable.animation);
            AnimationDrawable frameAnimation = (AnimationDrawable) back
                    .getBackground();
            if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                frameAnimation.setExitFadeDuration(2000);
            }

            frameAnimation.start();

        }

    }
}