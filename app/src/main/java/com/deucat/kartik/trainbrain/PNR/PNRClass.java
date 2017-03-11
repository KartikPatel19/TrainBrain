package com.deucat.kartik.trainbrain.PNR;

public class PNRClass {

    private String mTrainName;
    private String mTrainNumber;
    private String mDOJ;
    private String mClassName;
    private int mTotalPassanger;
    private String mFromStationName;
    private String mBoardingPointName;
    private String mToStationName;
    private String mReservationName;

    public PNRClass() { }

    public String getTrainName() {
        return mTrainName;
    }

    public void setTrainName(String trainName) {
        mTrainName = trainName;
    }

    public String getTrainNumber() {
        return mTrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        mTrainNumber = trainNumber;
    }

    public String getDOJ() {
        return mDOJ;
    }

    public void setDOJ(String DOJ) {
        mDOJ = DOJ;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public int getTotalPassanger() {
        return mTotalPassanger;
    }

    public void setTotalPassanger(int totalPassanger) {
        mTotalPassanger = totalPassanger;
    }

    public String getFromStationName() {
        return mFromStationName;
    }

    public void setFromStationName(String fromStationName) {
        mFromStationName = fromStationName;
    }

    public String getBoardingPointName() {
        return mBoardingPointName;
    }

    public void setBoardingPointName(String boardingPointName) {
        mBoardingPointName = boardingPointName;
    }

    public String getToStationName() {
        return mToStationName;
    }

    public void setToStationName(String toStationName) {
        mToStationName = toStationName;
    }

    public String getReservationName() {
        return mReservationName;
    }

    public void setReservationName(String reservationName) {
        mReservationName = reservationName;
    }
}
