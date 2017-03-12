package com.deucat.kartik.trainbrain.LiveTrain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LiveTrain extends AppCompatActivity {

    private static final String TAG = "LiveTrain";

    LiveTrainClass mLiveTrainClass = new LiveTrainClass();
    LiveRouteClass[] mLiveRouteClass;

    TextView mPosition;
    TextView mTrainName;

    RecyclerView mRecyclerView;


    String url = "http://api.railwayapi.com/live/train/12345/doj/20170312/apikey/o9je768f/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_train);

        mPosition = (TextView) findViewById(R.id.positionLive);
        mTrainName = (TextView) findViewById(R.id.trainNameLive);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewLive);

        getDataOverTheInternet(url);
    }

    private void getDataOverTheInternet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: Chut gay ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String JSONData = response.body().string();
                Log.d(TAG, "onResponse: "+ JSONData);

                try {
                    parshLiveTrainClass(JSONData);
                    mLiveRouteClass =  parshLiveRouteClass(JSONData);

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
        mLiveTrainClass.setError(root.getString("error"));
        mLiveTrainClass.setPosition(root.getString("position"));
        mLiveTrainClass.setTrainNumber(root.getString("train_number"));

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
            routeClass.setActArr(routeList.getString("actarr"));
            routeClass.setActDep(routeList.getString("actdep"));
            routeClass.setSchArrDate(routeList.getString("scharr_date"));
            routeClass.setSchDepDate(routeList.getString("actarr_date"));

            routeClass.setHasArr(routeList.getBoolean("has_arrived"));
            routeClass.setHasDep(routeList.getBoolean("has_departed"));

            liveRouteClasses[i] = routeClass;

        }

        return liveRouteClasses;
    }

    private void updateUI(){
        mTrainName.setText(mLiveTrainClass.getTrainNumber());
        mPosition.setText(mLiveTrainClass.getPosition());

        LiveTrainAdapter liveTrainAdapter = new LiveTrainAdapter(mLiveRouteClass);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setAdapter(liveTrainAdapter);
        mRecyclerView.setLayoutManager(manager);

    }
}
