package com.deucat.kartik.trainbrain.MainWork;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.deucat.kartik.trainbrain.GetFragment;
import com.deucat.kartik.trainbrain.R;

public class MainFragment extends Fragment {


    public MainFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Fragment initialFragment = new GetFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFullFragment, initialFragment);
        fragmentTransaction.commit();

        return view;
    }

}
