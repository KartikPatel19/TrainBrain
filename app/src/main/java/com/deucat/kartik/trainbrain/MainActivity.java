package com.deucat.kartik.trainbrain;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.deucat.kartik.trainbrain.LiveTrain.LiveTrain;
import com.deucat.kartik.trainbrain.MainWork.MainFragment;
import com.deucat.kartik.trainbrain.PNR.PNRActivity;
import com.deucat.kartik.trainbrain.Route.TrainRouteActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import cn.refactor.lib.colordialog.PromptDialog;

public class MainActivity extends AppCompatActivity {

    public static String API_KEY = "m2i8khklf4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Arability", MainFragment.class)
                .add("Live Train", LiveTrain.class)
                .add("PNR Status", PNRActivity.class)
                .add("Train Route", TrainRouteActivity.class)
                .create());

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }


}
