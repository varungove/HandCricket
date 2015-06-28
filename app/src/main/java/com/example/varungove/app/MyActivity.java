package com.example.varungove.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MyActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    TextView tv; //user scoreboard
    TextView cc; //cpu choiceboard
    TextView cs; //cpu Scoreboard
    TextView gm; //game over

    Button r0;
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

        if(toss==true)
            bflag=0;
        else
        bflag=1;

        tv = (TextView)findViewById(R.id.USER);
        cc = (TextView)findViewById(R.id.choice);
        cs = (TextView)findViewById(R.id.CPU);
        gm = (TextView)findViewById(R.id.over);

        r0 = (Button) findViewById(R.id.Run0);
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

    public void restart(View gar)
    {
        Intent myintent = new Intent(MyActivity.this, MainActivity.class);
        finish();
        startActivity(myintent);
       // finish();

    }

     //Called when the user clicks the Send button
    public void RUN(View view) {
            int x=1;
        if(bflag==0) {
             x = (int) (10 * (Math.random()));
            if (x == 4 || x == 5)
                x = 4;
            else if (x == 6 || x == 7)
                x = 5;
            else if (x == 8 || x == 9)
                x = 6;
        }
        else
        {
             x= (int)(7*Math.random());
        }


        switch (view.getId()) {
            case R.id.Run0:
                if(x==0) {
                        if(flag==0)
                        {
                            flag=1;

                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                            dlgAlert.setMessage("1st Innings Done!");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();

                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dismiss the dialog
                                        }
                                    });

                            if(bflag==0)
                                bflag=1;
                            else
                            bflag=0;

                            break;
                        }//1st innings done
                    else {
                            gm.setVisibility(View.VISIBLE);
                            rs.setVisibility(View.VISIBLE);
                            r0.setEnabled(false);
                            r1.setEnabled(false);
                            r2.setEnabled(false);
                            r3.setEnabled(false);
                            r4.setEnabled(false);
                            r5.setEnabled(false);
                            r6.setEnabled(false);

                            break;
                        }//second innings done
                }//out case
                if(bflag==0)
                score = score;
                else
                cpuscore=cpuscore+x;

                if(flag==1)
                {
                    if(bflag==0 && score>cpuscore) {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down

                break;


            case R.id.Run1:
                if(x==1) {
                    if(flag==0)
                    {
                        flag=1;

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("1st Innings Done!");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;

                        break;
                    }
                    else {

                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
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
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down
                break;


            case R.id.Run2:
                if(x==2) {
                    if(flag==0)
                    {
                        flag=1;

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("1st Innings Done!");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
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
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down
                break;


            case R.id.Run3:
                if(x==3) {
                    if(flag==0)
                    {
                        flag=1;

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("1st Innings Done!");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
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
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down

                break;


            case R.id.Run4:
                if(x==4) {
                    if(flag==0)
                    {
                        flag=1;

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("1st Innings Done!");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
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
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down


                break;



            case R.id.Run5:
                if(x==5) {
                    if(flag==0)
                    {
                        flag=1;

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("1st Innings Done!");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
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
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down

                break;



            case R.id.Run6:
                if(x==6) {
                    if(flag==0)
                    {
                        flag=1;

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("1st Innings Done!");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });

                        if(bflag==0)
                            bflag=1;
                        else
                            bflag=0;


                        break;
                    }
                    else {

                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
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
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }

                    if(bflag==1 && cpuscore>score)
                    {
                        gm.setVisibility(View.VISIBLE);
                        rs.setVisibility(View.VISIBLE);
                        r0.setEnabled(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);
                        r6.setEnabled(false);
                        break;
                    }
                } // checking if score was chased down


                break;



            default:
                throw new RuntimeException("Unknow button ID");


        }


        cc.setText(""+x); // CPU choice
        tv.setText(""+score); //user score
        cs.setText(""+cpuscore);//cpu score
    }
}
