package com.deucat.kartik.trainbrain;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.android.gms.internal.zzahg.runOnUiThread;


public class GetFragment extends Fragment {

    public GetFragment() {
    }

    EditText mSourceCodeEt, mDestCodeEt;
    Button mDateBtn, mOkBtn;
    TextInputLayout mSourceLayout, mDestLayout;

    Calendar mCalendar;
    DatePickerDialog.OnDateSetListener mDate;
    boolean mIsDateSelected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get, container, false);
        mCalendar=Calendar.getInstance();

        mSourceCodeEt = view.findViewById(R.id.getSourceName);
        mDestCodeEt = view.findViewById(R.id.getDestName);
        mDateBtn = view.findViewById(R.id.getDateBtn);
        mOkBtn = view.findViewById(R.id.getOkBtn);
        mSourceLayout = view.findViewById(R.id.getSourceNameLayout);
        mDestLayout = view.findViewById(R.id.getDestNameLayout);

        mDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mCalendar.set(Calendar.YEAR, i);
                mCalendar.set(Calendar.MONTH, i1);
                mCalendar.set(Calendar.DAY_OF_MONTH, i2);
                mIsDateSelected = true;
            }
        };

        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection ConstantConditions
                new DatePickerDialog(getActivity(), mDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srcCode = mSourceCodeEt.getText().toString();
                String dstCode = mDestCodeEt.getText().toString();

                if (TextUtils.isEmpty(srcCode)) {
                    mSourceLayout.setError("Please enter source code");
                    return;
                }

                if (TextUtils.isEmpty(dstCode)) {
                    mSourceLayout.setError("Please enter destination code");
                    return;
                }
                if (mCalendar == null) {
                    Toast.makeText(getActivity(), "Please select date", Toast.LENGTH_SHORT).show();
                    return;
                }

                startProcess(srcCode, dstCode);

            }
        });

        return view;
    }

    private void startProcess(String srcCode, String dstCode) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(mCalendar.getTime());
        String url = "http://api.railwayapi.com/v2/between/source/" + srcCode + "/dest/" + dstCode + "/date/" + date + "/apikey/" + MainActivity.API_KEY + "/";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                //noinspection ConstantConditions
                String JSONData = response.body().string();

            }
        });

    }


}
