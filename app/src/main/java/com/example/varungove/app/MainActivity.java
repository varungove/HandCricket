package com.example.varungove.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
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
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.popup);
        dialog.setTitle("Select heads or tails");

        final Button heads = (Button) dialog.findViewById(R.id.heads);
        final Button tails = (Button) dialog.findViewById(R.id.tails);
        final Button batting = (Button) dialog.findViewById(R.id.batting);
        final Button bowling = (Button) dialog.findViewById(R.id.bowling);
        final Button start_match = (Button) dialog.findViewById(R.id.start_match);
        final int toss = (int)(2*(Math.random()));
        final int cpu_decision = (int)(2*(Math.random()));

        heads.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toss == 0) {
                    dialog.setTitle("You won the toss! Choose batting or bowling!");
                    heads.setVisibility(View.GONE);
                    tails.setVisibility(View.GONE);
                    batting.setVisibility(View.VISIBLE);
                    bowling.setVisibility(View.VISIBLE);
                }
                else {
                    heads.setVisibility(View.GONE);
                    tails.setVisibility(View.GONE);
                    start_match.setVisibility(View.VISIBLE);

                    if (cpu_decision == 0) {
                        dialog.setTitle("You lost the toss! Bowl");
                        user_bats = false;
                    }
                    else if (cpu_decision == 1) {
                        dialog.setTitle("You lost the toss! Bat");
                        user_bats = true;
                    }
                }
            }
        });

        tails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toss == 1) {
                    dialog.setTitle("You won the toss! Choose batting or bowling!");
                    heads.setVisibility(View.GONE);
                    tails.setVisibility(View.GONE);
                    batting.setVisibility(View.VISIBLE);
                    bowling.setVisibility(View.VISIBLE);
                } else {
                    heads.setVisibility(View.GONE);
                    tails.setVisibility(View.GONE);
                    start_match.setVisibility(View.VISIBLE);

                    if (cpu_decision == 0) {
                        dialog.setTitle("You lost the toss! Bowl");
                        user_bats = false;
                    }
                    else if (cpu_decision == 1) {
                        dialog.setTitle("You lost the toss! Bat");
                        user_bats = true;
                    }
                }
            }
        });

        batting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user_bats = true;
                Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
                myIntent.putExtra("bool_user_bats", user_bats);
                startActivity(myIntent);

            }
        });

        bowling.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user_bats = false;
                Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
                myIntent.putExtra("bool_user_bats", user_bats);
                startActivity(myIntent);
            }
        });

        start_match.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
                myIntent.putExtra("bool_user_bats", user_bats);
                startActivity(myIntent);
            }
        });

        dialog.show();

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