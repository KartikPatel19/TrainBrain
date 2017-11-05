package com.deucat.kartik.trainbrain;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;


public class SeatAvaFragment extends Fragment {

    //http://api.railwayapi.com/v2/check-seat/train/<train number>/source/<stn code>/dest/<dest code>/date/<dd-mm-yyyy>/class/<class code>/quota/<quota code>/apikey/<apikey>/

    EditText eTrainEt ;
    Spinner mClassCode,mQuota;


    public SeatAvaFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat_ava, container, false);




        return view;
    }

}
