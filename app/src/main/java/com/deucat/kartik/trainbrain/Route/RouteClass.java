package com.deucat.kartik.trainbrain.Route;

class RouteClass {

    private int mIndexNumber;
    private int mDistance;
    private String mState;
    private String mSchArr;
    private String mSchDep;
    private String mStationName;

    RouteClass() {
    }

    int getIndexNumber() {
        return mIndexNumber;
    }

    void setIndexNumber(int indexNumber) {
        mIndexNumber = indexNumber;
    }

    int getDistance() {
        return mDistance;
    }

    void setDistance(int distance) {
        mDistance = distance;
    }

    String getState() {
        return mState;
    }

    void setState(String state) {
        mState = state;
    }

    String getSchArr() {
        return mSchArr;
    }

    void setSchArr(String schArr) {
        mSchArr = schArr;
    }

    String getSchDep() {
        return mSchDep;
    }

    void setSchDep(String schDep) {
        mSchDep = schDep;
    }

    String getStationName() {
        return mStationName;
    }

    void setStationName(String stationName) {
        mStationName = stationName;
    }
}
