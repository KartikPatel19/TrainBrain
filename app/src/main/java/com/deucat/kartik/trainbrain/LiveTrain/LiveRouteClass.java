package com.deucat.kartik.trainbrain.LiveTrain;

public class LiveRouteClass {

    private int mIndexNumber;
    private int mDistance;
    private int mLateTime;

    private String mNamne;
    private String mSchArr;
    private String mSchDep;
    private String mActArr;
    private String mActDep;
    private String mSchArrDate;
    private String mSchDepDate;

    private boolean mHasArr;
    private boolean mHasDep;

    public LiveRouteClass() {    }

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

    public int getLateTime() {
        return mLateTime;
    }

    public void setLateTime(int lateTime) {
        mLateTime = lateTime;
    }

    public String getNamne() {
        return mNamne;
    }

    public void setNamne(String namne) {
        mNamne = namne;
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

    public String getActArr() {
        return mActArr;
    }

    public void setActArr(String actArr) {
        mActArr = actArr;
    }

    public String getActDep() {
        return mActDep;
    }

    public void setActDep(String actDep) {
        mActDep = actDep;
    }

    public String getSchArrDate() {
        return mSchArrDate;
    }

    public void setSchArrDate(String schArrDate) {
        mSchArrDate = schArrDate;
    }

    public String getSchDepDate() {
        return mSchDepDate;
    }

    public void setSchDepDate(String schDepDate) {
        mSchDepDate = schDepDate;
    }

    public boolean isHasArr() {
        return mHasArr;
    }

    public void setHasArr(boolean hasArr) {
        mHasArr = hasArr;
    }

    public boolean isHasDep() {
        return mHasDep;
    }

    public void setHasDep(boolean hasDep) {
        mHasDep = hasDep;
    }
}
