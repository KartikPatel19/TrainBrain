package com.deucat.kartik.trainbrain.Route;

public class RouteClass {

    private int mIndexNumber;
    private int mDistance;
    private String mState;
    private String mSchArr;
    private String mSchDep;
    private String mStationName;

    public RouteClass() {    }

    public int getIndexNumber() {
        return mIndexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        mIndexNumber = indexNumber;
    }

    public int getDistance() {
        return mDistance;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getSchArr() {
        return mSchArr;
    }

    public void setSchArr(String schArr) {
        mSchArr = schArr;
    }

    public String getSchDep() {
        return mSchDep;
    }

    public void setSchDep(String schDep) {
        mSchDep = schDep;
    }

    public String getStationName() {
        return mStationName;
    }

    public void setStationName(String stationName) {
        mStationName = stationName;
    }
}
