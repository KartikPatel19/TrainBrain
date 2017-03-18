package com.deucat.kartik.trainbrain.LiveTrain;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.AlertDilog;

import com.deucat.kartik.trainbrain.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LiveTrain extends AppCompatActivity {


    LiveTrainClass mLiveTrainClass = new LiveTrainClass();
    LiveRouteClass[] mLiveRouteClass;

    TextView mPosition;
    EditText mEditText;
    Button mButton;

    RecyclerView mRecyclerView;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_train);

        mPosition = (TextView) findViewById(R.id.livePosition);
        mEditText = (EditText) findViewById(R.id.liveEditText);
        mButton = (Button) findViewById(R.id.liveOkButton);

        mRecyclerView = (RecyclerView) findViewById(R.id.liveRecyclerView);

        mAdView = (AdView) findViewById(R.id.liveAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trainNumber = mEditText.getText().toString();

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

                String date = dateFormat.format(calendar.getTime());

                String url = "http://api.railwayapi.com/live/train/" + trainNumber + "/doj/" + date + "/apikey/o9je768f/";

                getDataOverTheInternet(url);
            }
        });

    }

    private void getDataOverTheInternet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String JSONData = response.body().string();

                try {
                    parshLiveTrainClass(JSONData);

                    mLiveRouteClass = parshLiveRouteClass(JSONData);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUI();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private LiveTrainClass parshLiveTrainClass(String JSONData) throws JSONException {

        JSONObject root = new JSONObject(JSONData);
        mLiveTrainClass.setPosition(root.getString("position"));
        mLiveTrainClass.setResponceCode(root.getInt("response_code"));

        JSONObject current = root.getJSONObject("current_station");
        mLiveTrainClass.setCurrentIndexNumber(current.getInt("no"));

        return mLiveTrainClass;
    }

    private LiveRouteClass[] parshLiveRouteClass(String JSONData) throws JSONException {

        JSONObject root = new JSONObject(JSONData);
        JSONArray route = root.getJSONArray("route");

        LiveRouteClass[] liveRouteClasses = new LiveRouteClass[route.length()];

        for (int i = 0; i < route.length(); i++) {
            JSONObject routeList = route.getJSONObject(i);

            LiveRouteClass routeClass = new LiveRouteClass();

            routeClass.setIndexNumber(routeList.getInt("no"));
            routeClass.setDistance(routeList.getInt("distance"));
            routeClass.setLateTime(routeList.getInt("latemin"));

            JSONObject forName = routeList.getJSONObject("station_");

            routeClass.setNamne(forName.getString("name"));

            routeClass.setSchArr(routeList.getString("scharr"));
            routeClass.setSchDep(routeList.getString("schdep"));
            routeClass.setSchArrDate(routeList.getString("scharr_date"));
            routeClass.setSchDepDate(routeList.getString("actarr_date"));

            routeClass.setHasArr(routeList.getBoolean("has_arrived"));
            routeClass.setHasDep(routeList.getBoolean("has_departed"));

            liveRouteClasses[i] = routeClass;

        }

        return liveRouteClasses;
    }

    private void updateUI() {

        if (mLiveTrainClass.getResponceCode() != 200) {
            alerAboutEror();

        }

        mPosition.setText(mLiveTrainClass.getPosition());

        LiveTrainAdapter liveTrainAdapter = new LiveTrainAdapter(mLiveRouteClass);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setAdapter(liveTrainAdapter);
        mRecyclerView.setLayoutManager(manager);


    }

    void alerAboutEror() {
        AlertDilog alertDilog = new AlertDilog();
        alertDilog.show(getFragmentManager(), "Error");
    }
}

