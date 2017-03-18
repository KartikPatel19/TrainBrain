package com.deucat.kartik.trainbrain.LiveTrain;

class LiveRouteClass {

    private int mIndexNumber;
    private int mDistance;
    private int mLateTime;

    private String mNamne;
    private String mSchArr;
    private String mSchDep;
    private String mSchArrDate;
    private String mSchDepDate;

    private boolean mHasArr = true;
    private boolean mHasDep;

    LiveRouteClass() {
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

    int getLateTime() {
        return mLateTime;
    }

    void setLateTime(int lateTime) {
        mLateTime = lateTime;
    }

    String getNamne() {
        return mNamne;
    }

    void setNamne(String namne) {
        mNamne = namne;
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

    String getSchArrDate() {
        return mSchArrDate;
    }

    void setSchArrDate(String schArrDate) {
        mSchArrDate = schArrDate;
    }

    String getSchDepDate() {
        return mSchDepDate;
    }

    void setSchDepDate(String schDepDate) {
        mSchDepDate = schDepDate;
    }

    boolean isHasArr() {
        return mHasArr;
    }

    void setHasArr(boolean hasArr) {
        mHasArr = hasArr;
    }

    boolean isHasDep() {
        return mHasDep;
    }

    void setHasDep(boolean hasDep) {
        mHasDep = hasDep;
    }
}
