package com.deucat.kartik.trainbrain.PNR;

public class PassengerClass {

    private int mIndexNumber;
    private String mBookingStatus;
    private String mCurruntStatus;
    private int mCochePosition;

    public PassengerClass() {
    }

    public int getIndexNumber() {
        return mIndexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        mIndexNumber = indexNumber;
    }

    public String getBookingStatus() {
        return mBookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        mBookingStatus = bookingStatus;
    }

    public String getCurruntStatus() {
        return mCurruntStatus;
    }

    public void setCurruntStatus(String curruntStatus) {
        mCurruntStatus = curruntStatus;
    }

    public int getCochePosition() {
        return mCochePosition;
    }

    public void setCochePosition(int cochePosition) {
        mCochePosition = cochePosition;
    }
}
