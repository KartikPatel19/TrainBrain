package com.deucat.kartik.trainbrain.mainwork

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import com.deucat.kartik.trainbrain.MainActivity
import com.deucat.kartik.trainbrain.R

import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class GetFragment : Fragment() {

    private lateinit var mSourceCodeEt: EditText
    private lateinit var mDestCodeEt: EditText
    private lateinit var mDateBtn: Button
    private lateinit var mOkBtn: Button
    private lateinit var mSourceLayout: TextInputLayout
    private lateinit var mDestLayout: TextInputLayout

    private var mCalendar: Calendar? = null
    private lateinit var mDate: DatePickerDialog.OnDateSetListener
    private var mIsDateSelected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_get, container, false)
        mCalendar = Calendar.getInstance()

        mSourceCodeEt = view.findViewById(R.id.getSourceName)
        mDestCodeEt = view.findViewById(R.id.getDestName)
        mDateBtn = view.findViewById(R.id.getDateBtn)
        mOkBtn = view.findViewById(R.id.getOkBtn)
        mSourceLayout = view.findViewById(R.id.getSourceNameLayout)
        mDestLayout = view.findViewById(R.id.getDestNameLayout)

        mDate = DatePickerDialog.OnDateSetListener { _, i, i1, i2 ->
            mCalendar!!.set(Calendar.YEAR, i)
            mCalendar!!.set(Calendar.MONTH, i1)
            mCalendar!!.set(Calendar.DAY_OF_MONTH, i2)
            mIsDateSelected = true
        }

        mDateBtn.setOnClickListener {
            DatePickerDialog(
                activity!!,
                mDate,
                mCalendar!!.get(Calendar.YEAR),
                mCalendar!!.get(Calendar.MONTH),
                mCalendar!!.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        mOkBtn.setOnClickListener(View.OnClickListener {
            val srcCode = mSourceCodeEt.text.toString()
            val dstCode = mDestCodeEt.text.toString()

            if (TextUtils.isEmpty(srcCode)) {
                mSourceLayout.error = "Please enter source code"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(dstCode)) {
                mSourceLayout.error = "Please enter destination code"
                return@OnClickListener
            }
            if (mCalendar == null) {
                Toast.makeText(activity, "Please select date", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            startProcess(srcCode, dstCode)
        })

        return view
    }

    @SuppressLint("SimpleDateFormat")
    private fun startProcess(srcCode: String, dstCode: String) {

        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = dateFormat.format(mCalendar!!.time)
        val url =
            "http://api.railwayapi.com/v2/between/source/" + srcCode + "/dest/" + dstCode + "/date/" + date + "/apikey/" + MainActivity.API_KEY + "/"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {


                val jsonData = response.body()!!.string()


                val `object`: JSONObject?
                try {
                    `object` = JSONObject(jsonData)
                    val responceCode = `object`.getInt("response_code")


                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                val fragment = PutFragment()
                val bundle = Bundle()
                bundle.putString("Data", jsonData)
                fragment.arguments = bundle
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.mainFullFragment, fragment)
                fragmentTransaction.commit()


            }
        })

    }


}
