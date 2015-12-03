package com.HC.varungove.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.widget.GameRequestDialog;

public class MainActivity extends Activity {

    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
    boolean user_bats;

    ProfilePictureView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());

        if(isNetworkAvailable()) {
            Profile profile = Profile.getCurrentProfile();


            iv = (ProfilePictureView) findViewById(R.id.profile_image);
            iv.setProfileId(profile.getId());
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

    public void logout(View v) {
        LoginManager.getInstance().logOut();
        Intent myIntent = new Intent(MainActivity.this, FBLoginActivity.class);
        startActivity(myIntent);
    }

    public void invite(View v) {
        if(isNetworkAvailable()) {
            GameRequestDialog requestDialog;
            requestDialog = new GameRequestDialog(this);
            GameRequestContent content = new GameRequestContent.Builder()
                    .setMessage("Come play this level with me")
                    .build();
            requestDialog.show(content);
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

    public void toss(View v) {

        Intent myIntent = new Intent(MainActivity.this, TossActivity.class);
        startActivity(myIntent);

    }



    public void stats(View v) {
        Intent myIntent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(myIntent);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}