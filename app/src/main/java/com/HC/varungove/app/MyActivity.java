package com.HC.varungove.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.Set;


public class MyActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    SharedPreferences sharedPref;
    private CallbackManager callbackManager;
    TextView tv; //user scoreboard
    TextView wel;
    TextView cs; //cpu Scoreboard
    ImageView image1, image2, image3, image4;
    // Button r0;
    Button r1;
    Button r2;
    Button r3;
    Button r4;
    Button r5;
    Button r6;
    Button rs;



    int score=0; // user score
    int cpuscore=0; //cpu score
    int flag=0; // 0 first innings going on 1 second innings
    int bflag=0; //0 user batting 1 cpu batting


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);

        Boolean toss = getIntent().getExtras().getBoolean("bool_user_bats");
        sharedPref = this.getSharedPreferences("com.example.varungove.app", Context.MODE_PRIVATE);
        callbackManager = CallbackManager.Factory.create();

        image1 = (ImageView) findViewById(R.id.imageView1); //cpu hand
        image2 = (ImageView) findViewById(R.id.imageView2);  //user hand
        image3 = (ImageView) findViewById(R.id.imageView3); //cpu symbol
        image4 = (ImageView) findViewById(R.id.imageView4); //user symbol

        Profile profile = Profile.getCurrentProfile();
        wel=(TextView)findViewById(R.id.ys);
        String name  = profile.getFirstName();
        if(name.length()>19)
        {
            name=name.substring(0,18);
        }
        wel.setText(name + ":");

        image1.setImageResource(R.drawable.hand0f);
        image2.setImageResource(R.drawable.hand0f);

        if(toss==true) {
            image3.setImageResource(R.drawable.ballsf);
            image4.setImageResource(R.drawable.batf);
            bflag=0;
        }
        else {
            bflag=1;
            image3.setImageResource(R.drawable.batf);
            image4.setImageResource(R.drawable.ballsf);
        }

        tv = (TextView)findViewById(R.id.USER);

        cs = (TextView)findViewById(R.id.CPU);
        //gm = (TextView)findViewById(R.id.over);

        //r0 = (Button) findViewById(R.id.Run0);
        r1 = (Button) findViewById(R.id.Run1);
        r2 = (Button) findViewById(R.id.Run2);
        r3 = (Button) findViewById(R.id.Run3);
        r4 = (Button) findViewById(R.id.Run4);
        r5 = (Button) findViewById(R.id.Run5);
        r6 = (Button) findViewById(R.id.Run6);
        rs = (Button) findViewById(R.id.restart);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hands(int c, int u)
    {
        if(c==1)
            image1.setImageResource(R.drawable.hands1f);
        if(c==2)
            image1.setImageResource(R.drawable.hands2f);
        if(c==3)
            image1.setImageResource(R.drawable.hands3f);
        if(c==4)
            image1.setImageResource(R.drawable.hands4f);
        if(c==5)
            image1.setImageResource(R.drawable.hands5f);
        if(c==6)
            image1.setImageResource(R.drawable.hand6f);


        if(u==1)
            image2.setImageResource(R.drawable.hands1f);
        if(u==2)
            image2.setImageResource(R.drawable.hands2f);
        if(u==3)
            image2.setImageResource(R.drawable.hands3f);
        if(u==4)
            image2.setImageResource(R.drawable.hands4f);
        if(u==5)
            image2.setImageResource(R.drawable.hands5f);
        if(u==6)
            image2.setImageResource(R.drawable.hand6f);

    } //changes hand gestures  C is cpu gesture U is user gesture

    public void restart(View gar)
    {
        Intent myintent = new Intent(MyActivity.this, TossActivity.class);
        finish();
        myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myintent);

    }

    //called when the first innings is done
    public void out(int c, int p)
    {
        //bflag 0 player batted first bflag 1 CPU batted first

        String cp="CPU needs ";
        int x=0;

        if(bflag==0)
        {
            x=p+1;
            image3.setImageResource(R.drawable.batf);
            image4.setImageResource(R.drawable.ballsf);
        }
        else
        {
            x=c+1;
            cp="You need ";
            image3.setImageResource(R.drawable.ballsf);
            image4.setImageResource(R.drawable.batf);
        }

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("OUT!" +"\n" + cp + x + " runs to win!");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });

    }

    //called when game is done and chooses winner
    public void winner(int c, int p)
    {
        //BFLAG value is 1 is CPU BATTTED SECOND and 0 if PLAYER BATTER SECOND

        // PLAYER BATTED FIRST AND WON
        if(c<p && bflag==1)
        {
            int ctr=p-c;
            String alert2 = "You won by " + ctr + " runs!";



            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("GAME OVER!" +"\n" + alert2);
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });


        }

        // PLAYER BATTED FIRST AND LOST
        if(c>p && bflag==1)
        {


            String alert2 = "You lost!";



            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("GAME OVER!" +"\n" + alert2);
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
        }

        //PLAYER BATTED SECOND AND LOST
        if(c>p && bflag==0)
        {
            int ctr=c-p;
            String alert2 = "You lost by " + ctr + " runs!";



            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("GAME OVER!" +"\n" + alert2);
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });


        }

        //PLAYER BATTED SECOND AND WON
        if(c<p && bflag==0)
        {

            String alert2 = "You won!";



            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("GAME OVER!" +"\n" + alert2);
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });

        }

        if(c==p)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("GAME OVER!" +"\n" + "DRAW!");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
        }


