package com.deucat.kartik.trainbrain.pnr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.deucat.kartik.trainbrain.R


internal class PNRClassAdapter(private val mPassengerClasses: Array<PassengerClass>) :
    RecyclerView.Adapter<PNRClassAdapter.PNRViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PNRClassAdapter.PNRViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pnr_list, parent, false)
        return PNRViewHolder(view)
    }

    override fun onBindViewHolder(holder: PNRClassAdapter.PNRViewHolder, position: Int) {
        holder.bindPNR(mPassengerClasses[position])
    }

    override fun getItemCount(): Int {
        return mPassengerClasses.size
    }

    internal inner class PNRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mIndexNumber: TextView
        var mBookingStatus: TextView
        var mCurrentStatus: TextView
        var mCochePosition: TextView

        init {
            mIndexNumber = itemView.findViewById(R.id.indexPNRTVL)
            mBookingStatus = itemView.findViewById(R.id.bookingStatusPNRTVL)
            mCurrentStatus = itemView.findViewById(R.id.curruntCochePNRTVL)
            mCochePosition = itemView.findViewById(R.id.cochePositionPNRTVL)

        }

        fun bindPNR(passengerClass: PassengerClass) {
            mIndexNumber.text = passengerClass.indexNumber.toString() + ""
            mCochePosition.text = passengerClass.coachPosition.toString() + ""
            mCurrentStatus.text = passengerClass.currentStatus
            mBookingStatus.text = passengerClass.bookingStatus
        }
    }

}
