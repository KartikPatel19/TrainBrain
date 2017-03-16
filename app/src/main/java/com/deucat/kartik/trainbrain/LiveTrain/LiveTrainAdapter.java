package com.deucat.kartik.trainbrain.LiveTrain;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        TextView mIndexNumber;
        TextView mDistanceName;
        TextView mNameOfStation;
        TextView mSchArr;
        TextView mSchDep;
        TextView mSchArrDate;
        TextView mSchDepDate;

        LiveTrainHolder(View itemView) {
            super(itemView);

            mIndexNumber = (TextView) itemView.findViewById(R.id.indexNumberLiveR);
            mDistanceName = (TextView) itemView.findViewById(R.id.distanceTVLiveR);

            mNameOfStation = (TextView) itemView.findViewById(R.id.nameTVLiveR);
            mSchArr = (TextView) itemView.findViewById(R.id.schArrLiveR);
            mSchDep = (TextView) itemView.findViewById(R.id.schDepLiveR);
            mSchArrDate = (TextView) itemView.findViewById(R.id.actArrDate);
            mSchDepDate = (TextView) itemView.findViewById(R.id.actDepDate);

        }

        void bindLive(LiveRouteClass liveRouteClass) {

            mIndexNumber.setText(liveRouteClass.getIndexNumber() + "");
            mDistanceName.setText(liveRouteClass.getDistance() + "");

            mNameOfStation.setText(liveRouteClass.getNamne());
            mSchArr.setText(liveRouteClass.getSchArr());
            mSchDep.setText(liveRouteClass.getSchDep());
            mSchArrDate.setText(liveRouteClass.getSchArrDate());
            mSchDepDate.setText(liveRouteClass.getSchDepDate());

        }

    }


}
