@file:Suppress("DEPRECATION")

package com.deucat.kartik.trainbrain.route


import android.app.ProgressDialog
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.deucat.kartik.trainbrain.MainActivity
import com.deucat.kartik.trainbrain.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import org.json.JSONException
import org.json.JSONObject

import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


@Suppress("DEPRECATION")
class TrainRouteActivity : Fragment() {

    private var trainClass = TrainClass()
    private lateinit var mRouteClasses: Array<RouteClass?>

    private lateinit var mEditText: EditText
    private lateinit var mButton: Button
    private lateinit var mTrainNameTv: TextView
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAdView: AdView
    private lateinit var mDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.train_route_activity, container)
        mDialog = ProgressDialog(activity)
        mDialog.hide()

        mTrainNameTv = view.findViewById(R.id.routeTrainName)
        mRecyclerView = view.findViewById(R.id.routeRecyclerView)
        mEditText = view.findViewById(R.id.routeTrainNumber)
        mButton = view.findViewById(R.id.routeOkButton)

        mAdView = view.findViewById(R.id.routeAdView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        mButton.setOnClickListener(View.OnClickListener {
            val trainNumber = mEditText.text.toString()

            if (TextUtils.isEmpty(trainNumber)) {
                return@OnClickListener
            }
            mDialog.show()
            val url = "http://api.railwayapi.com/route/train/" + trainNumber + "/apikey/" + MainActivity.API_KEY + "/"

            try {
                getJsonDataOverTheInternet(url)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })

        return view
    }

    @Throws(IOException::class)
    private fun getJsonDataOverTheInternet(url: String) {

        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                try {
                    val jsonData = response.body()!!.string()
                    if (response.isSuccessful) {
                        mRouteClasses = parseRouteClass(jsonData)
                        parseTrainClass(jsonData)
                    } else {
                        Toast.makeText(context, "PLease Enter correct number of train", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        })

    }

    @Throws(JSONException::class)
    private fun parseTrainClass(JsonData: String): TrainClass {

        val mainData = JSONObject(JsonData)
        val data = mainData.getJSONObject("train")
        trainClass.nameOfTrain = data.getString("name")
        trainClass.responseCode = mainData.getInt("response_code")

        return trainClass
    }

    @Throws(JSONException::class)
    private fun parseRouteClass(JsonData: String): Array<RouteClass?> {

        val data = JSONObject(JsonData)
        val arrayOfRoute = data.getJSONArray("route")

        val routeClasses = arrayOfNulls<RouteClass>(arrayOfRoute.length())

        for (i in 0 until arrayOfRoute.length()) {
            val routeListData = arrayOfRoute.getJSONObject(i)

            val routeClass1 = RouteClass()

            routeClass1.distance = routeListData.getInt("distance")
            routeClass1.indexNumber = routeListData.getInt("no")

            routeClass1.schArr = routeListData.getString("scharr")
            routeClass1.schDep = routeListData.getString("schdep")
            routeClass1.stationName = routeListData.getString("fullname")
            routeClass1.state = routeListData.getString("state")

            routeClasses[i] = routeClass1
        }

        return routeClasses
    }


}
