package com.deucat.kartik.trainbrain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class AlertDilog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDiloge = new AlertDialog.Builder(getActivity());
        alertDiloge.setTitle("Error");
        alertDiloge.setMessage("There is an auccerd error please go to contact support");
        alertDiloge.setPositiveButton("OK",null);

        return alertDiloge.create();
    }

}
