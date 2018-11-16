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
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import com.deucat.kartik.trainbrain.MainActivity
import com.deucat.kartik.trainbrain.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class PNRActivity : Fragment() {


    internal var pnrNumber = ""
    internal var url = "http://api.railwayapi.com/pnr_status/pnr/$pnrNumber/apikey/o9je768f/"

    internal var mPassengerClasses: Array<PassengerClass>
    internal var mPNRClass = PNRClass()

    internal var mTrainName: TextView
    internal var mTrainNumber: TextView
    internal var mDOJ: TextView
    internal var mClassOfTrain: TextView
    internal var mNumberOfPassanger: TextView
    internal var mTrainStart: TextView
    internal var mTrainFrom: TextView
    internal var mBordingAt: TextView
    internal var mToStation: TextView
    internal var mReservation: TextView

    internal var mEditText: EditText
    internal var mButton: Button

    internal var mRecyclerView: RecyclerView
    internal var mAdView: AdView

    internal var mLayout: LinearLayout
    internal var mDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_pnr, null)

        mTrainName = view.findViewById(R.id.trainName)
        mTrainNumber = view.findViewById(R.id.trainNumberPNRTV)
        mDOJ = view.findViewById(R.id.trainDOJPNRTV)
        mClassOfTrain = view.findViewById(R.id.classOfPNRTV)
        mNumberOfPassanger = view.findViewById(R.id.passangerPNRTV)
        mTrainStart = view.findViewById(R.id.trainStartPNRTV)
        mTrainFrom = view.findViewById(R.id.fromPnrTV)
        mBordingAt = view.findViewById(R.id.bordingPNRTV)
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
                    val JSONData = response.body()!!.string()
                    if (response.isSuccessful) {

                        parsePNRClass(JSONData)
                        mPassengerClasses = parsePassengersClass(JSONData)
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

        mPNRClass.responceCode = root.getInt("response_code")
        mPNRClass.trainName = root.getString("train_name")
        mPNRClass.trainNumber = root.getString("train_num")
        mPNRClass.doj = root.getString("doj")
        mPNRClass.className = root.getString("class")
        mPNRClass.totalPassanger = root.getInt("total_passengers")

        val fromStation = root.getJSONObject("from_station")
        mPNRClass.fromStationName = fromStation.getString("name")

        val boardingPoient = root.getJSONObject("boarding_point")
        mPNRClass.boardingPointName = boardingPoient.getString("name")

        val toStation = root.getJSONObject("to_station")
        mPNRClass.toStationName = toStation.getString("name")

        val reservetionUpto = root.getJSONObject("reservation_upto")
        mPNRClass.reservationName = reservetionUpto.getString("name")

    }

    @Throws(JSONException::class)
    private fun parsePassengersClass(JSonData: String): Array<PassengerClass> {

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

        return passengerClasses
    }

    private fun updateUI() {

        mRecyclerView.visibility = View.VISIBLE

        mTrainName.text = mPNRClass.trainName
        mTrainNumber.text = mPNRClass.trainNumber
        mDOJ.text = mPNRClass.doj
        mClassOfTrain.text = mPNRClass.className
        mNumberOfPassanger.text = mPNRClass.totalPassanger.toString() + ""
        mTrainStart.text = mPNRClass.doj
        mTrainFrom.text = mPNRClass.fromStationName
        mBordingAt.text = mPNRClass.boardingPointName
        mToStation.text = mPNRClass.toStationName
        mReservation.text = mPNRClass.reservationName

        val adapter = PNRClassAdapter(mPassengerClasses)
        val layoutManager = LinearLayoutManager(context)

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = layoutManager

    }

}
