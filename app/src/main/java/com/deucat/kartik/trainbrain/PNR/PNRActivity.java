package com.deucat.kartik.trainbrain.PNR;

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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PNRActivity extends AppCompatActivity {


    String pnrNumber = "";
    String url = "http://api.railwayapi.com/pnr_status/pnr/" + pnrNumber + "/apikey/o9je768f/";

    PassengerClass[] mPassengerClasses;
    PNRClass mPNRClass = new PNRClass();

    TextView mTrainName;
    TextView mTrainNumber;
    TextView mDOJ;
    TextView mClassOfTrain;
    TextView mNumberOfPassanger;
    TextView mTrainStart;
    TextView mTrainFrom;
    TextView mBordingAt;
    TextView mToStation;
    TextView mReservation;

    EditText mEditText;
    Button mButton;

    RecyclerView mRecyclerView;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr);

        mTrainName = (TextView) findViewById(R.id.trainName);
        mTrainNumber = (TextView) findViewById(R.id.trainNumberPNRTV);
        mDOJ = (TextView) findViewById(R.id.trainDOJPNRTV);
        mClassOfTrain = (TextView) findViewById(R.id.classOfPNRTV);
        mNumberOfPassanger = (TextView) findViewById(R.id.passangerPNRTV);
        mTrainStart = (TextView) findViewById(R.id.trainStartPNRTV);
        mTrainFrom = (TextView) findViewById(R.id.fromPnrTV);
        mBordingAt = (TextView) findViewById(R.id.bordingPNRTV);
        mToStation = (TextView) findViewById(R.id.toStationPNRTV);
        mReservation = (TextView) findViewById(R.id.reservationPNRTV);

        mEditText = (EditText) findViewById(R.id.pnrEditText);
        mButton = (Button) findViewById(R.id.pnrOKButton);

        mRecyclerView = (RecyclerView) findViewById(R.id.pnrRecyclerView);

        mAdView = (AdView) findViewById(R.id.pnrAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              pnrNumber =   mEditText.getText().toString();
                url = "http://api.railwayapi.com/pnr_status/pnr/" + pnrNumber + "/apikey/o9je768f/";

                getJSONDataOverTheInterNet(url);
            }
        });

        getJSONDataOverTheInterNet(url);
    }

    private void getJSONDataOverTheInterNet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String JSONData = response.body().string();
                    if (response.isSuccessful()) {

                        parshPNRClass(JSONData);
                        mPassengerClasses = parshPassangerClass(JSONData);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI();
                            }
                        });

                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private PNRClass parshPNRClass(String JSONData) throws JSONException {

        JSONObject root = new JSONObject(JSONData);

        mPNRClass.setResponceCode(root.getInt("response_code"));
        mPNRClass.setTrainName(root.getString("train_name"));
        mPNRClass.setTrainNumber(root.getString("train_num"));
        mPNRClass.setDOJ(root.getString("doj"));
        mPNRClass.setClassName(root.getString("class"));
        mPNRClass.setTotalPassanger(root.getInt("total_passengers"));

        JSONObject fromStation = root.getJSONObject("from_station");
        mPNRClass.setFromStationName(fromStation.getString("name"));

        JSONObject boardingPoient = root.getJSONObject("boarding_point");
        mPNRClass.setBoardingPointName(boardingPoient.getString("name"));

        JSONObject toStation = root.getJSONObject("to_station");
        mPNRClass.setToStationName(toStation.getString("name"));

        JSONObject reservetionUpto = root.getJSONObject("reservation_upto");
        mPNRClass.setReservationName(reservetionUpto.getString("name"));

        return null;
    }

    private PassengerClass[] parshPassangerClass(String JSonData) throws JSONException {

        JSONObject root = new JSONObject(JSonData);
        JSONArray passanger = root.getJSONArray("passengers");

        PassengerClass[] passengerClasses = new PassengerClass[passanger.length()];

        for (int i = 0; i < passanger.length(); i++) {
            JSONObject passangerList = passanger.getJSONObject(i);

            PassengerClass passengerClass = new PassengerClass();

            passengerClass.setIndexNumber(passangerList.getInt("no"));
            passengerClass.setBookingStatus(passangerList.getString("booking_status"));
            passengerClass.setCurruntStatus(passangerList.getString("current_status"));
            passengerClass.setCochePosition(passangerList.getInt("coach_position"));

            passengerClasses[i] = passengerClass;
        }

        return passengerClasses;
    }

    private void updateUI() {

        mTrainName.setText(mPNRClass.getTrainName());
        mTrainNumber.setText(mPNRClass.getTrainNumber());
        mDOJ.setText(mPNRClass.getDOJ());
        mClassOfTrain.setText(mPNRClass.getClassName());
        mNumberOfPassanger.setText(mPNRClass.getTotalPassanger() + "");
        mTrainStart.setText(mPNRClass.getDOJ());
        mTrainFrom.setText(mPNRClass.getFromStationName());
        mBordingAt.setText(mPNRClass.getBoardingPointName());
        mToStation.setText(mPNRClass.getToStationName());
        mReservation.setText(mPNRClass.getReservationName());

        PNRClassAdapter adapter = new PNRClassAdapter(mPassengerClasses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);

        if (mPNRClass.getResponceCode()!=200){
            alerAboutEror();
        }

    }

    void alerAboutEror() {
        AlertDilog alertDilog = new AlertDilog();
        alertDilog.show(getFragmentManager(),"Error");
    }

    public void goBack(View view) {
    }
}
