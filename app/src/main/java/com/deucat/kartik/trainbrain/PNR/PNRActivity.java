package com.deucat.kartik.trainbrain.PNR;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

public class PNRActivity extends AppCompatActivity {

    private static final String TAG = "PNRActivity";

    String pnrNumber = "";
    String url = "http://api.railwayapi.com/pnr_status/pnr/" + pnrNumber + "/apikey/o9je768f/";

    PassengerClass[] mPassengerClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr);

        getJSONDataOverTheInterNet(url);
    }

    private void getJSONDataOverTheInterNet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String JSONData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: Succeed");

                        parshPNRClass(JSONData);
                        mPassengerClasses = parshPassangerClass(JSONData);

                    } else {
                        Log.d(TAG, "onResponse: Chut gay samjo");
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private PNRClass parshPNRClass(String JSONData) throws JSONException {
        PNRClass pnrClass = new PNRClass();

        JSONObject root = new JSONObject(JSONData);

        pnrClass.setTrainName(root.getString("train_name"));
        pnrClass.setTrainNumber(root.getString("train_num"));
        pnrClass.setDOJ(root.getString("doj"));
        pnrClass.setClassName(root.getString("class"));
        pnrClass.setTotalPassanger(root.getInt("total_passengers"));

        JSONObject fromStation = root.getJSONObject("from_station");
        pnrClass.setFromStationName(fromStation.getString("name"));

        JSONObject boardingPoient = root.getJSONObject("boarding_point");
        pnrClass.setBoardingPointName(fromStation.getString("name"));

        JSONObject toStation = root.getJSONObject("to_station");
        pnrClass.setToStationName(fromStation.getString("name"));

        JSONObject reservetionUpto = root.getJSONObject("reservation_upto");
        pnrClass.setReservationName(fromStation.getString("name"));

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

}
