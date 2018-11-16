package com.deucat.kartik.trainbrain.mainwork


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.deucat.kartik.trainbrain.R

import org.json.JSONException
import org.json.JSONObject


class PutFragment : Fragment() {

    private lateinit var mData: Array<Data?>
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_put, container, false)
        val bundle = arguments!!
        val jsonData = bundle.getString("Data")

        mRecyclerView = view.findViewById(R.id.putMainRecyclerView)

        try {
            mData = startWork(jsonData)
            val trainAdapter = TrainAdapter(mData)
            val manager = LinearLayoutManager(activity)

            mRecyclerView.adapter = trainAdapter
            mRecyclerView.layoutManager = manager
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return view
    }

    @Throws(JSONException::class)
    private fun startWork(jsonData: String?): Array<Data?> {

        val root = JSONObject(jsonData)
        val trains = root.getJSONArray("trains")

        val data = arrayOfNulls<Data>(trains.length())

        for (i in 0 until trains.length()) {

            val `object` = trains.getJSONObject(i)

            val data1 = Data()

            data1.number = `object`.getString("number")
            data1.name = `object`.getString("name")
            data1.traverTime = `object`.getString("travel_time")
            data1.srcDepartTime = `object`.getString("src_departure_time")
            data1.destArriveTime = `object`.getString("dest_arrival_time")

            val from = `object`.getJSONObject("from_station")
            data1.fromStation = from.getString("name")

            val to = `object`.getJSONObject("to_station")
            data1.toStation = to.getString("name")

            data[i] = data1

        }



        return data
    }

}
