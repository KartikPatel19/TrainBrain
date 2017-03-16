package com.deucat.kartik.trainbrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.deucat.kartik.trainbrain.LiveTrain.LiveTrain;
import com.deucat.kartik.trainbrain.PNR.PNRActivity;
import com.deucat.kartik.trainbrain.Route.TrainRouteActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView)findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void openRouteIntent(View view) {
        Intent intent = new Intent(this, TrainRouteActivity.class);
        startActivity(intent);
    }

    public void openPnrIntent(View view) {
        Intent intent = new Intent(this, PNRActivity.class);
        startActivity(intent);
    }

    public void openLiveTrain(View view) {
        Intent intent = new Intent(this, LiveTrain.class);
        startActivity(intent);
    }
}
