@file:Suppress("DEPRECATION")

package com.deucat.kartik.trainbrain.pnr

import android.app.ProgressDialog
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

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


class PNRActivity : Fragment() {


    private var pnrNumber = ""
    private var url = "http://api.railwayapi.com/pnr_status/pnr/$pnrNumber/apikey/o9je768f/"

    private lateinit var mPassengerClasses: Array<PassengerClass?>
    private var mPNRClass = PNRClass()

    private lateinit var mTrainName: TextView
    private lateinit var mTrainNumber: TextView
    private lateinit var mDOJ: TextView
    private lateinit var mClassOfTrain: TextView
    private lateinit var mNumberOfPassanger: TextView
    private lateinit var mTrainStart: TextView
    private lateinit var mTrainFrom: TextView
    private lateinit var mBoardingAt: TextView
    private lateinit var mToStation: TextView
    private lateinit var mReservation: TextView

    private lateinit var mEditText: EditText
    private lateinit var mButton: Button

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdView: AdView

    private lateinit var mLayout: LinearLayout
    private lateinit var mDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_pnr, container)

        mTrainName = view.findViewById(R.id.trainName)
        mTrainNumber = view.findViewById(R.id.trainNumberPNRTV)
        mDOJ = view.findViewById(R.id.trainDOJPNRTV)
        mClassOfTrain = view.findViewById(R.id.classOfPNRTV)
        mNumberOfPassanger = view.findViewById(R.id.passangerPNRTV)
        mTrainStart = view.findViewById(R.id.trainStartPNRTV)
        mTrainFrom = view.findViewById(R.id.fromPnrTV)
        mBoardingAt = view.findViewById(R.id.bordingPNRTV)
        mToStation = view.findViewById(R.id.toStationPNRTV)
        mReservation = view.findViewById(R.id.reservationPNRTV)

        mLayout = view.findViewById(R.id.pnrMainView)
        mDialog = ProgressDialog(activity)
        mDialog.hide()

        mEditText = view.findViewById(R.id.pnrEditText)
        mButton = view.findViewById(R.id.pnrOKButton)

        mRecyclerView = view.findViewById(R.id.pnrRecyclerView)

        mAdView = view.findViewById(R.id.pnrAdView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        mButton.setOnClickListener(View.OnClickListener {
            pnrNumber = mEditText.text.toString()

            if (TextUtils.isEmpty(pnrNumber)) {
                return@OnClickListener
            }

            url = "http://api.railwayapi.com/pnr_status/pnr/" + pnrNumber + "/apikey/" + MainActivity.API_KEY + "/"
            mDialog.show()
            getJSONDataOverTheInterNet(url)
        })

        getJSONDataOverTheInterNet(url)

        return view
    }

    private fun getJSONDataOverTheInterNet(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                try {
                    val jsonData = response.body()!!.string()
                    if (response.isSuccessful) {

                        parsePNRClass(jsonData)
                        mPassengerClasses = parsePassengersClass(jsonData)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })
    }

    @Throws(JSONException::class)
    private fun parsePNRClass(JSONData: String) {

        val root = JSONObject(JSONData)

        val code = root.getInt("response_code")

        mPNRClass.responseCode = root.getInt("response_code")
        mPNRClass.trainName = root.getString("train_name")
        mPNRClass.trainNumber = root.getString("train_num")
        mPNRClass.doj = root.getString("doj")
        mPNRClass.className = root.getString("class")
        mPNRClass.totalPassenger = root.getInt("total_passengers")

        val fromStation = root.getJSONObject("from_station")
        mPNRClass.fromStationName = fromStation.getString("name")

        val boardingPoient = root.getJSONObject("boarding_point")
        mPNRClass.boardingPointName = boardingPoient.getString("name")

        val toStation = root.getJSONObject("to_station")
        mPNRClass.toStationName = toStation.getString("name")

        val reservationUnto = root.getJSONObject("reservation_upto")
        mPNRClass.reservationName = reservationUnto.getString("name")

    }

    @Throws(JSONException::class)
    private fun parsePassengersClass(JSonData: String): Array<PassengerClass?> {

        val root = JSONObject(JSonData)
        val passanger = root.getJSONArray("passengers")

        val passengerClasses = arrayOfNulls<PassengerClass>(passanger.length())

        for (i in 0 until passanger.length()) {
            val passangerList = passanger.getJSONObject(i)

            val passengerClass = PassengerClass()

            passengerClass.indexNumber = passangerList.getInt("no")
            passengerClass.bookingStatus = passangerList.getString("booking_status")
            passengerClass.currentStatus = passangerList.getString("current_status")
            passengerClass.coachPosition = passangerList.getInt("coach_position")

            passengerClasses[i] = passengerClass
        }

        updateUI()

        return passengerClasses
    }

    private fun updateUI() {

        mRecyclerView.visibility = View.VISIBLE

        mTrainName.text = mPNRClass.trainName
        mTrainNumber.text = mPNRClass.trainNumber
        mDOJ.text = mPNRClass.doj
        mClassOfTrain.text = mPNRClass.className
        mNumberOfPassanger.text = mPNRClass.totalPassenger.toString()
        mTrainStart.text = mPNRClass.doj
        mTrainFrom.text = mPNRClass.fromStationName
        mBoardingAt.text = mPNRClass.boardingPointName
        mToStation.text = mPNRClass.toStationName
        mReservation.text = mPNRClass.reservationName

        val adapter = PNRClassAdapter(mPassengerClasses)
        val layoutManager = LinearLayoutManager(context)

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = layoutManager

    }

}
