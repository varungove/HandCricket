package com.HC.varungove.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TossActivity extends Activity {

    boolean user_bats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.layout_toss);

        final Button heads = (Button) findViewById(R.id.heads);
        final Button tails = (Button) findViewById(R.id.tails);
        final Button batting = (Button) findViewById(R.id.batting);
        final Button bowling = (Button) findViewById(R.id.bowling);
        final Button start_match = (Button) findViewById(R.id.startMatchButton);
        final TextView tv = (TextView) findViewById(R.id.tossTitle);
        final int toss = (int)(2*(Math.random()));
        final int cpu_decision = (int)(2*(Math.random()));

        heads.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toss == 0) {
                    tv.setText("You won the toss! Choose batting or bowling!");
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
                        tv.setText("You lost the toss! CPU decides to Bat");
                        user_bats = false;
                    }
                    else if (cpu_decision == 1) {
                        tv.setText("You lost the toss! CPU decides to bowl");
                        user_bats = true;
                    }
                }
            }
        });

        tails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toss == 1) {
                    tv.setText("You won the toss! Choose batting or bowling!");
                    heads.setVisibility(View.GONE);
                    tails.setVisibility(View.GONE);
                    batting.setVisibility(View.VISIBLE);
                    bowling.setVisibility(View.VISIBLE);
                } else {
                    heads.setVisibility(View.GONE);
                    tails.setVisibility(View.GONE);
                    start_match.setVisibility(View.VISIBLE);

                    if (cpu_decision == 0) {
                        tv.setText("You lost the toss! CPU decides to Bat");
                        user_bats = false;
                    }
                    else if (cpu_decision == 1) {
                        tv.setText("You lost the toss! CPU decides to bowl");
                        user_bats = true;
                    }
                }
            }
        });

        batting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user_bats = true;
                Intent myIntent = new Intent(TossActivity.this, MyActivity.class);
                myIntent.putExtra("bool_user_bats", user_bats);
                startActivity(myIntent);
                finish();

            }
        });

        bowling.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user_bats = false;
                Intent myIntent = new Intent(TossActivity.this, MyActivity.class);
                myIntent.putExtra("bool_user_bats", user_bats);
                startActivity(myIntent);
                finish();
            }
        });

        start_match.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TossActivity.this, MyActivity.class);
                myIntent.putExtra("bool_user_bats", user_bats);
                startActivity(myIntent);
                finish();
            }
        });

    }
}