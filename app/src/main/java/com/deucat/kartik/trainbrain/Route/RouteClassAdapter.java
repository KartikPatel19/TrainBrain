package com.deucat.kartik.trainbrain.Route;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;

class RouteClassAdapter extends RecyclerView.Adapter<RouteClassAdapter.RouteViewHolder> {

    private RouteClass[] mRouteClasses;

    RouteClassAdapter(RouteClass[] routeClasses) {
        mRouteClasses = routeClasses;
    }

    @Override
    public RouteClassAdapter.RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_route_list, parent, false);

        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RouteClassAdapter.RouteViewHolder holder, int position) {
        holder.bindRoute(mRouteClasses[position]);
    }

    @Override
    public int getItemCount() {
        return mRouteClasses.length;
    }

    class RouteViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "RouteViewHoler";
        TextView mIndexNumber;
        TextView mDistence;
        TextView mSchArr;
        TextView mSchDep;
        TextView mNameOfStation;
        TextView mNameOfState;

         RouteViewHolder(View itemView) {
            super(itemView);
            mIndexNumber = (TextView) itemView.findViewById(R.id.routeListIndexNumber);
            mDistence = (TextView) itemView.findViewById(R.id.routeListDistance);
            mSchArr = (TextView) itemView.findViewById(R.id.routeListSch);
            mSchDep = (TextView) itemView.findViewById(R.id.routeListDep);
            mNameOfStation = (TextView) itemView.findViewById(R.id.routeListStationName);
            mNameOfState = (TextView) itemView.findViewById(R.id.routeListStateName);
        }

         void bindRoute(RouteClass routeClass) {
            mIndexNumber.setText(routeClass.getIndexNumber() + "");
            mDistence.setText(routeClass.getDistance() + "");
            mSchArr.setText(routeClass.getSchArr());
            mSchDep.setText(routeClass.getSchDep());
            mNameOfStation.setText(routeClass.getStationName());
            mNameOfState.setText(routeClass.getState());

            Log.d(TAG, "bindRoute: " + routeClass.getStationName());

        }

    }

}
