package com.deucat.kartik.trainbrain.LiveTrain;

class LiveTrainClass {


    private String mPosition;
    private int mResponceCode;
    private int mCurrentIndexNumber;

    LiveTrainClass() {    }

     int getCurrentIndexNumber() {
        return mCurrentIndexNumber;
    }

     void setCurrentIndexNumber(int currentIndexNumber) {
        mCurrentIndexNumber = currentIndexNumber;
    }

    String getPosition() {
        return mPosition;
    }

    void setPosition(String position) {
        mPosition = position;
    }

    int getResponceCode() {
        return mResponceCode;
    }

     void setResponceCode(int responceCode) {
        mResponceCode = responceCode;
    }

}
