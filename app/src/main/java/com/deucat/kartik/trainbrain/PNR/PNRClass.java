package com.deucat.kartik.trainbrain.PNR;

class PNRClass {

    private String mTrainName;
    private String mTrainNumber;
    private String mDOJ;
    private String mClassName;
    private int mTotalPassanger;
    private String mFromStationName;
    private String mBoardingPointName;
    private String mToStationName;
    private String mReservationName;
    private int mResponceCode;

    PNRClass() {
    }

     int getResponceCode() {
        return mResponceCode;
    }

     void setResponceCode(int responceCode) {
        mResponceCode = responceCode;
    }

    String getTrainName() {
        return mTrainName;
    }

    void setTrainName(String trainName) {
        mTrainName = trainName;
    }

    String getTrainNumber() {
        return mTrainNumber;
    }

    void setTrainNumber(String trainNumber) {
        mTrainNumber = trainNumber;
    }

    String getDOJ() {
        return mDOJ;
    }

    void setDOJ(String DOJ) {
        mDOJ = DOJ;
    }

    String getClassName() {
        return mClassName;
    }

    void setClassName(String className) {
        mClassName = className;
    }

    int getTotalPassanger() {
        return mTotalPassanger;
    }

    void setTotalPassanger(int totalPassanger) {
        mTotalPassanger = totalPassanger;
    }

    String getFromStationName() {
        return mFromStationName;
    }

    void setFromStationName(String fromStationName) {
        mFromStationName = fromStationName;
    }

    String getBoardingPointName() {
        return mBoardingPointName;
    }

    void setBoardingPointName(String boardingPointName) {
        mBoardingPointName = boardingPointName;
    }

    String getToStationName() {
        return mToStationName;
    }

    void setToStationName(String toStationName) {
        mToStationName = toStationName;
    }

    String getReservationName() {
        return mReservationName;
    }

    void setReservationName(String reservationName) {
        mReservationName = reservationName;
    }
}
