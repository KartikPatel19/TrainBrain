package com.deucat.kartik.trainbrain.LiveTrain;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;

class LiveTrainAdapter extends RecyclerView.Adapter<LiveTrainAdapter.LiveTrainHolder> {

    private LiveRouteClass[] mRouteClasses;
    private LiveTrainClass mLiveTrainClass;

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

        TextView mIndexNumber;
        TextView mNameOfStation;
        TextView mSchArr;
        TextView mSchDep;
        TextView mSchArrDate;
        TextView mSchDepDate;

        ImageView mImageView;


        LiveTrainHolder(View itemView) {
            super(itemView);

            mIndexNumber = (TextView) itemView.findViewById(R.id.indexNumberLiveR);

            mNameOfStation = (TextView) itemView.findViewById(R.id.nameTVLiveR);
            mSchArr = (TextView) itemView.findViewById(R.id.schArrLiveR);
            mSchDep = (TextView) itemView.findViewById(R.id.schDepLiveR);
            mSchArrDate = (TextView) itemView.findViewById(R.id.actArrDate);
            mSchDepDate = (TextView) itemView.findViewById(R.id.actDepDate);

            mImageView = (ImageView) itemView.findViewById(R.id.liveRHasPicture);

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
                mImageView.setImageResource(R.drawable.ic_directions_transit);
            }else if(!liveRouteClass.isHasArr() && liveRouteClass.isHasDep()){
                mImageView.setImageResource(R.drawable.ic_transfer_within_a_station);
            }else if(!liveRouteClass.isHasArr() && !liveRouteClass.isHasDep()){
                mImageView.setImageResource(R.drawable.flotting_button_img);
            }
        }

    }


}
