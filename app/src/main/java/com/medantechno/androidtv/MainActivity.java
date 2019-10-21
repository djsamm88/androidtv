package com.medantechno.androidtv;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.net.Uri;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.medantechno.androidtv.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    String alamat;

    private InterstitialAd mInterstitialAd;

    VideoView vidView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        playernya();

        /*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                iklan_pop();
            }
        }, 50000);
        */
        //iklan_pop();
    }


    private void playernya()
    {

        vidView = (VideoView)findViewById(R.id.myVideo);

        Intent intent = getIntent();
        alamat = intent.getStringExtra("link");


        String vidAddress = alamat;
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.start();
    }

    private void iklan_pop()
    {


        /********* iklan popup ************/
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2993509046689702/7011149538");
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice("FE3B2C85BC57494FB1154697FEB2BF52")
                .build()
        );
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                //finish();
                //playernya();
            }
        });
        /********* iklan popup ************/

    }


    @Override
    public void onBackPressed() {
        finish();
    }

}