//        if(isNetworkAvailable()) {
//            AccessToken a = AccessToken.getCurrentAccessToken();
//            Set<String> permissions = a.getPermissions();
//
//            if (!permissions.contains("publish_actions")) {
//                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
//                dlgAlert.setMessage("We will not post to your Facebook. Permission is required for the Leaderboard. \nAccept when prompted.");
//                dlgAlert.setPositiveButton("OK", null);
//                dlgAlert.setCancelable(true);
//                dlgAlert.create().show();
//
//                dlgAlert.setPositiveButton("Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                //dismiss the dialog
//                            }
//                        });
//            }
//
//
//        }
//        else
//        {
//            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
//            dlgAlert.setMessage("Check Internet Connection");
//            dlgAlert.setPositiveButton("OK", null);
//            dlgAlert.setCancelable(true);
//            dlgAlert.create().show();
//
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            //dismiss the dialog
//                        }
//                    });
//        }

        submitscore(p);
        updatestats(c, p);
    }

    private void updatestats(int c, int p) {

        int user_total = sharedPref.getInt("user_total", 0);
        int cpu_total = sharedPref.getInt("cpu_total", 0);
        int matches = sharedPref.getInt("Matches", 0);
        int high_score = sharedPref.getInt("high_score", 0);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("user_total", user_total + p);
        editor.putInt("Matches", matches+1);
        editor.putInt("cpu_total", cpu_total + c);
        if(p > high_score)
            editor.putInt("high_score", p);
        editor.commit();
    }

    private void submitscore(int p) {
        if(isNetworkAvailable()) {
            AccessToken a = AccessToken.getCurrentAccessToken();
            Set<String> permissions = a.getPermissions();

            if (!permissions.contains("publish_actions")) {
                asktopublish();
            }
            int high_score = sharedPref.getInt("high_score", 0);

            if (p > high_score) {
                Bundle fbParams = new Bundle();
                fbParams.putString("score", "" + p);
                new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/scores", fbParams, HttpMethod.POST, new GraphRequest.Callback() {

                    public void onCompleted(GraphResponse response) {

                    }

                }).executeAsync();
            }
        }
        else
        {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Check Internet Connection");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
        }
    }

    private void asktopublish() {
        if(isNetworkAvailable()) {



            LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));

            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {

                        }

                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onError(FacebookException error) {

                        }
                    });
        }
        else
        {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Check Internet Connection");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
        }
    }
    //Called when the user clicks the Send button
    public void RUN(View view) {
        int x=1;
        if(bflag==0) {
            x= (int)(10*Math.random());
            if(x==0)
                x=6;
            else if(x>=1 && x<4)
                x=x;
            else if(x==4 || x==5)
                x=4;
            else if(x==6 || x==7)
                x=5;
            else
                x=6;

        } else
        {
            x= (int)(7*Math.random());
            if(x==0)
                x=6;
        }


        switch (view.getId()) {

            case R.id.Run1:
                hands(x,1);
                if(x==1) {
                    if(flag==0)
                    {
                        flag=1;

                        out(cpuscore, score);

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;

                        break;
                    }
                    else {

                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                }//out case
                if(bflag==0)
                    score = score+1;
                else
                    cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                } // checking if score was chased down
                break;


            case R.id.Run2:
                hands(x,2);
                if(x==2) {
                    if(flag==0)
                    {
                        flag=1;

                        out(cpuscore, score);

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                }//out case
                if(bflag==0)
                    score = score+2;
                else
                    cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                } // checking if score was chased down
                break;


            case R.id.Run3:
                hands(x,3);
                if(x==3) {
                    if(flag==0)
                    {
                        flag=1;

                        out(cpuscore, score);

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                }//out case
                if(bflag==0)
                    score = score+3;
                else
                    cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                } // checking if score was chased down

                break;


            case R.id.Run4:
                hands(x,4);
                if(x==4) {
                    if(flag==0)
                    {
                        flag=1;

                        out(cpuscore, score);

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                }//out case
                if(bflag==0)
                    score = score+4;
                else
                    cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                } // checking if score was chased down


                break;



            case R.id.Run5:
                hands(x,5);
                if(x==5) {
                    if(flag==0)
                    {
                        flag=1;

                        out(cpuscore, score);

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                }//out case
                if(bflag==0)
                    score = score+5;
                else
                    cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //   r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                } // checking if score was chased down

                break;



            case R.id.Run6:
                hands(x,6);
                if(x==6) {
                    if(flag==0)
                    {
                        flag=1;

                        out(cpuscore, score);

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        //  r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                }//out case
                if(bflag==0)
                    score = score+6;
                else
                    cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        //gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        // r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        winner(cpuscore, score);
                        break;
                    }
                } // checking if score was chased down


                break;



            default:
                throw new RuntimeException("Unknow button ID");


        }



        tv.setText("" + score); //user score
        cs.setText("" + cpuscore);//cpu score
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}