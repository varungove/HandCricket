package com.HC.varungove.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends Activity {

    Button lb;
    TextView m;
    TextView hs;
    TextView tr;
    TextView ba;
    TextView boa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.layout_stats);

        SharedPreferences sharedPref = this.getSharedPreferences("com.example.varungove.app", Context.MODE_PRIVATE);

        int user_total = sharedPref.getInt("user_total", 0);
        int cpu_total = sharedPref.getInt("cpu_total", 0);
        int matches = sharedPref.getInt("Matches", 0);
        int high_score = sharedPref.getInt("high_score", 0);
        int bat_av = 0, bowl_av = 0;
        if (matches != 0) {
            bat_av = user_total / matches;
            bowl_av = cpu_total / matches;
        }

        m = (TextView)findViewById(R.id.mp);
        hs = (TextView)findViewById(R.id.hs);
        tr = (TextView)findViewById(R.id.tr);
        ba = (TextView)findViewById(R.id.ba);
        boa = (TextView)findViewById(R.id.boa);



        m.setText(""+matches);
        hs.setText(""+high_score);
        tr.setText(""+user_total);
        ba.setText(""+bat_av);
        boa.setText(""+bowl_av);

        lb = (Button)findViewById(R.id.leaderboardButton);
    }

    public void leaderboard(View v) {
        Intent myIntent = new Intent(StatsActivity.this, LeaderboardActivity.class);
        startActivity(myIntent);

    }
}
