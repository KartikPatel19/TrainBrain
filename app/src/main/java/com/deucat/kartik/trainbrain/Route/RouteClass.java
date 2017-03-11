package com.deucat.kartik.trainbrain.Route;

public class RouteClass {

    private int mIndexNumber;
    private int mDistance;
    private String mState;
    private String mSchArr;
    private String mSchDep;
    private String mStationName;

    public RouteClass() {    }

    int getIndexNumber() {
        return mIndexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        mIndexNumber = indexNumber;
    }

     int getDistance() {
        return mDistance;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

     String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

     String getSchArr() {
        return mSchArr;
    }

    public void setSchArr(String schArr) {
        mSchArr = schArr;
    }

     String getSchDep() {
        return mSchDep;
    }

    public void setSchDep(String schDep) {
        mSchDep = schDep;
    }

     String getStationName() {
        return mStationName;
    }

    public void setStationName(String stationName) {
        mStationName = stationName;
    }
}
