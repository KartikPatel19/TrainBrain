package com.deucat.kartik.trainbrain;


import android.content.Context;

import cn.refactor.lib.colordialog.PromptDialog;

public class AlertDilog {

    public void alertErrorToUser(int errorCode, Context context) {

        switch (errorCode) {

            case 210: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\tTrain doesn’t run on the date queried.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 211: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\t\tTrain doesn’t have journey class queried.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 220: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\t0\tFlushed PNR.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 221: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\tInvalid PNR.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 304: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\tData couldn’t be fetched. No Data available.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 404: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\t\tData couldn’t be fetched. Request couldn’t go through.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 504: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\t\tArgument error.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            case 704: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\t\tUnauthorized user query. User account expired/exhausted or unregistered.")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
            break;
            default: {

                new PromptDialog(context)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Error")
                        .setContentText("\tDeveloper Error")
                        .setPositiveListener("Ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }

        }
    }

}
