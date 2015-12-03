package com.HC.varungove.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class LeaderboardActivity extends Activity{

    private LinearLayout leaderboardContainer;
    private ArrayList<ScoreboardEntry> scoreboardEntriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.layout_leaderboard);

        leaderboardContainer = (LinearLayout)findViewById(R.id.leaderboardcontainer);

        String fbAppID = getResources().getString(R.string.app_id);
        if(isNetworkAvailable()) {
            new GraphRequest(AccessToken.getCurrentAccessToken(), "/" + fbAppID + "/scores", null, HttpMethod.GET, new GraphRequest.Callback() {

                public void onCompleted(GraphResponse response) {

                    JSONObject dataObject = response.getJSONObject();
                    JSONArray dataArray = null;
                    try {
                        dataArray = dataObject.getJSONArray("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    scoreboardEntriesList = new ArrayList<ScoreboardEntry>();

                    for (int i = 0; i < dataArray.length() && i < 10; i++) {
                        JSONObject oneData = dataArray.optJSONObject(i);
                        int score = oneData.optInt("score");

                        JSONObject userObj = oneData.optJSONObject("user");
                        String userID = userObj.optString("id");
                        String userName = userObj.optString("name");

                        scoreboardEntriesList.add(new ScoreboardEntry(userID, userName, score));
                    }

                    populateleaderboard();

                }

            }).executeAsync();
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

    private void populateleaderboard() {

        leaderboardContainer.removeAllViews();

        Iterator<ScoreboardEntry> scoreboardEntriesIterator = scoreboardEntriesList.iterator();

        while (scoreboardEntriesIterator.hasNext()) {
            final ScoreboardEntry currentScoreboardEntry = scoreboardEntriesIterator.next();

            LayoutInflater inflater = (LayoutInflater)this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            RelativeLayout element = (RelativeLayout) inflater.inflate( R.layout.leaderboard_element, null, false);
            leaderboardContainer.addView(element);
            ProfilePictureView profilePictureView = (ProfilePictureView) element.findViewById(R.id.profile_picture);
            profilePictureView.setProfileId(currentScoreboardEntry.getId());
            TextView titleTextView = (TextView) element.findViewById(R.id.name);
            titleTextView.setText(currentScoreboardEntry.getName());
            TextView score = (TextView) element.findViewById(R.id.score);
            score.setText(currentScoreboardEntry.getScore() + "");

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}