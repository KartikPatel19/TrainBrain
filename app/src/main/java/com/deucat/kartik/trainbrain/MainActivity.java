package com.deucat.kartik.trainbrain;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.LiveTrain.LiveTrain;
import com.deucat.kartik.trainbrain.PNR.PNRActivity;
import com.deucat.kartik.trainbrain.Route.TrainRouteActivity;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("live").setIndicator("Live Train").setContent(new Intent(this, LiveTrain.class)));
        mTabHost.addTab(mTabHost.newTabSpec("pnr").setIndicator("PNR").setContent(new Intent(this, PNRActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("route").setIndicator("Route").setContent(new Intent(this, TrainRouteActivity.class)));
        mTabHost.setBackgroundColor(Color.parseColor("#388E3C"));

        for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
        }
        mTabHost.setCurrentTab(0);
    }
}
