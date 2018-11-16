package com.deucat.kartik.trainbrain.mainwork

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.deucat.kartik.trainbrain.R


class TrainAdapter internal constructor(private val mData: Array<Data>) :
    RecyclerView.Adapter<TrainAdapter.TrainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_trains, parent, false)
        return TrainHolder(view)
    }

    override fun onBindViewHolder(holder: TrainHolder, position: Int) {
        holder.bindTrain(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class TrainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mNameTv: TextView = itemView.findViewById(R.id.mainTrainName)
        private var mNumberTv: TextView = itemView.findViewById(R.id.mainTrainNumber)
        private var mTravelTimeTv: TextView = itemView.findViewById(R.id.mainTrainTotalTime)
        private var mSrcTimeTv: TextView = itemView.findViewById(R.id.mainTrainStartTime)
        private var mDestTimeTv: TextView = itemView.findViewById(R.id.mainTrainStopTime)
        private var mFromTv: TextView = itemView.findViewById(R.id.mainTrainFrom)
        private var mToTv: TextView = itemView.findViewById(R.id.mainTrainTo)

        fun bindTrain(data: Data) {
            mNameTv.text = data.name
            mNumberTv.text = data.number
            mTravelTimeTv.text = data.traverTime
            mSrcTimeTv.text = data.srcDepartTime
            mDestTimeTv.text = data.destArriveTime
            mFromTv.text = data.fromStation
            mToTv.text = data.toStation

        }

    }


}
