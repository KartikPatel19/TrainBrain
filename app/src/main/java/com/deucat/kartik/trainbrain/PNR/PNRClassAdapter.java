package com.deucat.kartik.trainbrain.PNR;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deucat.kartik.trainbrain.R;


class PNRClassAdapter extends RecyclerView.Adapter<PNRClassAdapter.PNRViewHolder> {

    private PassengerClass[] mPassengerClasses;

      PNRClassAdapter(PassengerClass[] passengerClasses) {
         mPassengerClasses = passengerClasses;
     }

     @Override
    public PNRClassAdapter.PNRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pnr_list,parent,false);
         return new PNRViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PNRClassAdapter.PNRViewHolder holder, int position) {
        holder.bindPNR(mPassengerClasses[position]);
    }

    @Override
    public int getItemCount() {
        return mPassengerClasses.length;
    }

      class PNRViewHolder extends RecyclerView.ViewHolder{

         TextView mIndexNumber;
         TextView mBookingStatus;
         TextView mCurrentStatus;
         TextView mCochePosition;

         PNRViewHolder(View itemView) {
            super(itemView);

            mIndexNumber = (TextView)itemView.findViewById(R.id.indexPNRTVL);
            mBookingStatus = (TextView)itemView.findViewById(R.id.bookingStatusPNRTVL);
            mCurrentStatus = (TextView)itemView.findViewById(R.id.curruntCochePNRTVL);
            mCochePosition = (TextView)itemView.findViewById(R.id.cochePositionPNRTVL);

        }

        void bindPNR(PassengerClass passengerClass ) {
            mIndexNumber.setText(passengerClass.getIndexNumber()+"");
            mCochePosition.setText(passengerClass.getCochePosition()+"");
            mCurrentStatus.setText(passengerClass.getCurruntStatus());
            mBookingStatus.setText(passengerClass.getBookingStatus());
        }
    }

}
