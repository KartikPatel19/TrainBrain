package com.deucat.kartik.trainbrain.LiveTrain;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.deucat.kartik.trainbrain.MainActivity;
import com.deucat.kartik.trainbrain.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LiveTrain extends Fragment {

    LiveTrainClass mLiveTrainClass = new LiveTrainClass();
    LiveRouteClass[] mLiveRouteClass;

    TextView mPosition;
    EditText mEditText;
    Button mButton;

    RecyclerView mRecyclerView;
    AdView mAdView;

    ProgressDialog mDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.activity_live_train, null);

        mDialog = new ProgressDialog(getActivity());
        mDialog.hide();

        mPosition = view.findViewById(R.id.livePosition);
        mEditText = view.findViewById(R.id.liveEditText);
        mButton = view.findViewById(R.id.liveOkButton);

        mRecyclerView = view.findViewById(R.id.liveRecyclerView);

        mAdView = view.findViewById(R.id.liveAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trainNumber = mEditText.getText().toString();

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

                String date = dateFormat.format(calendar.getTime());
                String url = "http://api.railwayapi.com/live/train/" + trainNumber + "/doj/" + date + "/apikey/" + MainActivity.Companion.getAPI_KEY() + "/";
                mDialog.show();

                getDataOverTheInternet(url);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getDataOverTheInternet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                //noinspection ConstantConditions
                String JSONData = response.body().string();

                try {
                    parshLiveTrainClass(JSONData);

                    mLiveRouteClass = parshLiveRouteClass(JSONData);

                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
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

    private void parshLiveTrainClass(String JSONData) throws JSONException {


        final JSONObject root = new JSONObject(JSONData);
        final int responceCode = root.getInt("response_code");
        mLiveTrainClass.setResponceCode(responceCode);

        if (responceCode != 200) {

            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
            return;
        }

        mLiveTrainClass.setPosition(root.getString("position"));

        JSONObject current = root.getJSONObject("current_station");
        mLiveTrainClass.setCurrentIndexNumber(current.getInt("no"));

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

        mPosition.setText(mLiveTrainClass.getPosition());

        mDialog.hide();
        LiveTrainAdapter liveTrainAdapter = new LiveTrainAdapter(mLiveRouteClass);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setAdapter(liveTrainAdapter);
        mRecyclerView.setLayoutManager(manager);


    }

}

