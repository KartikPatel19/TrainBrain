package com.deucat.kartik.trainbrain.livetrain

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import com.deucat.kartik.trainbrain.R

internal class LiveTrainAdapter(private val mRouteClasses: Array<LiveRouteClass>) :
    RecyclerView.Adapter<LiveTrainAdapter.LiveTrainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveTrainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.live_route_list, parent, false)
        return LiveTrainHolder(view)
    }

    override fun onBindViewHolder(holder: LiveTrainHolder, position: Int) {
        holder.bindLive(mRouteClasses[position])
    }

    override fun getItemCount(): Int {
        return mRouteClasses.size
    }

    internal inner class LiveTrainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mIndexNumber: TextView = itemView.findViewById(R.id.indexNumberLiveR)
        private var mNameOfStation: TextView = itemView.findViewById(R.id.nameTVLiveR)
        private var mSchArr: TextView = itemView.findViewById(R.id.schArrLiveR)
        private var mSchDep: TextView = itemView.findViewById(R.id.schDepLiveR)
        private var mSchArrDate: TextView = itemView.findViewById(R.id.actArrDate)
        private var mSchDepDate: TextView = itemView.findViewById(R.id.actDepDate)
        private var mLayout: RelativeLayout = itemView.findViewById(R.id.liveBG)

        fun bindLive(liveRouteClass: LiveRouteClass) {

            val indexNo = liveRouteClass.indexNumber
            mIndexNumber.text = indexNo.toString()

            mNameOfStation.text = liveRouteClass.name
            mSchArr.text = liveRouteClass.schArr
            mSchDep.text = liveRouteClass.schDep
            mSchArrDate.text = liveRouteClass.schArrDate
            mSchDepDate.text = liveRouteClass.schDepDate

            if (liveRouteClass.isHasArr && liveRouteClass.isHasDep) {
                mLayout.setBackgroundColor(Color.parseColor("#A5D6A7"))
            } else if (!liveRouteClass.isHasArr && liveRouteClass.isHasDep) {
                mLayout.setBackgroundColor(Color.parseColor("#FFA726"))
            } else if (!liveRouteClass.isHasArr && !liveRouteClass.isHasDep) {
                mLayout.setBackgroundColor(Color.parseColor("#FF5722"))
            } else {
                mLayout.setBackgroundColor(Color.parseColor("#757575"))
            }


        }

    }


}
