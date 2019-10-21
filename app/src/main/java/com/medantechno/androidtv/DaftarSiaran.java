package com.medantechno.androidtv;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.VideoView;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dinaskominfokab.pakpakbharat on 30/03/19.
 */

public class DaftarSiaran extends AppCompatActivity {
    //private SiaranListAdapter siaranListAdapter;
    private List<ModelSiaran> modelSiaranList = new ArrayList<>();
    //private ListView listView;

    private SiaranGridAdapter siaranGridAdapter;
    private GridView gridView;

    SwipeRefreshLayout pullToRefresh;
    ProgressBar progressBar_berita;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_list);
        listBeritaSimple();

        pullToRefresh = findViewById(R.id.pullToRefreshSiaran);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //progressBar_berita.setVisibility(View.VISIBLE);
                listBeritaSimple();
                pullToRefresh.setRefreshing(false);
            }
        });

        /*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                iklan_pop();
            }
        }, 20000);
        */
        //iklan_pop();

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
            }
        });
        /********* iklan popup ************/

    }

    private void listBeritaSimple() {


        gridView = (GridView) findViewById(R.id.grid_siaran);
        siaranGridAdapter = new SiaranGridAdapter(this, modelSiaranList);
        gridView.setAdapter(siaranGridAdapter);



        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest newsRequest = new JsonArrayRequest("https://medantechno.com/siaran.json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Hasil", response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        ModelSiaran modelNews = new ModelSiaran();
                        modelNews.setLink(obj.getString("link"));
                        modelNews.setLogo(obj.getString("logo"));
                        modelNews.setNama(obj.getString("nama"));

                        if (i + 1 == response.length()) {
                            // end
                            progressBar_berita.setVisibility(View.GONE);
                        }

                        modelSiaranList.add(modelNews);
                        siaranGridAdapter.notifyDataSetChanged();

                    } catch (Exception e) {

                    }

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Mode offline-"+error.toString());
                progressBar_berita.setVisibility(View.GONE);

            }
        });
        //AppController.getInstance().addToRequestQueue(newsRequest);
        queue.add(newsRequest);

        siaranGridAdapter.notifyDataSetChanged();

    }
}
