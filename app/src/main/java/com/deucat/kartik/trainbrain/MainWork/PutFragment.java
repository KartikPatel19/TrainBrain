package com.deucat.kartik.trainbrain.MainWork;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deucat.kartik.trainbrain.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class PutFragment extends Fragment {

    Data[] mDatas;

    RecyclerView mRecyclerView;

    public PutFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_put, container, false);
        Bundle bundle = getArguments();
        assert bundle != null;
        String JSONData = bundle.getString("Data");

        mRecyclerView = view.findViewById(R.id.putMainRecyclerView);

        try {
            mDatas = startWork(JSONData);
            TrainAdapter trainAdapter = new TrainAdapter(mDatas);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());

            mRecyclerView.setAdapter(trainAdapter);
            mRecyclerView.setLayoutManager(manager);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private Data[] startWork(String jsonData) throws JSONException {

        JSONObject root = new JSONObject(jsonData);
        JSONArray trains = root.getJSONArray("trains");

        Data[] data = new Data[trains.length()];

        for (int i = 0; i<trains.length();i++){

            JSONObject object = trains.getJSONObject(i);

            Data data1 = new Data();

            data1.setNumber(object.getString("number"));
            data1.setName(object.getString("name"));
            data1.setTraverTime(object.getString("travel_time"));
            data1.setSrcDepartTime(object.getString("src_departure_time"));
            data1.setDestArrivTime(object.getString("dest_arrival_time"));

            JSONObject from = object.getJSONObject("from_station");
            data1.setFromStarion(from.getString("name"));

            JSONObject to = object.getJSONObject("to_station");
            data1.setToStation(to.getString("name"));

            data[i] = data1;

        }



        return data;
    }

}
