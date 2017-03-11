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

     PNRClass() { }

    public String getTrainName() {
        return mTrainName;
    }

     void setTrainName(String trainName) {
        mTrainName = trainName;
    }

    public String getTrainNumber() {
        return mTrainNumber;
    }

     void setTrainNumber(String trainNumber) {
        mTrainNumber = trainNumber;
    }

    public String getDOJ() {
        return mDOJ;
    }

     void setDOJ(String DOJ) {
        mDOJ = DOJ;
    }

    public String getClassName() {
        return mClassName;
    }

     void setClassName(String className) {
        mClassName = className;
    }

    public int getTotalPassanger() {
        return mTotalPassanger;
    }

     void setTotalPassanger(int totalPassanger) {
        mTotalPassanger = totalPassanger;
    }

    public String getFromStationName() {
        return mFromStationName;
    }

     void setFromStationName(String fromStationName) {
        mFromStationName = fromStationName;
    }

    public String getBoardingPointName() {
        return mBoardingPointName;
    }

     void setBoardingPointName(String boardingPointName) {
        mBoardingPointName = boardingPointName;
    }

    public String getToStationName() {
        return mToStationName;
    }

     void setToStationName(String toStationName) {
        mToStationName = toStationName;
    }

    public String getReservationName() {
        return mReservationName;
    }

     void setReservationName(String reservationName) {
        mReservationName = reservationName;
    }
}
