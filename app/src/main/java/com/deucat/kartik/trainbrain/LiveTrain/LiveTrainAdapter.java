package com.deucat.kartik.trainbrain.LiveTrain;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;

class LiveTrainAdapter extends RecyclerView.Adapter<LiveTrainAdapter.LiveTrainHolder> {

    private LiveRouteClass[] mRouteClasses;

    LiveTrainAdapter(LiveRouteClass[] routeClasses) {
        mRouteClasses = routeClasses;
    }

    @Override
    public LiveTrainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_route_list, parent, false);
        return new LiveTrainHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveTrainHolder holder, int position) {
        holder.bindLive(mRouteClasses[position]);
    }

    @Override
    public int getItemCount() {
        return mRouteClasses.length;
    }

    class LiveTrainHolder extends RecyclerView.ViewHolder {

        TextView mIndexNumber, mNameOfStation, mSchArr, mSchDep, mSchArrDate, mSchDepDate;
        RelativeLayout mLayout;
        ImageView mImageView;


        LiveTrainHolder(View itemView) {
            super(itemView);

            mIndexNumber = itemView.findViewById(R.id.indexNumberLiveR);

            mNameOfStation = itemView.findViewById(R.id.nameTVLiveR);
            mSchArr = itemView.findViewById(R.id.schArrLiveR);
            mSchDep = itemView.findViewById(R.id.schDepLiveR);
            mSchArrDate = itemView.findViewById(R.id.actArrDate);
            mSchDepDate = itemView.findViewById(R.id.actDepDate);

            mLayout = itemView.findViewById(R.id.liveBG);

            mImageView = itemView.findViewById(R.id.liveRHasPicture);

        }

        void bindLive(LiveRouteClass liveRouteClass) {

            int indexNo = liveRouteClass.getIndexNumber();
            mIndexNumber.setText(indexNo + "");

            mNameOfStation.setText(liveRouteClass.getNamne());
            mSchArr.setText(liveRouteClass.getSchArr());
            mSchDep.setText(liveRouteClass.getSchDep());
            mSchArrDate.setText(liveRouteClass.getSchArrDate());
            mSchDepDate.setText(liveRouteClass.getSchDepDate());


            if (liveRouteClass.isHasArr() && liveRouteClass.isHasDep()) {
                mLayout.setBackgroundColor(Color.parseColor("#A5D6A7"));
            } else if (!liveRouteClass.isHasArr() && liveRouteClass.isHasDep()) {
                mLayout.setBackgroundColor(Color.parseColor("#FFA726"));
            } else if(!liveRouteClass.isHasArr() && !liveRouteClass.isHasDep()){
                mLayout.setBackgroundColor(Color.parseColor("#FF5722"));
            }else {
                mLayout.setBackgroundColor(Color.parseColor("#757575"));
            }


        }

    }


}
