package com.deucat.kartik.trainbrain.LiveTrain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;

class LiveTrainAdapter extends RecyclerView.Adapter<LiveTrainAdapter.LiveTrainHolder> {

    private LiveRouteClass[] mRouteClasses;

     LiveTrainAdapter(LiveRouteClass[] routeClasses) {
        mRouteClasses = routeClasses;
    }

    @Override
    public LiveTrainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_route_list,parent,false);
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
        TextView mDistanceName;
        TextView mLateTime;
        TextView mNameOfStation;
        TextView mSchArr;
        TextView mSchDep;
        TextView mActArr;
        TextView mActDep;
        TextView mSchArrDate;
        TextView mSchDepDate;
        TextView mHasArr;
        TextView mHasDep;


         LiveTrainHolder(View itemView) {
            super(itemView);

            mIndexNumber = (TextView) itemView.findViewById(R.id.indexNumberLiveR);
            mDistanceName = (TextView) itemView.findViewById(R.id.distanceTVLiveR);
            mLateTime = (TextView) itemView.findViewById(R.id.lateTimeTVLiveR);

            mNameOfStation = (TextView) itemView.findViewById(R.id.nameTVLiveR);
            mSchArr = (TextView) itemView.findViewById(R.id.schArrLiveR);
            mSchDep = (TextView) itemView.findViewById(R.id.schDepLiveR);
            mActArr = (TextView) itemView.findViewById(R.id.actArr);
            mActDep = (TextView) itemView.findViewById(R.id.actDep);
            mSchArrDate = (TextView) itemView.findViewById(R.id.actArrDate);
            mSchDepDate = (TextView) itemView.findViewById(R.id.actDepDate);

            mHasArr = (TextView) itemView.findViewById(R.id.hasArr);
            mHasDep = (TextView) itemView.findViewById(R.id.hasDep);

        }

        void bindLive(LiveRouteClass liveRouteClass) {

            mIndexNumber.setText(liveRouteClass.getIndexNumber() + "");
            mDistanceName.setText(liveRouteClass.getDistance() + "");
            mLateTime.setText(liveRouteClass.getLateTime() + "");

            mNameOfStation.setText(liveRouteClass.getNamne());
            mSchArr.setText(liveRouteClass.getSchArr());
            mSchDep.setText(liveRouteClass.getSchDep());
            mActArr.setText(liveRouteClass.getActArr());
            mActDep.setText(liveRouteClass.getActDep());
            mSchArrDate.setText(liveRouteClass.getSchArrDate());
            mSchDepDate.setText(liveRouteClass.getSchDepDate());

            if (!liveRouteClass.isHasArr()) {
                mHasArr.setVisibility(View.INVISIBLE);
            }
            if (!liveRouteClass.isHasDep()) {
                mHasDep.setVisibility(View.INVISIBLE);
            }

        }

    }


}
