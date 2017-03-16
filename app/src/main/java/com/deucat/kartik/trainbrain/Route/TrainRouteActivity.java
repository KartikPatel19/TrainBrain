package com.deucat.kartik.trainbrain.Route;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deucat.kartik.trainbrain.AlertDilog;
import com.deucat.kartik.trainbrain.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrainRouteActivity extends AppCompatActivity {
    TrainClass trainClass = new TrainClass();
    RouteClass[] mRouteClasses;

    String trainNumber = "";
    String url = "http://api.railwayapi.com/route/train/" + trainNumber + "/apikey/o9je768f/";


    EditText mEditText;
    Button mButton;
    TextView mTrainNameTv;
    RecyclerView mRecyclerView;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_route_activity);

        mTrainNameTv = (TextView) findViewById(R.id.routeTrainName);
        mRecyclerView = (RecyclerView) findViewById(R.id.routeRecyclerView);
        mEditText = (EditText) findViewById(R.id.routeTrainNumber);
        mButton = (Button) findViewById(R.id.routeOkButton);

        mAdView = (AdView) findViewById(R.id.routeAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trainNumber = mEditText.getText().toString();
                url = "http://api.railwayapi.com/route/train/" + trainNumber + "/apikey/o9je768f/";

                try {
                    getJsonDataOverTheInternet(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void getJsonDataOverTheInternet(String url) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String JSONData = response.body().string();
                    if (response.isSuccessful()) {

                        mRouteClasses = parshRouteClass(JSONData);
                        parshTrainClass(JSONData);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI();
                            }
                        });


                    } else {
                        alerAboutEror();
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void updateUI() {
        mTrainNameTv.setText(trainClass.getNameOfTrain());

        if (trainClass.getResponceCode() != 200) {
            alerAboutEror();
            Toast.makeText(TrainRouteActivity.this, "PLease Enter correct number of train", Toast.LENGTH_SHORT).show();
        }

        RouteClassAdapter mAdapter = new RouteClassAdapter(mRouteClasses);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private TrainClass parshTrainClass(String JsonData) throws JSONException {

        JSONObject mainData = new JSONObject(JsonData);
        JSONObject data = mainData.getJSONObject("train");
        trainClass.setNameOfTrain(data.getString("name"));
        trainClass.setResponceCode(mainData.getInt("response_code"));

//        JSONArray classesOfTrain = data.getJSONArray("classes");
//        String[] classAva = new String[classesOfTrain.length()];
//        for (int i = 0; i < classesOfTrain.length(); i++) {
//            JSONObject code = classesOfTrain.getJSONObject(i);
//            String yOrN = code.getString("available");
//            if (yOrN.equals("Y")) {
//                classAva[i] = code.getString("class-code");
//            }
//        }
//        trainClass.setClassName(classAva);
//        JSONArray dayOfTheTrain = data.getJSONArray("days");
//        String[] dayAva = new String[dayOfTheTrain.length()];
//
//        for (int i = 0; i < dayOfTheTrain.length(); i++) {
//            JSONObject code = dayOfTheTrain.getJSONObject(i);
//            String yOrN = code.getString("runs");
//            if (yOrN.equals("Y")) {
//                dayAva[i] = code.getString("day-code");
//            }
//        }
//        trainClass.setDayName(dayAva);

        return trainClass;
    }

    private RouteClass[] parshRouteClass(String JsonData) throws JSONException {

        JSONObject data = new JSONObject(JsonData);
        JSONArray arreyOfRoute = data.getJSONArray("route");

        RouteClass[] routeClasses = new RouteClass[arreyOfRoute.length()];

        for (int i = 0; i < arreyOfRoute.length(); i++) {
            JSONObject routeListData = arreyOfRoute.getJSONObject(i);

            RouteClass routeClass1 = new RouteClass();

            routeClass1.setDistance(routeListData.getInt("distance"));
            routeClass1.setIndexNumber(routeListData.getInt("no"));

            routeClass1.setSchArr(routeListData.getString("scharr"));
            routeClass1.setSchDep(routeListData.getString("schdep"));
            routeClass1.setStationName(routeListData.getString("fullname"));
            routeClass1.setState(routeListData.getString("state"));

            routeClasses[i] = routeClass1;
        }

        return routeClasses;
    }

    void alerAboutEror() {
        AlertDilog alertDilog = new AlertDilog();
        alertDilog.show(getFragmentManager(), "Error");
    }
}
