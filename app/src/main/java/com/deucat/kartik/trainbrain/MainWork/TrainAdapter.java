package com.deucat.kartik.trainbrain.MainWork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainHolder> {

    private Data[] mData;

    TrainAdapter(Data[] data) {
        mData = data;
    }

    @Override
    public TrainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trains, parent, false);
        return new TrainHolder(view);
    }

    @Override
    public void onBindViewHolder(TrainHolder holder, int position) {
        holder.bindTrain(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    class TrainHolder extends RecyclerView.ViewHolder {

        TextView mNameTv, mNumberTv, mTravelTimeTv, mSrcTimeTv, mDestTimeTv, mFromTv, mToTv;

        TrainHolder(View itemView) {
            super(itemView);

            mNameTv = itemView.findViewById(R.id.mainTrainName);
            mNumberTv = itemView.findViewById(R.id.mainTrainNumber);
            mTravelTimeTv = itemView.findViewById(R.id.mainTrainTotalTime);
            mSrcTimeTv = itemView.findViewById(R.id.mainTrainStartTime);
            mDestTimeTv = itemView.findViewById(R.id.mainTrainStopTime);
            mFromTv = itemView.findViewById(R.id.mainTrainFrom);
            mToTv = itemView.findViewById(R.id.mainTrainTo);

        }

        void bindTrain(Data data) {

            mNameTv.setText(data.getName());
            mNumberTv.setText(data.getNumber());
            mTravelTimeTv.setText(data.getTraverTime());
            mSrcTimeTv.setText(data.getSrcDepartTime());
            mDestTimeTv.setText(data.getDestArrivTime());
            mFromTv.setText(data.getFromStarion());
            mToTv.setText(data.getToStation());

        }


    }


}
