package com.deucat.kartik.trainbrain.LiveTrain


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


import com.deucat.kartik.trainbrain.MainActivity
import com.deucat.kartik.trainbrain.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Objects

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class LiveTrain : Fragment() {

    internal var mLiveTrainClass = LiveTrainClass()
    internal var mLiveRouteClass: Array<LiveRouteClass>

    internal var mPosition: TextView
    internal var mEditText: EditText
    internal var mButton: Button

    internal var mRecyclerView: RecyclerView
    internal var mAdView: AdView

    internal var mDialog: ProgressDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        @SuppressLint("InflateParams")
        val view = inflater.inflate(R.layout.activity_live_train, null)

        mDialog = ProgressDialog(activity)
        mDialog.hide()

        mPosition = view.findViewById(R.id.livePosition)
        mEditText = view.findViewById(R.id.liveEditText)
        mButton = view.findViewById(R.id.liveOkButton)

        mRecyclerView = view.findViewById(R.id.liveRecyclerView)

        mAdView = view.findViewById(R.id.liveAdView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mButton.setOnClickListener {
            val trainNumber = mEditText.text.toString()

            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyyMMdd")

            val date = dateFormat.format(calendar.time)
            val url =
                "http://api.railwayapi.com/live/train/" + trainNumber + "/doj/" + date + "/apikey/" + MainActivity.API_KEY + "/"
            mDialog.show()

            getDataOverTheInternet(url)
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getDataOverTheInternet(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {


                val JSONData = response.body()!!.string()

                try {
                    parshLiveTrainClass(JSONData)

                    mLiveRouteClass = parshLiveRouteClass(JSONData)

                    Objects.requireNonNull<FragmentActivity>(activity).runOnUiThread(Runnable { updateUI() })

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })

    }

    @Throws(JSONException::class)
    private fun parshLiveTrainClass(JSONData: String) {


        val root = JSONObject(JSONData)
        val responceCode = root.getInt("response_code")
        mLiveTrainClass.responceCode = responceCode

        if (responceCode != 200) {

            Objects.requireNonNull<FragmentActivity>(activity).runOnUiThread(Runnable { })
            return
        }

        mLiveTrainClass.position = root.getString("position")

        val current = root.getJSONObject("current_station")
        mLiveTrainClass.currentIndexNumber = current.getInt("no")

    }

    @Throws(JSONException::class)
    private fun parshLiveRouteClass(JSONData: String): Array<LiveRouteClass> {

        val root = JSONObject(JSONData)
        val route = root.getJSONArray("route")

        val liveRouteClasses = arrayOfNulls<LiveRouteClass>(route.length())

        for (i in 0 until route.length()) {
            val routeList = route.getJSONObject(i)

            val routeClass = LiveRouteClass()

            routeClass.indexNumber = routeList.getInt("no")
            routeClass.distance = routeList.getInt("distance")
            routeClass.lateTime = routeList.getInt("latemin")

            val forName = routeList.getJSONObject("station_")

            routeClass.name = forName.getString("name")

            routeClass.schArr = routeList.getString("scharr")
            routeClass.schDep = routeList.getString("schdep")
            routeClass.schArrDate = routeList.getString("scharr_date")
            routeClass.schDepDate = routeList.getString("actarr_date")

            routeClass.isHasArr = routeList.getBoolean("has_arrived")
            routeClass.isHasDep = routeList.getBoolean("has_departed")

            liveRouteClasses[i] = routeClass

        }

        return liveRouteClasses
    }

    private fun updateUI() {

        mPosition.text = mLiveTrainClass.position

        mDialog.hide()
        val liveTrainAdapter = LiveTrainAdapter(mLiveRouteClass)
        val manager = LinearLayoutManager(activity)
        mRecyclerView.adapter = liveTrainAdapter
        mRecyclerView.layoutManager = manager


    }

}

