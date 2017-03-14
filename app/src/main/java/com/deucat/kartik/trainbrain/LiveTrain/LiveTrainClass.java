package com.deucat.kartik.trainbrain.LiveTrain;

class LiveTrainClass {


    private String mPosition;
    private String mTrainNumber;
    private int mResponceCode;

    LiveTrainClass() {
    }


    String getPosition() {
        return mPosition;
    }

    void setPosition(String position) {
        mPosition = position;
    }

    String getTrainNumber() {
        return mTrainNumber;
    }

    void setTrainNumber(String trainNumber) {
        mTrainNumber = trainNumber;
    }

    int getResponceCode() {
        return mResponceCode;
    }

     void setResponceCode(int responceCode) {
        mResponceCode = responceCode;
    }
}
